package com.example.mymail.service;

import com.example.mymail.dto.PmsProductCategoryParam;
import com.example.mymail.dto.PmsProductCategoryWithChildrenItem;
import com.example.mymail.model.PmsProductCategory;

import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2020/8/3 9:02   
 */
public interface PmsProductCategoryService {
    List<PmsProductCategoryWithChildrenItem> withChildren();

    List<PmsProductCategory> listPage(Long parentId, Integer pageSize, Integer pageNum);

    int update(Long id, PmsProductCategory productCategory);

    PmsProductCategory getById(Long id);

    int updateCategoryParam(Long id, PmsProductCategoryParam productCategoryParam);

    int create(PmsProductCategoryParam productCategoryParam);

    int delete(Long id);
}
