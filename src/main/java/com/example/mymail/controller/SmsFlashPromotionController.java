package com.example.mymail.controller;

import com.example.mymail.common.api.CommonPage;
import com.example.mymail.common.api.CommonResult;
import com.example.mymail.model.SmsFlashPromotion;
import com.example.mymail.model.SmsFlashPromotionSession;
import com.example.mymail.service.SmsFlashPromotionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 @Description 限时购活动管理
 *@author kang.li
 *@date 2020/8/3 10:09   
 */
@RestController
@RequestMapping("/flash")
public class SmsFlashPromotionController {
    @Autowired
    private SmsFlashPromotionService flashPromotionService;

    @ApiOperation("分页查询限时购活动")
    @GetMapping("/list")
    public CommonResult<CommonPage<SmsFlashPromotion>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                            @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize,
                                                            @RequestParam(value = "pageNum", defaultValue = "1")Integer pageNum) {
        List<SmsFlashPromotion> list =  flashPromotionService.list(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @ApiOperation("编辑状态")
    @PostMapping("/update/status/{id}")
    public CommonResult updateStatus(@PathVariable("id")Long id, @RequestParam("status")Integer status){
        SmsFlashPromotion flashPromotion = new SmsFlashPromotion();
        flashPromotion.setStatus(status);
        int count = flashPromotionService.update(id, flashPromotion);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("编辑")
    @PostMapping("/update/{id}")
    public CommonResult updateStatus(@PathVariable("id")Long id, @RequestBody SmsFlashPromotion flashPromotion){
        int count = flashPromotionService.update(id, flashPromotion);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("添加")
    @PostMapping("/create")
    public CommonResult create(@RequestBody SmsFlashPromotion flashPromotion){
        int count = flashPromotionService.create(flashPromotion);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("删除")
    @PostMapping("/delete/{id}")
    public CommonResult delete(@PathVariable("id")Long id){
        int count = flashPromotionService.delete(id);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }
}
