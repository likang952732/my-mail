package com.example.mymail.service;

import com.example.mymail.model.CmsSubject;

import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2020/7/31 11:32   
 */
public interface CmsSubjectService {
    List<CmsSubject> list(String keyword, Integer pageSize, Integer pageNum);

    List<CmsSubject> listAll();
}
