package com.example.mymail.service;

import com.example.mymail.dto.PmsProductAttributeParam;
import com.example.mymail.model.PmsProductAttribute;

import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2020/8/4 10:00   
 */
public interface PmsProductAttributeService {
    List<PmsProductAttribute> getList(Long cid, Integer type, Integer pageSize, Integer pageNum);

    PmsProductAttribute getById(Long id);

    int create(PmsProductAttributeParam productAttributeParam);

    int update(Long id, PmsProductAttribute productAttribute);

    int delete(Long id);
}
