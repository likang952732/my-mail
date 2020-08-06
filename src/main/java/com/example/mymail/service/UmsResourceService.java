package com.example.mymail.service;

import com.example.mymail.model.UmsResource;

import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2020/7/29 14:56   
 */
public interface UmsResourceService {
    /**
     * 查询全部资源
     */
    List<UmsResource> list(String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum);

    List<UmsResource> listAll();

    int update(Long id, UmsResource umsResource);

    int delete(Long id);

    int create(UmsResource umsResource);
}
