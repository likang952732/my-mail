package com.example.mymail.service;

import com.example.mymail.model.SmsFlashPromotionSession;

import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2020/8/3 10:27   
 */
public interface SmsFlashPromotionSessionService {
    SmsFlashPromotionSession getById(Long flashPromotionId);

    List<SmsFlashPromotionSession> list();

    int create(SmsFlashPromotionSession flashPromotionSession);

    int update(Long id, SmsFlashPromotionSession flashPromotionSession);

    int delete(Long id);
}
