package com.example.mymail.service.impl;

import com.example.mymail.dto.UmsMenuNode;
import com.example.mymail.mapper.UmsMenuMapper;
import com.example.mymail.model.UmsMenu;
import com.example.mymail.model.UmsMenuExample;
import com.example.mymail.service.UmsMenuService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/*
 @Description
 *@author kang.li
 *@date 2020/7/30 14:54   
 */
@Service
public class UmsMenuServiceImpl implements UmsMenuService {
    @Resource
    private UmsMenuMapper menuMapper;

    @Override
    public List<UmsMenu> list(Long parentId, String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        UmsMenuExample example = new UmsMenuExample();
        UmsMenuExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        if(!StringUtils.isEmpty(keyword))
            criteria.andNameLike(keyword);
        return menuMapper.selectByExample(example);
    }

    @Override
    public int create(UmsMenu menu) {
        return menuMapper.insert(menu);
    }

    @Override
    public int delete(Long menuId) {
        return menuMapper.deleteByPrimaryKey(menuId);
    }

    @Override
    public int update(Long menuId, UmsMenu menu) {
        menu.setId(menuId);
        return menuMapper.updateByPrimaryKey(menu);
    }

    @Override
    public UmsMenu getMenuById(Long menuId) {
        return menuMapper.selectByPrimaryKey(menuId);
    }

    @Override
    public List<UmsMenuNode> treeList() {
        List<UmsMenu> menuList = menuMapper.selectByExample(new UmsMenuExample());
        List<UmsMenuNode> result = menuList.stream()
                .filter(menu -> menu.getParentId() == 0)
                .map(menu ->covertMenuNode(menu, menuList))
                .collect(Collectors.toList());
        return result;
    }

    private UmsMenuNode covertMenuNode(UmsMenu menu, List<UmsMenu> menuList) {
        UmsMenuNode node = new UmsMenuNode();
        BeanUtils.copyProperties(menu, node);
        List<UmsMenuNode> childrens = menuList.stream()
                    .filter(subMenu ->menu.getId().equals(subMenu.getParentId()))
                    .map(subMenu -> covertMenuNode(subMenu, menuList))
                    .collect(Collectors.toList());
        node.setChildren(childrens);
        return node;
    }
}
