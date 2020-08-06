package com.example.mymail.service.impl;

import com.example.mymail.mapper.PmsSkuStockMapper;
import com.example.mymail.model.PmsSkuStock;
import com.example.mymail.service.PmsSkuStockService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/*
 @Description
 *@author kang.li
 *@date 2020/8/4 9:51   
 */
@Service
public class PmsSkuStockServiceImpl implements PmsSkuStockService {
    @Resource
    private PmsSkuStockMapper skuStockMapper;

    @Override
    public PmsSkuStock getById(Long id) {
        return skuStockMapper.selectByPrimaryKey(id);
    }
}
