package com.example.mymail.service;

import com.example.mymail.model.SmsHomeNewProduct;

import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2020/7/31 15:06   
 */
public interface SmsHomeNewProductService {
    List<SmsHomeNewProduct> list(String productName, Integer recommendStatus, Integer pageSize, Integer pageNum);

    int update(Long id, SmsHomeNewProduct recommendProduct);

    int create(List<SmsHomeNewProduct> newProductList);

    int delete(Long id);
}
