package com.example.mymail.controller;

import com.example.mymail.common.api.CommonPage;
import com.example.mymail.common.api.CommonResult;
import com.example.mymail.dto.PmsProductParam;
import com.example.mymail.model.PmsProduct;
import com.example.mymail.service.PmsProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 @Description 商品管理
 *@author kang.li
 *@date 2020/7/31 14:29   
 */
@RestController
@RequestMapping("/product")
public class PmsProductController {
    @Autowired
    private PmsProductService productService;

    @ApiOperation("分页查询商品")
    @GetMapping("/list")
    public CommonResult<CommonPage<PmsProduct>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                     @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize,
                                                     @RequestParam(value = "pageNum", defaultValue = "1")Integer pageNum) {
        List<PmsProduct> list =  productService.list(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @ApiOperation("添加商品")
    @PostMapping("/create")
    public CommonResult create(@RequestBody PmsProductParam productParam) {
        int count = productService.create(productParam);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }


    @ApiOperation("修改上架状态")
    @PostMapping("/update/publishStatus")
    public CommonResult publishStatus(@RequestParam("ids")Long ids, @RequestParam("publishStatus")Integer publishStatus) {
        PmsProduct product = new PmsProduct();
        product.setPublishStatus(publishStatus);
        int count = productService.publishStatus(ids, product);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("修改新品状态")
    @PostMapping("/update/newStatus")
    public CommonResult newStatus(@RequestParam("ids")Long ids, @RequestParam("newStatus")Integer newStatus) {
        PmsProduct product = new PmsProduct();
        product.setNewStatus(newStatus);
        int count = productService.publishStatus(ids, product);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("修改推荐状态")
    @PostMapping("/update/recommendStatus")
    public CommonResult recommendStatus(@RequestParam("ids")Long ids, @RequestParam("recommendStatus")Integer recommendStatus) {
        PmsProduct product = new PmsProduct();
        product.setRecommandStatus(recommendStatus);
        int count = productService.publishStatus(ids, product);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

}
