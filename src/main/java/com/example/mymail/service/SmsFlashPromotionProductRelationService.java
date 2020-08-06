package com.example.mymail.service;

import com.example.mymail.model.SmsFlashPromotionProductRelation;

import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2020/8/3 10:41   
 */
public interface SmsFlashPromotionProductRelationService {
    List<SmsFlashPromotionProductRelation> list(Long flashPromotionId, Integer pageSize, Integer pageNum);
}
