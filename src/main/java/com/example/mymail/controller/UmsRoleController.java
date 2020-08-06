package com.example.mymail.controller;

/*
 @Description 后台角色管理
 *@author kang.li
 *@date 2020/7/30 10:21   
 */

import com.example.mymail.common.api.CommonPage;
import com.example.mymail.common.api.CommonResult;
import com.example.mymail.model.UmsMenu;
import com.example.mymail.model.UmsResource;
import com.example.mymail.model.UmsRole;
import com.example.mymail.service.UmsRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class UmsRoleController {
    @Autowired
    private UmsRoleService roleService;

    @ApiOperation("获取所有角色")
    @GetMapping("/listAll")
    public CommonResult<List<UmsRole>> listAll() {
        return CommonResult.success(roleService.list());
    }

    @ApiOperation("分页获取角色列表")
    @GetMapping("/list")
    public CommonResult<CommonPage<UmsRole>> list(@RequestParam(value = "keyword", required = false)String keyword,
                                                  @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize,
                                                  @RequestParam(value = "pageNum", defaultValue = "1")Integer pageNum) {
        List<UmsRole> roles = roleService.listPage(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(roles));
    }

    @ApiOperation("获取角色的菜单")
    @GetMapping("/listMenu/{id}")
    public CommonResult<List<UmsMenu>> listMenu(@PathVariable("id")Long id) {
        List<UmsMenu> menus = roleService.listMenu(id);
        return CommonResult.success(menus);
    }

    @ApiOperation("获取角色的资源")
    @GetMapping("/listResource/{id}")
    public CommonResult<List<UmsResource>> listResource(@PathVariable("id")Long id) {
        List<UmsResource> resources = roleService.listResource(id);
        return CommonResult.success(resources);
    }



    @ApiOperation("添加角色")
    @PostMapping("/create")
    public CommonResult create(@RequestBody UmsRole role) {
        int count = roleService.create(role);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("修改角色状态")
    @PostMapping("/updateStatus/{id}")
    public CommonResult updateStatus(@PathVariable("id")Long id, @RequestParam("status")Integer status) {
        UmsRole umsRole = new UmsRole();
        umsRole.setStatus(status);
        int count = roleService.updateUmsRole(id, umsRole);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("修改角色")
    @PostMapping("/update/{id}")
    public CommonResult updateStatus(@PathVariable("id")Long id, @RequestBody UmsRole umsRole) {
        int count = roleService.updateUmsRole(id, umsRole);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("删除角色")
    @PostMapping("/delete")
    public CommonResult delete(@RequestParam Long ids) {
        int count = roleService.delete(ids);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("分配菜单")
    @PostMapping("/allocMenu")
    public CommonResult allocMenu(@RequestParam Long roleId, @RequestParam List<Long> menuIds) {
        int count = roleService.allocMenu(roleId, menuIds);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("分配资源")
    @PostMapping("/allocResource")
    public CommonResult allocResource(@RequestParam Long roleId, @RequestParam List<Long> resourceIds) {
        int count = roleService.allocResource(roleId, resourceIds);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

}
