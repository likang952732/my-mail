package com.example.mymail.service;

import com.example.mymail.dto.PmsProductAttributeCategoryItem;
import com.example.mymail.model.PmsProductAttributeCategory;

import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2020/8/4 10:40   
 */
public interface PmsProductAttributeCategoryService {
    List<PmsProductAttributeCategory> list(String keyword, Integer pageSize, Integer pageNum);

    List<PmsProductAttributeCategoryItem> getListWithAttr();

    int create(PmsProductAttributeCategory productAttributeCategory);

    int delete(Long id);
}
