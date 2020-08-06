package com.example.mymail.dao;

import com.example.mymail.dto.PmsProductAttributeCategoryItem;

import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2020/8/5 9:50   
 */
public interface PmsProductAttributeCategoryDao {
    public List<PmsProductAttributeCategoryItem> getListWithAttr();
}
