package com.example.mymail.service.impl;

import com.example.mymail.mapper.SmsHomeRecommendProductMapper;
import com.example.mymail.model.SmsHomeRecommendProduct;
import com.example.mymail.model.SmsHomeRecommendProductExample;
import com.example.mymail.service.SmsHomeRecommendProductService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2020/7/31 13:56   
 */
@Service
public class SmsHomeRecommendProductServiceImpl implements SmsHomeRecommendProductService {
    @Resource
    private SmsHomeRecommendProductMapper recommendProductMapper;

    @Override
    public List<SmsHomeRecommendProduct> list(String productName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        SmsHomeRecommendProductExample example = new SmsHomeRecommendProductExample();
        SmsHomeRecommendProductExample.Criteria criteria = example.createCriteria();
        if(!StringUtils.isEmpty(productName))
            criteria.andProductNameLike("%" + productName + "%");
        if(!StringUtils.isEmpty(recommendStatus))
            criteria.andRecommendStatusEqualTo(recommendStatus);
        return recommendProductMapper.selectByExample(example);
    }

    @Override
    public int update(Long id, SmsHomeRecommendProduct recommendProduct) {
        recommendProduct.setId(id);
        return recommendProductMapper.updateByPrimaryKeySelective(recommendProduct);
    }

    @Override
    public int delete(Long id) {
        return recommendProductMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int create(List<SmsHomeRecommendProduct> recommendProductList) {
        int count = recommendProductList == null ? 0 : recommendProductList.size();
        for (SmsHomeRecommendProduct recommendProduct: recommendProductList) {
            recommendProductMapper.insert(recommendProduct);
        }
        return count;
    }
}
