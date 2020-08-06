package com.example.mymail.controller;

import com.example.mymail.common.api.CommonResult;
import com.example.mymail.model.PmsSkuStock;
import com.example.mymail.service.PmsSkuStockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 @Description sku库存管理
 *@author kang.li
 *@date 2020/8/4 9:44   
 */
@RestController
@RequestMapping("/sku")
public class PmsSkuStockController {
    @Autowired
    private PmsSkuStockService skuStockService;

    @ApiOperation("")
    @GetMapping("/{id}")
    public CommonResult<PmsSkuStock> getById(@PathVariable("id")Long id){
        PmsSkuStock skuStock = skuStockService.getById(id);
        return CommonResult.success(skuStock);
    }
}
