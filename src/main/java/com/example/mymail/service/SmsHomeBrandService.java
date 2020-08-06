package com.example.mymail.service;

import com.example.mymail.model.SmsHomeBrand;

import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2020/7/31 15:51   
 */
public interface SmsHomeBrandService {
    List<SmsHomeBrand> list(String brandName, Integer recommendStatus, Integer pageSize, Integer pageNum);

    int create(List<SmsHomeBrand> newProductList);

    int update(Long ids, SmsHomeBrand homeBrand);

    int delete(List<Long> ids);
}
