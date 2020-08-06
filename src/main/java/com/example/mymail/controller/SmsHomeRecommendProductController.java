package com.example.mymail.controller;
import com.example.mymail.common.api.CommonPage;
import com.example.mymail.common.api.CommonResult;
import com.example.mymail.model.SmsHomeRecommendProduct;
import com.example.mymail.service.SmsHomeRecommendProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 @Description 首页人气推荐管理
 *@author kang.li
 *@date 2020/7/31 13:55   
 */
@RestController
@RequestMapping("/home/recommendProduct")
public class SmsHomeRecommendProductController {
    @Autowired
    private SmsHomeRecommendProductService recommendProductService;

    @ApiOperation("分页查询人气推荐")
    @GetMapping("/list")
    public CommonResult<CommonPage<SmsHomeRecommendProduct>> list(@RequestParam(value = "productName", required = false) String productName,
                                                                  @RequestParam(value = "recommendStatus", required = false)Integer recommendStatus,
                                                                  @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize,
                                                                  @RequestParam(value = "pageNum", defaultValue = "1")Integer pageNum) {
        List<SmsHomeRecommendProduct> list =  recommendProductService.list(productName, recommendStatus, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @ApiOperation("添加广告")
    @PostMapping("/create")
    public CommonResult create(@RequestBody List<SmsHomeRecommendProduct> recommendProductList) {
        int count = recommendProductService.create(recommendProductList);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("编辑推荐")
    @PostMapping("/update/recommendStatus")
    public CommonResult updateStatus(@RequestParam("ids") Long ids, @RequestParam Integer recommendStatus) {
        SmsHomeRecommendProduct recommendProduct = new SmsHomeRecommendProduct();
        recommendProduct.setRecommendStatus(recommendStatus);
        int count = recommendProductService.update(ids, recommendProduct);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("修改排序")
    @PostMapping("/update/sort/{id}")
    public CommonResult updateSort(@PathVariable("id")Long id, @RequestParam("sort") Integer sort) {
        SmsHomeRecommendProduct recommendProduct = new SmsHomeRecommendProduct();
        recommendProduct.setSort(sort);
        int count = recommendProductService.update(id, recommendProduct);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("删除人气推荐")
    @PostMapping("/delete")
    public CommonResult delete(@RequestParam("ids") Long ids) {
        int count = recommendProductService.delete(ids);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }
}
