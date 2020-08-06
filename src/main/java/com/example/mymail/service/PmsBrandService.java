package com.example.mymail.service;

import com.example.mymail.model.PmsBrand;

import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2020/7/31 16:19   
 */
public interface PmsBrandService {
    List<PmsBrand> list(String keyword, Integer pageSize, Integer pageNum);

    int updateFactoryStatu(Long id, PmsBrand brand);

    int updateShowStatus(Long id, PmsBrand brand);

    int update(Long id, PmsBrand brand);

    PmsBrand getById(Long id);

    int create(PmsBrand brand);
}
