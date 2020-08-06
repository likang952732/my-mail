package com.example.mymail.controller;

import com.example.mymail.common.api.CommonPage;
import com.example.mymail.common.api.CommonResult;
import com.example.mymail.model.SmsCouponHistory;
import com.example.mymail.service.SmsCouponHistoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
 @Description 优惠券领取记录管理
 *@author kang.li
 *@date 2020/8/3 9:37   
 */
@RestController
@RequestMapping("/couponHistory")
public class SmsCouponHistoryController {
    @Autowired
    private SmsCouponHistoryService couponHistoryService;

    @ApiOperation("分页查询优惠券记录")
    @GetMapping("/list")
    public CommonResult<CommonPage<SmsCouponHistory>> list(@RequestParam(value = "useStatus", required = false) Integer useStatus,
                                                           @RequestParam(value = "orderSn", required = false) String orderSn,
                                                    @RequestParam(value = "couponId") Long couponId,
                                                    @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize,
                                                    @RequestParam(value = "pageNum", defaultValue = "1")Integer pageNum) {
        List<SmsCouponHistory> list = couponHistoryService.list(useStatus, orderSn, couponId, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(list));
    }
}
