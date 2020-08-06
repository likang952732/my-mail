package com.example.mymail.service;

import com.example.mymail.model.OmsOrderSetting;

/*
 @Description
 *@author kang.li
 *@date 2020/8/3 14:07   
 */
public interface OmsOrderSettingService {
    int updtae(Long id, OmsOrderSetting orderSetting);

    OmsOrderSetting getById(Long id);
}
