package com.example.mymail.service;

import com.example.mymail.model.UmsMemberLevel;

import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2020/8/4 10:22   
 */
public interface UmsMemberLevelService {
    List<UmsMemberLevel> getList(Integer defaultStatus);
}
