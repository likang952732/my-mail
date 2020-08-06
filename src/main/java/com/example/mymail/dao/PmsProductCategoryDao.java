package com.example.mymail.dao;

import com.example.mymail.dto.PmsProductCategoryWithChildrenItem;

import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2020/8/3 9:11   
 */
public interface PmsProductCategoryDao {
    List<PmsProductCategoryWithChildrenItem> withChildren();
}
