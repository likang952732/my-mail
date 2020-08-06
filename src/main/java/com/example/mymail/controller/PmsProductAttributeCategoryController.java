package com.example.mymail.controller;

import com.example.mymail.common.api.CommonPage;
import com.example.mymail.common.api.CommonResult;
import com.example.mymail.dto.PmsProductAttributeCategoryItem;
import com.example.mymail.model.PmsProductAttributeCategory;
import com.example.mymail.service.PmsProductAttributeCategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 @Description 商品属性分类
 *@author kang.li
 *@date 2020/8/4 10:37   
 */
@RestController
@RequestMapping("/productAttribute/category")
public class PmsProductAttributeCategoryController {
    @Autowired
    private PmsProductAttributeCategoryService productAttributeCategoryService;

    @ApiOperation("分页查询商品属性分类")
    @GetMapping("/list")
    public CommonResult<CommonPage<PmsProductAttributeCategory>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                     @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize,
                                                     @RequestParam(value = "pageNum", defaultValue = "1")Integer pageNum) {
        List<PmsProductAttributeCategory> list =  productAttributeCategoryService.list(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @ApiOperation("获取所有商品属性及其以下属性")
    @GetMapping("/list/withAttr")
    public CommonResult<List<PmsProductAttributeCategoryItem>> getListWithAttr() {
        List<PmsProductAttributeCategoryItem> list = productAttributeCategoryService.getListWithAttr();
        return CommonResult.success(list);
    }

    @ApiOperation("创建商品属性分类")
    @PostMapping("/create")
    public CommonResult create(PmsProductAttributeCategory productAttributeCategory) {
        int count = productAttributeCategoryService.create(productAttributeCategory);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("删除")
    @GetMapping("/delete/{id}")
    public CommonResult delete(@PathVariable("id")Long id) {
        int count = productAttributeCategoryService.delete(id);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }
}
