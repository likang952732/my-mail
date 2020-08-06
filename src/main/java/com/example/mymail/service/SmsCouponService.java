package com.example.mymail.service;

import com.example.mymail.model.SmsCoupon;

import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2020/7/31 16:38   
 */
public interface SmsCouponService {
    List<SmsCoupon> list(String name, Integer type, Integer pageSize, Integer pageNum);

    int create(SmsCoupon coupon);

    SmsCoupon getCouponById(Long id);

    int update(Long id, SmsCoupon coupon);

    int delete(Long id);
}
