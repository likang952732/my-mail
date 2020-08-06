package com.example.mymail.controller;

import com.example.mymail.common.api.CommonPage;
import com.example.mymail.common.api.CommonResult;
import com.example.mymail.model.SmsHomeAdvertise;
import com.example.mymail.service.SmsHomeAdvertiseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 @Description 首轮广告轮播管理
 *@author kang.li
 *@date 2020/7/31 9:49   
 */
@RestController
@RequestMapping("/home/advertise")
public class SmsHomeAdvertiseController {
    @Autowired
    private SmsHomeAdvertiseService advertiseService;

    @ApiOperation("分页获取广告列表")
    @GetMapping("/list")
    public CommonResult<CommonPage<SmsHomeAdvertise>> list(@RequestParam(value = "name", required = false) String name,
                                                            @RequestParam(value = "type", required = false) Integer type,
                                                            @RequestParam(value = "endTime", required = false) String endTime,
                                                            @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize,
                                                            @RequestParam(value = "pageNum", defaultValue = "1")Integer pageNum) {
        List<SmsHomeAdvertise> list =  advertiseService.list(name, type, endTime, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @ApiOperation("根据id获取广告")
    @GetMapping("/{id}")
    public CommonResult<SmsHomeAdvertise> getHomeAdvertise(@PathVariable("id") Long id) {
        SmsHomeAdvertise advertise = advertiseService.getHomeAdvertiseById(id);
        return CommonResult.success(advertise);
    }

    @ApiOperation("添加广告")
    @PostMapping("/create")
    public CommonResult create(@RequestBody SmsHomeAdvertise advertise) {
        int count = advertiseService.create(advertise);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("修改状态")
    @PostMapping("/update/status/{id}")
    public CommonResult updateStatus(@PathVariable("id") Long id, @RequestParam Integer status) {
        SmsHomeAdvertise advertise = new SmsHomeAdvertise();
        advertise.setStatus(status);
        int count = advertiseService.update(id, advertise);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("编辑广告")
    @PostMapping("/update/{id}")
    public CommonResult update(@PathVariable("id") Long id, @RequestBody SmsHomeAdvertise advertise) {
        int count = advertiseService.update(id, advertise);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("删除广告")
    @PostMapping("/delete")
    public CommonResult delete(@RequestParam List<Long> ids) {
        int count = advertiseService.delete(ids);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }
}
