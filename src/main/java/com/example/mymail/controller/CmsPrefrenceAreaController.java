package com.example.mymail.controller;

import com.example.mymail.common.api.CommonResult;
import com.example.mymail.model.CmsPrefrenceArea;
import com.example.mymail.service.CmsPrefrenceAreaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
 @Description 商品优选管理
 *@author kang.li
 *@date 2020/8/4 10:58   
 */
@RestController
@RequestMapping("/prefrenceArea")
public class CmsPrefrenceAreaController {
    @Autowired
    private CmsPrefrenceAreaService prefrenceAreaService;

    @ApiOperation("")
    @GetMapping("/listAll")
    public CommonResult<List<CmsPrefrenceArea>> listAll() {
        List<CmsPrefrenceArea> list = prefrenceAreaService.listAll();
        return CommonResult.success(list);
    }
}
