package com.example.mymail.controller;

import com.example.mymail.common.api.CommonPage;
import com.example.mymail.common.api.CommonResult;
import com.example.mymail.model.SmsHomeBrand;
import com.example.mymail.service.SmsHomeBrandService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 @Description 首页品牌推荐管理
 *@author kang.li
 *@date 2020/7/31 15:49   
 */
@RestController
@RequestMapping("/home/brand")
public class SmsHomeBrandController {
    @Autowired
    private SmsHomeBrandService homeBrandService;

    @ApiOperation("分页查询首页品牌推荐")
    @GetMapping("/list")
    public CommonResult<CommonPage<SmsHomeBrand>> list(@RequestParam(value = "brandName", required = false) String brandName,
                                                            @RequestParam(value = "recommendStatus", required = false)Integer recommendStatus,
                                                            @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize,
                                                            @RequestParam(value = "pageNum", defaultValue = "1")Integer pageNum) {
        List<SmsHomeBrand> list =  homeBrandService.list(brandName, recommendStatus, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @ApiOperation("添加品牌推荐")
    @PostMapping("/create")
    public CommonResult create(@RequestBody List<SmsHomeBrand> newProductList) {
        int count = homeBrandService.create(newProductList);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("编辑推荐")
    @PostMapping("/update/recommendStatus")
    public CommonResult updateStatus(@RequestParam("ids") Long ids, @RequestParam Integer recommendStatus) {
        SmsHomeBrand homeBrand = new SmsHomeBrand();
        homeBrand.setRecommendStatus(recommendStatus);
        int count = homeBrandService.update(ids, homeBrand);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("修改排序")
    @PostMapping("/update/sort/{id}")
    public CommonResult updateSort(@PathVariable("id")Long id, @RequestParam("sort") Integer sort) {
        SmsHomeBrand homeBrand = new SmsHomeBrand();
        homeBrand.setSort(sort);
        int count = homeBrandService.update(id, homeBrand);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("删除新品")
    @PostMapping("/delete")
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        int count = homeBrandService.delete(ids);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }
}
