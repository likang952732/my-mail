package com.example.mymail.dao;

import com.example.mymail.model.UmsMenu;
import com.example.mymail.model.UmsResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2020/7/30 9:42   
 */
public interface UmsRoleDao {
    List<UmsMenu> getMenuList(@Param("adminId")Long adminId);

    List<UmsMenu> getMenuListByRoleId(@Param("roleId")Long roleId);

    List<UmsResource> getResourceListByRoleId(@Param("roleId")Long roleId);
}
