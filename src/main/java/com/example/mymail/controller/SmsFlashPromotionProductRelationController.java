package com.example.mymail.controller;

import com.example.mymail.common.api.CommonPage;
import com.example.mymail.common.api.CommonResult;
import com.example.mymail.model.SmsFlashPromotionProductRelation;
import com.example.mymail.service.SmsFlashPromotionProductRelationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
 @Description 限时购和商品关系管理
 *@author kang.li
 *@date 2020/8/3 10:37   
 */
@RestController
@RequestMapping("/flashProductRelation")
public class SmsFlashPromotionProductRelationController {
    @Autowired
    private SmsFlashPromotionProductRelationService flashPromotionProductRelationService;

    @ApiOperation("分页查询限时购和商品关系")
    @GetMapping("/list")
    public CommonResult<CommonPage<SmsFlashPromotionProductRelation>> list(@RequestParam(value = "flashPromotionId") Long flashPromotionId,
                                                           @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize,
                                                           @RequestParam(value = "pageNum", defaultValue = "1")Integer pageNum) {
        List<SmsFlashPromotionProductRelation> list = flashPromotionProductRelationService.list(flashPromotionId, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(list));
    }

}
