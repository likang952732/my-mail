package com.example.mymail.controller;

import com.example.mymail.common.api.CommonPage;
import com.example.mymail.common.api.CommonResult;
import com.example.mymail.model.SmsHomeNewProduct;
import com.example.mymail.service.SmsHomeNewProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 @Description 首页新品管理
 *@author kang.li
 *@date 2020/7/31 14:58   
 */
@RestController
@RequestMapping("/home/newProduct")
public class SmsHomeNewProductController {
    @Autowired
    private SmsHomeNewProductService homeNewProductService;

    @ApiOperation("分页查询首页新品")
    @GetMapping("/list")
    public CommonResult<CommonPage<SmsHomeNewProduct>> list(@RequestParam(value = "productName", required = false) String productName,
                                                                  @RequestParam(value = "recommendStatus", required = false)Integer recommendStatus,
                                                                  @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize,
                                                                  @RequestParam(value = "pageNum", defaultValue = "1")Integer pageNum) {
        List<SmsHomeNewProduct> list =  homeNewProductService.list(productName, recommendStatus, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @ApiOperation("添加新品")
    @PostMapping("/create")
    public CommonResult create(@RequestBody List<SmsHomeNewProduct> newProductList) {
        int count = homeNewProductService.create(newProductList);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("编辑推荐")
    @PostMapping("/update/recommendStatus")
    public CommonResult updateStatus(@RequestParam("ids") Long ids, @RequestParam Integer recommendStatus) {
        SmsHomeNewProduct recommendProduct = new SmsHomeNewProduct();
        recommendProduct.setRecommendStatus(recommendStatus);
        int count = homeNewProductService.update(ids, recommendProduct);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("修改排序")
    @PostMapping("/update/sort/{id}")
    public CommonResult updateSort(@PathVariable("id")Long id, @RequestParam("sort") Integer sort) {
        SmsHomeNewProduct recommendProduct = new SmsHomeNewProduct();
        recommendProduct.setSort(sort);
        int count = homeNewProductService.update(id, recommendProduct);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("删除新品")
    @PostMapping("/delete")
    public CommonResult delete(@RequestParam("ids") Long ids) {
        int count = homeNewProductService.delete(ids);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

}
