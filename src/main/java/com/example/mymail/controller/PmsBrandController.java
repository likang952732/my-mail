package com.example.mymail.controller;

import com.example.mymail.common.api.CommonPage;
import com.example.mymail.common.api.CommonResult;
import com.example.mymail.model.PmsBrand;
import com.example.mymail.service.PmsBrandService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 @Description 品牌管理
 *@author kang.li
 *@date 2020/7/31 16:12   
 */
@RestController
@RequestMapping("/brand")
public class PmsBrandController {
    @Autowired
    private PmsBrandService brandService;

    @ApiOperation("分页查询品牌")
    @GetMapping("/list")
    public CommonResult<CommonPage<PmsBrand>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                     @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize,
                                                     @RequestParam(value = "pageNum", defaultValue = "1")Integer pageNum) {
        List<PmsBrand> list = brandService.list(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @ApiOperation("")
    @PostMapping("/create")
    public CommonResult create(@RequestBody PmsBrand brand) {
        int count = brandService.create(brand);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }


    @ApiOperation("修改品牌制造商")
    @PostMapping("/update/factoryStatu")
    public CommonResult updateFactoryStatu(@RequestParam("id")Long id, @RequestParam("factoryStatus")Integer factoryStatus){
        PmsBrand brand = new PmsBrand();
        brand.setFactoryStatus(factoryStatus);
        int count = brandService.updateFactoryStatu(id, brand);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("修改是否显示")
    @PostMapping("/update/showStatus")
    public CommonResult updateShowStatus(@RequestParam("ids")Long ids, @RequestParam("showStatus")Integer showStatus){
        PmsBrand brand = new PmsBrand();
        brand.setShowStatus(showStatus);
        int count = brandService.updateShowStatus(ids, brand);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("编辑品牌")
    @PostMapping("/update/{id}")
    public CommonResult update(@PathVariable("id")Long id, @RequestBody PmsBrand brand){
        int count = brandService.update(id, brand);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("获取品牌信息")
    @GetMapping("/{id}")
    public CommonResult getById(@PathVariable("id")Long id) {
        PmsBrand brand = brandService.getById(id);
        return CommonResult.success(brand);
    }





}
