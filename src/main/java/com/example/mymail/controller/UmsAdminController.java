package com.example.mymail.controller;

/*
 @Description 后台用户管理
 *@author kang.li
 *@date 2020/7/29 14:02   
 */

import com.example.mymail.common.api.CommonPage;
import com.example.mymail.common.api.CommonResult;
import com.example.mymail.dto.UmsAdminLoginParam;
import com.example.mymail.dto.UmsAdminParam;
import com.example.mymail.model.UmsAdmin;
import com.example.mymail.model.UmsRole;
import com.example.mymail.service.UmsAdminService;
import com.example.mymail.service.UmsRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class UmsAdminController {
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private UmsAdminService adminService;
    @Autowired
    private UmsRoleService roleService;


    @ApiOperation(value = "登录以后返回token")
    @PostMapping("/login")
    public CommonResult login(@RequestBody UmsAdminLoginParam umsAdminLoginParam, BindingResult result) {
        String token = adminService.login(umsAdminLoginParam.getUsername(), umsAdminLoginParam.getPassword());
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public CommonResult<UmsAdmin> register(@RequestBody UmsAdminParam umsAdminParam) {
        UmsAdmin umsAdmin = adminService.register(umsAdminParam);
        if(umsAdmin == null)
            CommonResult.failed();
        return CommonResult.success(umsAdmin);
    }

    @ApiOperation(value = "获取登录用户信息")
    @GetMapping("/info")
    public CommonResult<Map> info(Principal principal) {
        if (principal == null)
            return CommonResult.unauthorized(null);
        UmsAdmin umsAdmin = adminService.getAdminByUsername(principal.getName());
        Long id = umsAdmin.getId();
        Map<String, Object> data = new HashMap<>();
        data.put("username", umsAdmin.getUsername());
        data.put("roles", roleService.getUmsRole(id));
        data.put("icon", umsAdmin.getIcon());
        data.put("menus", roleService.getMenuList(id));
        return CommonResult.success(data);
    }

    @ApiOperation(value = "退出")
    @PostMapping("/logout")
    public CommonResult logout() {
        return CommonResult.success(null);
    }

    @ApiOperation(value = "分页获取用户列表数据")
    @GetMapping("/list")
    public CommonResult<CommonPage<UmsAdmin>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                   @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<UmsAdmin> umsAdminList = adminService.getUserPage(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(umsAdminList));
    }

    @ApiOperation("修改用户状态")
    @PostMapping("/updateStatus/{id}")
    public CommonResult updateStatus(@PathVariable("id") Long id, @RequestParam("status") Integer status) {
        UmsAdmin umsAdmin = new UmsAdmin();
        umsAdmin.setStatus(status);
        int count = adminService.updateAdmin(id, umsAdmin);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("编辑用户")
    @PostMapping("/update/{id}")
    public CommonResult update(@PathVariable("id") Long id, @RequestBody UmsAdmin umsAdmin) {
        int count = adminService.updateAdmin(id, umsAdmin);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("删除用户")
    @PostMapping("/delete/{id}")
    public CommonResult delete(@PathVariable("id") Long id) {
        int count = adminService.deleteAdmin(id);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("获取指定用户的角色")
    @GetMapping("/role/{id}")
    public CommonResult<List<UmsRole>> role(@PathVariable("id") Long id) {
        List<UmsRole> roles = roleService.getUmsRole(id);
        return CommonResult.success(roles);
    }

    @ApiOperation("分配角色")
    @PostMapping("/role/update")
    public CommonResult update(@RequestParam("adminId") Long adminId, @RequestParam("roleIds")List<Long> roleIds) {
        int count = adminService.updateRole(adminId, roleIds);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }
}
