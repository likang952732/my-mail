package com.example.mymail.service;

import com.example.mymail.model.UmsResourceCategory;

import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2020/7/30 16:37   
 */
public interface UmsResourceCategoryService {
    List<UmsResourceCategory> listAll();

    int update(Long id, UmsResourceCategory category);

    int delete(Long id);

    int create(UmsResourceCategory category);
}
