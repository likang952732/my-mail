package com.example.mymail.dao;

import com.example.mymail.model.UmsAdminRoleRelation;
import com.example.mymail.model.UmsResource;
import com.example.mymail.model.UmsRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/*
 @Description 后台用户与角色管理自定义Dao
 *@author kang.li
 *@date 2020/7/29 16:40   
 */
public interface UmsAdminRoleRelationDao {
    /**
     * 获取用户所有可访问资源
     */
    List<UmsResource> getResourceList(@Param("adminId") Long adminId);

    /**
     * 获取系统用户角色列表
     * @param adminId
     * @return
     */
    List<UmsRole> getRoleList(@Param("adminId") Long adminId);

    int insertList(@Param("list") List<UmsAdminRoleRelation> list);
}
