package com.example.mymail.dao;

import com.example.mymail.model.PmsProductAttributeValue;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/*
 @Description 商品参数，商品自定义规格属性
 *@author kang.li
 *@date 2020/8/4 15:05   
 */
public interface PmsProductAttributeValueDao {
    /**
     * 批量创建
     */
    int insertList(@Param("list") List<PmsProductAttributeValue> productAttributeValueList);
}
