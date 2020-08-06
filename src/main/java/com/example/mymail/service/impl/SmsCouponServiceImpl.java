package com.example.mymail.service.impl;

import com.example.mymail.mapper.SmsCouponMapper;
import com.example.mymail.model.SmsCoupon;
import com.example.mymail.model.SmsCouponExample;
import com.example.mymail.service.SmsCouponService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2020/7/31 16:38   
 */
@Service
public class SmsCouponServiceImpl implements SmsCouponService {
    @Resource
    private SmsCouponMapper couponMapper;
    @Override
    public List<SmsCoupon> list(String name, Integer type, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        SmsCouponExample example = new SmsCouponExample();
        SmsCouponExample.Criteria criteria = example.createCriteria();
        if(!StringUtils.isEmpty(name))
            criteria.andNameLike("%" + name + "%");
        if(!StringUtils.isEmpty(type)) {
            criteria.andTypeEqualTo(type);
        }
        return couponMapper.selectByExample(example);
    }

    @Override
    public int create(SmsCoupon coupon) {
        return couponMapper.insert(coupon);
    }

    @Override
    public SmsCoupon getCouponById(Long id) {
        return couponMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(Long id, SmsCoupon coupon) {
        return couponMapper.updateByPrimaryKeySelective(coupon);
    }

    @Override
    public int delete(Long id) {
        return couponMapper.deleteByPrimaryKey(id);
    }
}
