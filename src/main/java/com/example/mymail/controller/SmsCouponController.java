package com.example.mymail.controller;

import com.example.mymail.common.api.CommonPage;
import com.example.mymail.common.api.CommonResult;
import com.example.mymail.model.SmsCoupon;
import com.example.mymail.service.SmsCouponService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 @Description 优惠券管理
 *@author kang.li
 *@date 2020/7/31 16:34   
 */
@RestController
@RequestMapping("/coupon")
public class SmsCouponController {
    @Autowired
    private SmsCouponService couponService;

    @ApiOperation("分页查询优惠券")
    @GetMapping("/list")
    public CommonResult<CommonPage<SmsCoupon>> list(@RequestParam(value = "name", required = false) String name,
                                                                  @RequestParam(value = "type", required = false) Integer type,
                                                                  @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize,
                                                                  @RequestParam(value = "pageNum", defaultValue = "1")Integer pageNum) {
        List<SmsCoupon> list = couponService.list(name, type, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @ApiOperation("查询优惠券信息")
    @GetMapping("/{id}")
    public CommonResult<SmsCoupon> getCouponById(@PathVariable("id")Long id){
        SmsCoupon coupon = couponService.getCouponById(id);
        return CommonResult.success(coupon);
    }

    @ApiOperation("添加优惠券")
    @PostMapping("/create")
    public CommonResult create(@RequestBody SmsCoupon coupon) {
        int count = couponService.create(coupon);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("编辑优惠券")
    @PostMapping("/update/{id}")
    public CommonResult update(@PathVariable("id")Long id, @RequestBody SmsCoupon coupon){
        int count = couponService.update(id, coupon);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("删除优惠券")
    @PostMapping("/delete/{id}")
    public CommonResult delete(@PathVariable("id")Long id){
        int count = couponService.delete(id);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

}
