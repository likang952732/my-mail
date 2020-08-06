package com.example.mymail.dao;

import com.example.mymail.model.PmsSkuStock;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/*
 @Description 自定义商品sku库存
 *@author kang.li
 *@date 2020/8/4 15:03   
 */
public interface PmsSkuStockDao {
    /*
     * 批量插入操作
     */
    int insertList(@Param("list") List<PmsSkuStock> skuStockList);

    /**
     * 批量插入或替换操作
     */
    int replaceList(@Param("list")List<PmsSkuStock> skuStockList);
}
