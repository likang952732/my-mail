package com.example.mymail.service.impl;

import com.example.mymail.mapper.UmsResourceMapper;
import com.example.mymail.model.UmsResourceExample;
import com.example.mymail.service.UmsResourceService;
import com.example.mymail.model.UmsResource;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2020/7/29 14:57   
 */
@Service
public class UmsResourceServiceImpl implements UmsResourceService {
    @Autowired
    private UmsResourceMapper resourceMapper;

    @Override
    public List<UmsResource> list(String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        UmsResourceExample example = new UmsResourceExample();
        UmsResourceExample.Criteria criteria = example.createCriteria();
        if(!StringUtils.isEmpty(nameKeyword))
            criteria.andNameLike("%" + nameKeyword + "%");
        if (!StringUtils.isEmpty(urlKeyword))
            criteria.andUrlLike("%" + urlKeyword + "%");
        return resourceMapper.selectByExample(example);
    }

    @Override
    public List<UmsResource> listAll() {
        return resourceMapper.selectByExample(new UmsResourceExample());
    }

    @Override
    public int update(Long id, UmsResource umsResource) {
        umsResource.setId(id);
        return resourceMapper.updateByPrimaryKey(umsResource);
    }

    @Override
    public int delete(Long id) {
        return resourceMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int create(UmsResource umsResource) {
        return resourceMapper.insert(umsResource);
    }
}
