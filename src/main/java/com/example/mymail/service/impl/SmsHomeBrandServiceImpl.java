package com.example.mymail.service.impl;

import com.example.mymail.mapper.SmsHomeBrandMapper;
import com.example.mymail.model.SmsHomeBrand;
import com.example.mymail.model.SmsHomeBrandExample;
import com.example.mymail.service.SmsHomeBrandService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2020/7/31 15:52   
 */
@Service
public class SmsHomeBrandServiceImpl implements SmsHomeBrandService {
    @Resource
    private SmsHomeBrandMapper homeBrandMapper;


    @Override
    public List<SmsHomeBrand> list(String brandName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        SmsHomeBrandExample example = new SmsHomeBrandExample();
        SmsHomeBrandExample.Criteria criteria = example.createCriteria();
        if(!StringUtils.isEmpty(brandName))
            criteria.andBrandNameLike("%" + brandName + "%");
        if(!StringUtils.isEmpty(recommendStatus))
            criteria.andRecommendStatusEqualTo(recommendStatus);
        return homeBrandMapper.selectByExample(example);
    }

    @Override
    public int create(List<SmsHomeBrand> newProductList) {
        int count = newProductList == null ? 0 : newProductList.size();
        for (SmsHomeBrand homeBrand: newProductList) {
            homeBrand.setRecommendStatus(1);
            homeBrand.setSort(0);
            homeBrandMapper.insert(homeBrand);
        }
        return count;
    }

    @Override
    public int update(Long ids, SmsHomeBrand homeBrand) {
        homeBrand.setId(ids);
        return homeBrandMapper.updateByPrimaryKeySelective(homeBrand);
    }

    @Override
    public int delete(List<Long> ids) {
        int count = ids == null ? 0 : ids.size();
        for (Long id:ids) {
            homeBrandMapper.deleteByPrimaryKey(id);
        }
        return count;
    }
}
