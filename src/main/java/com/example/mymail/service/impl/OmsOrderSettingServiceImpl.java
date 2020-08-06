package com.example.mymail.service.impl;

import com.example.mymail.mapper.OmsOrderSettingMapper;
import com.example.mymail.model.OmsOrderSetting;
import com.example.mymail.service.OmsOrderSettingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/*
 @Description
 *@author kang.li
 *@date 2020/8/3 14:07   
 */
@Service
public class OmsOrderSettingServiceImpl implements OmsOrderSettingService {
    @Resource
    private OmsOrderSettingMapper orderSettingMapper;
    @Override
    public int updtae(Long id, OmsOrderSetting orderSetting) {
        orderSetting.setId(id);
        return orderSettingMapper.updateByPrimaryKeySelective(orderSetting);
    }

    @Override
    public OmsOrderSetting getById(Long id) {
        return orderSettingMapper.selectByPrimaryKey(id);
    }
}
