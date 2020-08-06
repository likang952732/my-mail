package com.example.mymail.controller;

import com.example.mymail.common.api.CommonResult;
import com.example.mymail.model.UmsResourceCategory;
import com.example.mymail.service.UmsResourceCategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 @Description 资源分类
 *@author kang.li
 *@date 2020/7/30 16:35   
 */
@RestController
@RequestMapping("/resourceCategory")
public class UmsResourceCategoryController {
    @Autowired
    private UmsResourceCategoryService resourceCategoryService;

    @ApiOperation("/获取所有的资源分类")
    @GetMapping("/listAll")
    public CommonResult<List<UmsResourceCategory>> listAll() {
        return CommonResult.success(resourceCategoryService.listAll());
    }

    @ApiOperation("添加资源分类")
    @PostMapping("/create")
    public CommonResult create(@RequestBody UmsResourceCategory category) {
        int count = resourceCategoryService.create(category);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("修改资源分类")
    @PostMapping("/update/{id}")
    public CommonResult updateStatus(@PathVariable("id")Long id, @RequestBody UmsResourceCategory category) {
        int count = resourceCategoryService.update(id, category);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("删除资源分类")
    @PostMapping("/delete/{id}")
    public CommonResult delete(@PathVariable("id") Long id) {
        int count = resourceCategoryService.delete(id);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

}
