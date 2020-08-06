package com.example.mymail.service;

import com.example.mymail.model.SmsHomeAdvertise;

import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2020/7/31 9:51   
 */
public interface SmsHomeAdvertiseService {

    List<SmsHomeAdvertise> list(String keyword, Integer type, String endTime, Integer pageSize, Integer pageNum);

    int update(Long id, SmsHomeAdvertise advertise);

    SmsHomeAdvertise getHomeAdvertiseById(Long id);

    int delete(List<Long> ids);

    int create(SmsHomeAdvertise advertise);
}
