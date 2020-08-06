package com.example.mymail.controller;

import com.example.mymail.common.api.CommonPage;
import com.example.mymail.common.api.CommonResult;
import com.example.mymail.dto.PmsProductCategoryParam;
import com.example.mymail.dto.PmsProductCategoryWithChildrenItem;
import com.example.mymail.model.PmsProductCategory;
import com.example.mymail.service.PmsProductCategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 @Description 商品分类模块
 *@date 2020/8/3 9:00   
 */
@RestController
@RequestMapping("/productCategory")
public class PmsProductCategoryController {
    @Autowired
    private PmsProductCategoryService productCategoryService;

    @ApiOperation("获取所有一级分类及其子类")
    @GetMapping("/list/withChildren")
    public CommonResult<List<PmsProductCategoryWithChildrenItem>> withChildren() {
        List<PmsProductCategoryWithChildrenItem> list = productCategoryService.withChildren();
        return CommonResult.success(list);
    }

    @ApiOperation("分页查询商品分类")
    @GetMapping("/list/{parentId}")
    public CommonResult<CommonPage<PmsProductCategory>> list(@PathVariable("parentId")Long parentId,
                                                             @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize,
                                                             @RequestParam(value = "pageNum", defaultValue = "1")Integer pageNum) {
        List<PmsProductCategory> list = productCategoryService.listPage(parentId, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @ApiOperation("获取单个商品分类")
    @GetMapping("/{id}")
    public CommonResult<PmsProductCategory> getById(@PathVariable("id")Long id){
        PmsProductCategory productCategory = productCategoryService.getById(id);
        return CommonResult.success(productCategory);
    }

    @ApiOperation("添加商品分类")
    @PostMapping("/create")
    public CommonResult create(@RequestBody PmsProductCategoryParam productCategoryParam) {
        int count = productCategoryService.create(productCategoryParam);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("编辑商品分类")
    @PostMapping("/update/{id}")
    public CommonResult update(@PathVariable("id")Long id, @RequestBody PmsProductCategoryParam productCategoryParam) {
        int count = productCategoryService.updateCategoryParam(id, productCategoryParam);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("修改导航栏状态")
    @PostMapping("/update/navStatus")
    public CommonResult updateNavStatus(@RequestParam("ids")Long ids, @RequestParam("navStatus")Integer navStatus) {
        PmsProductCategory productCategory = new PmsProductCategory();
        productCategory.setNavStatus(navStatus);
        int count = productCategoryService.update(ids, productCategory);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("修改是否显示")
    @PostMapping("/update/showStatus")
    public CommonResult updateShowStatus(@RequestParam("ids")Long ids, @RequestParam("showStatus")Integer showStatus) {
        PmsProductCategory productCategory = new PmsProductCategory();
        productCategory.setShowStatus(showStatus);
        int count = productCategoryService.update(ids, productCategory);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("删除")
    @PostMapping("/delete/{id}")
    public CommonResult delete(@PathVariable("id")Long id) {
        int count = productCategoryService.delete(id);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }
}
