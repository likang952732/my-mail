package com.example.mymail.service;

import com.example.mymail.model.UmsMenu;
import com.example.mymail.model.UmsResource;
import com.example.mymail.model.UmsRole;
import com.example.mymail.model.UmsRoleExample;

import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2020/7/30 9:02   
 */
public interface UmsRoleService {
    List<UmsRole> getUmsRole(Long adminId);

    List<UmsMenu> getMenuList(Long adminId);

    List<UmsRole> list();

    List<UmsRole> listPage(String keyword, Integer pageSize, Integer pageNum);

    int updateUmsRole(Long roleId, UmsRole umsRole);

    int create(UmsRole role);

    int delete(Long id);

    List<UmsMenu> listMenu(Long roleId);

    int allocMenu(Long roleId, List<Long> menuIds);

    List<UmsResource> listResource(Long id);

    int allocResource(Long roleId, List<Long> resourceIds);
}
