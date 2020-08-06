package com.example.mymail.service.impl;

import com.example.mymail.mapper.SmsHomeNewProductMapper;
import com.example.mymail.model.SmsHomeNewProduct;
import com.example.mymail.model.SmsHomeNewProductExample;
import com.example.mymail.service.SmsHomeNewProductService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2020/7/31 15:07   
 */
@Service
public class SmsHomeNewProductServiceImpl implements SmsHomeNewProductService {
    @Resource
    private SmsHomeNewProductMapper homeNewProductMapper;

    @Override
    public List<SmsHomeNewProduct> list(String productName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        SmsHomeNewProductExample example = new SmsHomeNewProductExample();
        SmsHomeNewProductExample.Criteria criteria = example.createCriteria();
        if(!StringUtils.isEmpty(productName))
            criteria.andProductNameLike("%" + productName + "%");
        if(!StringUtils.isEmpty(recommendStatus))
            criteria.andRecommendStatusEqualTo(recommendStatus);
        return homeNewProductMapper.selectByExample(example);
    }

    @Override
    public int update(Long id, SmsHomeNewProduct recommendProduct) {
        recommendProduct.setId(id);
        return homeNewProductMapper.updateByPrimaryKeySelective(recommendProduct);
    }

    @Override
    public int create(List<SmsHomeNewProduct> newProductList) {
        int count = newProductList == null ? 0 : newProductList.size();
        for (SmsHomeNewProduct  newProduct: newProductList) {
            newProduct.setRecommendStatus(1);
            newProduct.setSort(0);
            homeNewProductMapper.insert(newProduct);
        }
        return count;
    }

    @Override
    public int delete(Long id) {
        return homeNewProductMapper.deleteByPrimaryKey(id);
    }
}
