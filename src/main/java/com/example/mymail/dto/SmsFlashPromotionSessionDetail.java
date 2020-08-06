package com.example.mymail.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 包含商品数量的场次信息
 * Created by macro on 2018/11/19.
 */
public class SmsFlashPromotionSessionDetail extends com.example.mymail.model.SmsFlashPromotionSession {
    @Setter
    @Getter
    private Long productCount;
}
