package com.example.mymail.controller;

import com.example.mymail.common.api.CommonPage;
import com.example.mymail.common.api.CommonResult;
import com.example.mymail.dto.UmsMenuNode;
import com.example.mymail.model.UmsMenu;
import com.example.mymail.service.UmsMenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 @Description  菜单管理
 *@author kang.li
 *@date 2020/7/30 14:28   
 */
@RestController
@RequestMapping("/menu")
public class UmsMenuController {
    @Autowired
    private UmsMenuService menuService;

    @ApiOperation("获取菜单分页列表")
    @GetMapping("/list/{parentId}")
    public CommonResult<CommonPage<UmsMenu>> list(@PathVariable("parentId")Long parentId, @RequestParam(value = "keyword", required = false) String keyword,
                                            @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize,
                                            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<UmsMenu> menus = menuService.list(parentId, keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(menus));
    }

    @ApiOperation("获取菜单信息")
    @GetMapping("/{menuId}")
    public CommonResult<UmsMenu> getMenu(@PathVariable("menuId")Long menuId) {
        UmsMenu menu = menuService.getMenuById(menuId);
        return CommonResult.success(menu);
    }

    @ApiOperation("树形结构返回所有菜单列表")
    @GetMapping("/treeList")
    public CommonResult<List<UmsMenuNode>> treeList() {
        List<UmsMenuNode> menuNodes = menuService.treeList();
        return CommonResult.success(menuNodes);
    }

    @ApiOperation("添加菜单")
    @PostMapping("/create")
    public CommonResult create(@RequestBody UmsMenu menu) {
        int count = menuService.create(menu);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("删除菜单")
    @PostMapping("/delete/{menuId}")
    public CommonResult delete(@PathVariable("menuId")Long menuId) {
        int count = menuService.delete(menuId);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("编辑菜单")
    @PostMapping("/update/{menuId}")
    public CommonResult update(@PathVariable("menuId")Long menuId, @RequestBody UmsMenu menu) {
        int count = menuService.update(menuId, menu);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("编辑菜单")
    @PostMapping("/updateHidden/{menuId}")
    public CommonResult updateHidden(@PathVariable("menuId")Long menuId, @RequestParam("hidden") Integer hidden) {
        UmsMenu menu = new UmsMenu();
        menu.setHidden(hidden);
        int count = menuService.update(menuId, menu);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }


}
