package com.example.mymail.service.impl;

import com.example.mymail.dao.UmsAdminRoleRelationDao;
import com.example.mymail.dao.UmsRoleDao;
import com.example.mymail.mapper.UmsRoleMapper;
import com.example.mymail.mapper.UmsRoleMenuRelationMapper;
import com.example.mymail.mapper.UmsRoleResourceRelationMapper;
import com.example.mymail.model.*;
import com.example.mymail.service.UmsRoleService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import javax.annotation.Resource;
import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2020/7/30 9:13   
 */
@Service
public class UmsRoleServiceImpl implements UmsRoleService {
    @Resource
    private UmsAdminRoleRelationDao roleRelationDao;
    @Resource
    private UmsRoleDao roleDao;
    @Resource
    private UmsRoleMapper roleMapper;
    @Resource
    private UmsRoleMenuRelationMapper roleMenuRelationMapper;

    @Resource
    private UmsRoleResourceRelationMapper roleResourceRelationMapper;

    @Override
    public List<UmsRole> getUmsRole(Long adminId) {
        return roleRelationDao.getRoleList(adminId);
    }

    @Override
    public List<UmsMenu> getMenuList(Long adminId) {
        return roleDao.getMenuList(adminId);
    }

    @Override
    public List<UmsRole> list() {
        return roleMapper.selectByExample(new UmsRoleExample());
    }

    @Override
    public List<UmsRole> listPage(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        UmsRoleExample example = new UmsRoleExample();
        UmsRoleExample.Criteria criteria = example.createCriteria();
        if(!StringUtils.isEmpty(keyword)) {
            criteria.andNameLike("%" + keyword + "%");
        }
        return roleMapper.selectByExample(example);
    }

    @Override
    public int updateUmsRole(Long roleId, UmsRole umsRole) {
        umsRole.setId(roleId);
        return roleMapper.updateByPrimaryKeySelective(umsRole);
    }

    @Override
    public int create(UmsRole role) {
        return roleMapper.insert(role);
    }

    @Override
    public int delete(Long id) {
        return roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<UmsMenu> listMenu(Long roleId) {
        return roleDao.getMenuListByRoleId(roleId);
    }

    @Override
    public int allocMenu(Long roleId, List<Long> menuIds) {
        int count = menuIds == null ? 0 : menuIds.size();
        UmsRoleMenuRelationExample example = new UmsRoleMenuRelationExample();
        UmsRoleMenuRelationExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(roleId);
        roleMenuRelationMapper.deleteByExample(example);

        if(!CollectionUtils.isEmpty(menuIds)) {
            for (Long menuId : menuIds) {
                UmsRoleMenuRelation roleMenuRelation = new UmsRoleMenuRelation();
                roleMenuRelation.setRoleId(roleId);
                roleMenuRelation.setMenuId(menuId);
                roleMenuRelationMapper.insert(roleMenuRelation);
            }
        }
        return count;
    }

    @Override
    public List<UmsResource> listResource(Long id) {
        return roleDao.getResourceListByRoleId(id);
    }

    @Override
    public int allocResource(Long roleId, List<Long> resourceIds) {
        int count = resourceIds == null ? 0 : resourceIds.size();
        UmsRoleResourceRelationExample example = new UmsRoleResourceRelationExample();
        UmsRoleResourceRelationExample.Criteria criteria = example.createCriteria();
        criteria.andRoleIdEqualTo(roleId);
        roleResourceRelationMapper.deleteByExample(example);
        if(!CollectionUtils.isEmpty(resourceIds)) {
            for (Long resourceId : resourceIds) {
                UmsRoleResourceRelation roleResourceRelation = new UmsRoleResourceRelation();
                roleResourceRelation.setRoleId(roleId);
                roleResourceRelation.setResourceId(resourceId);
                roleResourceRelationMapper.insert(roleResourceRelation);
            }
        }
        return count;
    }
}
