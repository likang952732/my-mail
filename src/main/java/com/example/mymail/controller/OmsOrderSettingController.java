package com.example.mymail.controller;

import com.example.mymail.common.api.CommonResult;
import com.example.mymail.model.OmsOrderSetting;
import com.example.mymail.service.OmsOrderSettingService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/*
 @Description 订单设置
 *@author kang.li
 *@date 2020/8/3 14:01   
 */
@RestController
@RequestMapping("/orderSetting")
public class OmsOrderSettingController {
    @Autowired
    private OmsOrderSettingService orderSettingService;

    @ApiOperation("订单设置")
    @PostMapping("/update/{id}")
    public CommonResult update(@PathVariable("id")Long id , @RequestBody OmsOrderSetting orderSetting) {
        int count = orderSettingService.updtae(id, orderSetting);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("获取订单设置")
    @GetMapping("/{id}")
    public CommonResult getById(@PathVariable("id")Long id){
        OmsOrderSetting orderSetting = orderSettingService.getById(id);
        return CommonResult.success(orderSetting);
    }

}
