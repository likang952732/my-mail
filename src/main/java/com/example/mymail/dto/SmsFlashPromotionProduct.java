package com.example.mymail.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 限时购及商品信息封装
 * Created by macro on 2018/11/16.
 */
public class SmsFlashPromotionProduct extends com.example.mymail.model.SmsFlashPromotionProductRelation {
    @Getter
    @Setter
    private com.example.mymail.model.PmsProduct product;
}
