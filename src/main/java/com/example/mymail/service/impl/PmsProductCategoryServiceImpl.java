package com.example.mymail.service.impl;

import com.example.mymail.dao.PmsProductCategoryAttributeRelationDao;
import com.example.mymail.dao.PmsProductCategoryDao;
import com.example.mymail.dto.PmsProductCategoryParam;
import com.example.mymail.dto.PmsProductCategoryWithChildrenItem;
import com.example.mymail.mapper.PmsProductCategoryAttributeRelationMapper;
import com.example.mymail.mapper.PmsProductCategoryMapper;
import com.example.mymail.mapper.PmsProductMapper;
import com.example.mymail.model.*;
import com.example.mymail.service.PmsProductCategoryService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2020/8/3 9:02   
 */
@Service
public class PmsProductCategoryServiceImpl implements PmsProductCategoryService {
    @Resource
    private PmsProductCategoryDao productCategoryDao;
    @Resource
    private PmsProductCategoryAttributeRelationDao productCategoryAttributeRelationDao;
    @Resource
    private PmsProductCategoryMapper productCategoryMapper;
    @Resource
    private PmsProductMapper productMapper;
    @Resource
    private PmsProductCategoryAttributeRelationMapper productCategoryAttributeRelationMapper;


    @Override
    public List<PmsProductCategoryWithChildrenItem> withChildren() {
        return productCategoryDao.withChildren();
    }

    @Override
    public List<PmsProductCategory> listPage(Long parentId, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        PmsProductCategoryExample example = new PmsProductCategoryExample();
        PmsProductCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        return productCategoryMapper.selectByExample(example);
    }

    @Override
    public int update(Long id, PmsProductCategory productCategory) {
        productCategory.setId(id);
        return productCategoryMapper.updateByPrimaryKeySelective(productCategory);
    }

    @Override
    public PmsProductCategory getById(Long id) {
        return productCategoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateCategoryParam(Long id, PmsProductCategoryParam productCategoryParam) {
        PmsProductCategory productCategory = new PmsProductCategory();
        BeanUtils.copyProperties(productCategoryParam, productCategory);
        productCategory.setId(id);
        setCategoryLevel(productCategory);
        //更新商品中的商品分类名称
        PmsProduct product = new PmsProduct();
        product.setProductCategoryName(productCategory.getName());
        PmsProductExample example = new PmsProductExample();
        example.createCriteria().andProductCategoryIdEqualTo(id);
        productMapper.updateByExampleSelective(product,example);
        //同时更新筛选属性的信息
        if(!CollectionUtils.isEmpty(productCategoryParam.getProductAttributeIdList())){
            PmsProductCategoryAttributeRelationExample relationExample = new PmsProductCategoryAttributeRelationExample();
            relationExample.createCriteria().andProductCategoryIdEqualTo(id);
            productCategoryAttributeRelationMapper.deleteByExample(relationExample);
            insertRelationList(id, productCategoryParam.getProductAttributeIdList());
        }else{
            PmsProductCategoryAttributeRelationExample relationExample = new PmsProductCategoryAttributeRelationExample();
            relationExample.createCriteria().andProductCategoryIdEqualTo(id);
            productCategoryAttributeRelationMapper.deleteByExample(relationExample);
        }
        return productCategoryMapper.updateByPrimaryKeySelective(productCategory);
    }

    @Override
    public int create(PmsProductCategoryParam productCategoryParam) {
        PmsProductCategory productCategory = new PmsProductCategory();
        productCategory.setProductCount(0);
        BeanUtils.copyProperties(productCategoryParam, productCategory);
        setCategoryLevel(productCategory);
        int count = productCategoryMapper.insertSelective(productCategory);
        //创建关联的属性
        List<Long> productAttributeIdList = productCategoryParam.getProductAttributeIdList();
        if(!CollectionUtils.isEmpty(productAttributeIdList)){
            insertRelationList(productCategory.getId(), productAttributeIdList);
        }
        return count;
    }

    @Override
    public int delete(Long id) {
        return productCategoryMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据分类parentId设置分类lever
     * @param productCategory
     */
    private void setCategoryLevel(PmsProductCategory productCategory) {
        if(productCategory.getParentId() == 0) {
            productCategory.setLevel(0);
        } else {
            //有父分类时选择根据父分类level设置
            PmsProductCategory parentCategory = productCategoryMapper.selectByPrimaryKey(productCategory.getParentId());
            productCategory.setLevel(0);
            if (parentCategory != null) {
                productCategory.setLevel(parentCategory.getLevel() + 1);
            }
        }
    }

    /**
     * 批量插入商品分类与筛选属性关系表
     * @param productCategoryId 商品分类id
     * @param productAttributeIdList 相关商品筛选属性id集合
     */
    private void insertRelationList(Long productCategoryId, List<Long> productAttributeIdList) {
        List<PmsProductCategoryAttributeRelation> relationList = new ArrayList<>();
        for (Long productAttrId : productAttributeIdList) {
            PmsProductCategoryAttributeRelation relation = new PmsProductCategoryAttributeRelation();
            relation.setProductAttributeId(productAttrId);
            relation.setProductCategoryId(productCategoryId);
            relationList.add(relation);
        }
        productCategoryAttributeRelationDao.insertList(relationList);
    }
}
