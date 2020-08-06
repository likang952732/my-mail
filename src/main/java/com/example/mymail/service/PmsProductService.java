package com.example.mymail.service;

import com.example.mymail.dto.PmsProductParam;
import com.example.mymail.model.PmsProduct;

import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2020/7/31 14:32   
 */
public interface PmsProductService {
    List<PmsProduct> list(String keyword, Integer pageSize, Integer pageNum);

    int publishStatus(Long id, PmsProduct product);

    int create(PmsProductParam productParam);
}
