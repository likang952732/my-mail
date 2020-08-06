package com.example.mymail.service;

import com.example.mymail.model.SmsHomeRecommendProduct;

import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2020/7/31 13:56   
 */
public interface SmsHomeRecommendProductService {
    List<SmsHomeRecommendProduct> list(String productName, Integer recommendStatus, Integer pageSize, Integer pageNum);

    int update(Long id, SmsHomeRecommendProduct recommendProduct);

    int delete(Long id);

    int create(List<SmsHomeRecommendProduct> recommendProductList);
}
