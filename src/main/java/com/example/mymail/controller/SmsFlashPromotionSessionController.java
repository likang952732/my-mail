package com.example.mymail.controller;

import com.example.mymail.common.api.CommonResult;
import com.example.mymail.model.SmsFlashPromotionSession;
import com.example.mymail.service.SmsFlashPromotionSessionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 @Description 限时购场次管理
 *@author kang.li
 *@date 2020/8/3 10:24   
 */
@RestController
@RequestMapping("/flashSession")
public class SmsFlashPromotionSessionController {
    @Autowired
    private SmsFlashPromotionSessionService flashPromotionSessionService;

    @ApiOperation("查询优惠券信息")
    @GetMapping("/selectList")
    public CommonResult<SmsFlashPromotionSession> getCouponById(@RequestParam("flashPromotionId")Long flashPromotionId){
        SmsFlashPromotionSession flashPromotionSession = flashPromotionSessionService.getById(flashPromotionId);
        return CommonResult.success(flashPromotionSession);
    }

    @ApiOperation("获取全部场次")
    @GetMapping("/list")
    public CommonResult<List<SmsFlashPromotionSession>> list(){
        List<SmsFlashPromotionSession> list = flashPromotionSessionService.list();
        return CommonResult.success(list);
    }

    @ApiOperation("添加场次")
    @PostMapping("/create")
    public CommonResult create(@RequestBody SmsFlashPromotionSession flashPromotionSession) {
        int count = flashPromotionSessionService.create(flashPromotionSession);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("编辑场次")
    @PostMapping("/update/{id}")
    public CommonResult update(@PathVariable("id")Long id, @RequestBody SmsFlashPromotionSession flashPromotionSession){
        int count = flashPromotionSessionService.update(id, flashPromotionSession);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("编辑场次状态")
    @PostMapping("/update/status/{id}")
    public CommonResult update(@PathVariable("id")Long id, @RequestParam("status")Integer status){
        SmsFlashPromotionSession flashPromotionSession = new SmsFlashPromotionSession();
        flashPromotionSession.setStatus(status);
        int count = flashPromotionSessionService.update(id, flashPromotionSession);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("删除场次")
    @PostMapping("/delete/{id}")
    public CommonResult delete(@PathVariable("id")Long id){
        int count = flashPromotionSessionService.delete(id);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }
}
