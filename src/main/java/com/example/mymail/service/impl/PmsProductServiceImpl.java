package com.example.mymail.service.impl;

import com.example.mymail.dao.*;
import com.example.mymail.dto.PmsProductParam;
import com.example.mymail.mapper.PmsProductMapper;
import com.example.mymail.model.PmsProduct;
import com.example.mymail.model.PmsProductExample;
import com.example.mymail.model.PmsSkuStock;
import com.example.mymail.service.PmsProductService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2020/7/31 14:32   
 */
@Service
@Slf4j
public class PmsProductServiceImpl implements PmsProductService {
    @Resource
    private PmsProductMapper productMapper;
    @Resource
    private PmsMemberPriceDao memberPriceDao;
    @Resource
    private PmsProductLadderDao productLadderDao;
    @Resource
    private PmsProductFullReductionDao productFullReductionDao;
    @Resource
    private PmsSkuStockDao skuStockDao;
    @Resource
    private PmsProductAttributeValueDao productAttributeValueDao;
    @Resource
    private CmsSubjectProductRelationDao subjectProductRelationDao;
    @Resource
    private CmsPrefrenceAreaProductRelationDao prefrenceAreaProductRelationDao;

    @Override
    public List<PmsProduct> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        PmsProductExample example = new PmsProductExample();
        PmsProductExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(keyword))
            criteria.andNameLike("%" + keyword + "%");
        return productMapper.selectByExample(example);
    }

    @Override
    public int publishStatus(Long id, PmsProduct product) {
        product.setId(id);
        return productMapper.updateByPrimaryKeySelective(product);
    }

    @Override
    public int create(PmsProductParam productParam) {
        int count;
        //创建商品
        PmsProduct product = productParam;
        product.setId(null);
        productMapper.insertSelective(product);
        //根据促销类型设置价格：会员价格、阶梯价格、满减价格
        Long productId = product.getId();
        //会员价格
        relateAndInsertList(memberPriceDao, productParam.getMemberPriceList() ,productId);
        //阶梯价格
        relateAndInsertList(productLadderDao, productParam.getProductLadderList(), productId);
        //满减价格
        relateAndInsertList(productFullReductionDao, productParam.getProductFullReductionList(), productId);
        //处理sku的编码
        handleSkuStockCode(productParam.getSkuStockList(),productId);
        //添加sku库存信息
        relateAndInsertList(skuStockDao, productParam.getSkuStockList(), productId);
        //添加商品参数,添加自定义商品规格
        relateAndInsertList(productAttributeValueDao, productParam.getProductAttributeValueList(), productId);
        //关联专题
        relateAndInsertList(subjectProductRelationDao, productParam.getSubjectProductRelationList(), productId);
        //关联优选
        relateAndInsertList(prefrenceAreaProductRelationDao, productParam.getPrefrenceAreaProductRelationList(), productId);
        count = 1;
        return count;
    }



    /**
     * 建立和插入关系表操作
     *
     * @param dao       可以操作的dao
     * @param dataList  要插入的数据
     * @param productId 建立关系的id
     */
    private void relateAndInsertList(Object dao, List dataList, Long productId) {
        try {
            if (CollectionUtils.isEmpty(dataList)) return;
            for (Object item : dataList) {
                Method setId = item.getClass().getMethod("setId", Long.class);
                setId.invoke(item, (Long) null);
                Method setProductId = item.getClass().getMethod("setProductId", Long.class);
                setProductId.invoke(item, productId);
            }
            Method insertList = dao.getClass().getMethod("insertList", List.class);
            insertList.invoke(dao, dataList);
        } catch (Exception e) {
            log.warn("创建产品出错:{}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    private void handleSkuStockCode(List<PmsSkuStock> skuStockList, Long productId) {
        if(CollectionUtils.isEmpty(skuStockList))return;
        for(int i=0;i<skuStockList.size();i++){
            PmsSkuStock skuStock = skuStockList.get(i);
            if(StringUtils.isEmpty(skuStock.getSkuCode())){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                StringBuilder sb = new StringBuilder();
                //日期
                sb.append(sdf.format(new Date()));
                //四位商品id
                sb.append(String.format("%04d", productId));
                //三位索引id
                sb.append(String.format("%03d", i+1));
                skuStock.setSkuCode(sb.toString());
            }
        }
    }
}
