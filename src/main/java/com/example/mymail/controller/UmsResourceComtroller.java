package com.example.mymail.controller;

import com.example.mymail.common.api.CommonPage;
import com.example.mymail.common.api.CommonResult;
import com.example.mymail.model.UmsResource;
import com.example.mymail.service.UmsResourceService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.event.ListDataEvent;
import java.util.List;

/*
 @Description 资源管理
 *@author kang.li
 *@date 2020/7/30 16:18   
 */
@RestController
@RequestMapping("/resource")
public class UmsResourceComtroller {
    @Autowired
    private UmsResourceService resourceService;

    @ApiOperation("获取资源分页列表")
    @GetMapping("/list")
    public CommonResult<CommonPage<UmsResource>> list(@RequestParam(value = "nameKeyword", required = false) String nameKeyword,
                                                      @RequestParam(value = "urlKeyword", required = false) String urlKeyword,
                                                      @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize,
                                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<UmsResource> resources = resourceService.list(nameKeyword, urlKeyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(resources));
    }

    @ApiOperation("")
    @GetMapping("/listAll")
    public CommonResult<List<UmsResource>> listAll() {
        List<UmsResource> resources = resourceService.listAll();
        return CommonResult.success(resources);
    }

    @ApiOperation("添加资源")
    @PostMapping("/create")
    public CommonResult create(@RequestBody UmsResource umsResource) {
        int count = resourceService.create(umsResource);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("修改资源")
    @PostMapping("/update/{id}")
    public CommonResult updateStatus(@PathVariable("id")Long id, @RequestBody UmsResource umsResource) {
        int count = resourceService.update(id, umsResource);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("删除资源")
    @PostMapping("/delete/{id}")
    public CommonResult delete(@PathVariable("id") Long id) {
        int count = resourceService.delete(id);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }
}
