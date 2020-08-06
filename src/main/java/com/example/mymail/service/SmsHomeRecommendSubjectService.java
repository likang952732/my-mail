package com.example.mymail.service;

import com.example.mymail.model.SmsHomeRecommendSubject;

import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2020/7/31 10:58   
 */
public interface SmsHomeRecommendSubjectService {
    List<SmsHomeRecommendSubject> list(String name, Integer status, Integer pageSize, Integer pageNum);

    int update(Long id, SmsHomeRecommendSubject subject);

    int delete(Long id);

    int create(List<SmsHomeRecommendSubject> recommendSubjectList);
}
