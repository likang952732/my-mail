package com.example.mymail.controller;

import com.example.mymail.common.api.CommonResult;
import com.example.mymail.model.OmsCompanyAddress;
import com.example.mymail.service.OmsCompanyAddressService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
 @Description 收货地址管理
 *@author kang.li
 *@date 2020/8/3 15:48   
 */
@RestController
@RequestMapping("/companyAddress")
public class OmsCompanyAddressController {
    @Autowired
    private OmsCompanyAddressService companyAddressService;

    @ApiOperation("获取收货地址")
    @GetMapping("/list")
    public CommonResult list(){
        List<OmsCompanyAddress> list = companyAddressService.list();
        return CommonResult.success(list);
    }

}
