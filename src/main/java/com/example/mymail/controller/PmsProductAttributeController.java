package com.example.mymail.controller;

import com.example.mymail.common.api.CommonPage;
import com.example.mymail.common.api.CommonResult;
import com.example.mymail.dto.PmsProductAttributeParam;
import com.example.mymail.model.PmsProductAttribute;
import com.example.mymail.service.PmsProductAttributeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 @Description 商品属性管理
 *@author kang.li
 *@date 2020/8/4 9:58   
 */
@RestController
@RequestMapping("/productAttribute")
public class PmsProductAttributeController {
    @Autowired
    private PmsProductAttributeService productAttributeService;

    @ApiOperation("根据产品属性分类id查询属性列表或参数列表")
    @GetMapping("/list/{cid}")
    public CommonResult<CommonPage<PmsProductAttribute>> list(@PathVariable("cid")Long cid,
                                                              @RequestParam("type")Integer type,
                                                              @RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize,
                                                              @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum) {
        List<PmsProductAttribute> list = productAttributeService.getList(cid, type, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @ApiOperation("获取商品属性")
    @GetMapping("/attrInfo/{id}")
    public CommonResult<PmsProductAttribute> getAttrInfoById(@PathVariable("id")Long id) {
        PmsProductAttribute productAttribute = productAttributeService.getById(id);
        return CommonResult.success(productAttribute);
    }

    @ApiOperation("")
    @GetMapping("/{id}")
    public CommonResult<PmsProductAttribute> getById(@PathVariable("id")Long id) {
        PmsProductAttribute productAttribute = productAttributeService.getById(id);
        return CommonResult.success(productAttribute);
    }

    @ApiOperation("编辑商品属性")
    @PostMapping("/update/{id}")
    public CommonResult update(@PathVariable("id")Long id, @RequestBody PmsProductAttribute productAttribute) {
        int count = productAttributeService.update(id, productAttribute);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("添加商品属性")
    @PostMapping("/create")
    public CommonResult create(@RequestBody PmsProductAttributeParam productAttributeParam) {
        int count = productAttributeService.create(productAttributeParam);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("删除商品属性")
    @PostMapping("/delete")
    public CommonResult delete(@RequestParam("ids")Long ids) {
        int count = productAttributeService.delete(ids);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }
}
