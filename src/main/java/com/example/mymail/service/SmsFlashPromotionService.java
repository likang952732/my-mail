package com.example.mymail.service;

import com.example.mymail.model.SmsFlashPromotion;

import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2020/8/3 10:10   
 */
public interface SmsFlashPromotionService {
    List<SmsFlashPromotion> list(String keyword, Integer pageSize, Integer pageNum);

    int update(Long id, SmsFlashPromotion flashPromotion);

    int create(SmsFlashPromotion flashPromotion);

    int delete(Long id);
}
