package com.example.mymail.dao;

import com.example.mymail.model.PmsProductFullReduction;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/*
 @Description 自定义商品满减
 *@author kang.li
 */
public interface PmsProductFullReductionDao {
    /**
     * 批量创建
     */
    int insertList(@Param("list") List<PmsProductFullReduction> productFullReductionList);
}
