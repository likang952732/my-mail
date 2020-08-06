package com.example.mymail.service;

import com.example.mymail.model.SmsCouponHistory;

import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2020/8/3 9:39   
 */
public interface SmsCouponHistoryService {
    List<SmsCouponHistory> list(Integer useStatus, String orderSn, Long couponId, Integer pageSize, Integer pageNum);
}
