package com.example.mymail.controller;

import com.example.mymail.common.api.CommonResult;
import com.example.mymail.model.UmsMemberLevel;
import com.example.mymail.service.UmsMemberLevelService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
 @Description 会员等级管理
 *@author kang.li
 *@date 2020/8/4 10:19   
 */
@RestController
@RequestMapping("/memberLevel")
public class UmsMemberLevelController {
    @Autowired
    private UmsMemberLevelService memberLevelService;

    @ApiOperation("")
    @GetMapping("/list")
    public CommonResult<List<UmsMemberLevel>> list(@RequestParam("defaultStatus")Integer defaultStatus) {
        List<UmsMemberLevel> list = memberLevelService.getList(defaultStatus);
        return CommonResult.success(list);
    }

}
