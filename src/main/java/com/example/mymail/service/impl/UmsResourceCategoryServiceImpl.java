package com.example.mymail.service.impl;

import com.example.mymail.mapper.UmsResourceCategoryMapper;
import com.example.mymail.model.UmsResourceCategory;
import com.example.mymail.model.UmsResourceCategoryExample;
import com.example.mymail.service.UmsResourceCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2020/7/30 16:37   
 */
@Service
public class UmsResourceCategoryServiceImpl implements UmsResourceCategoryService {
    @Resource
    private UmsResourceCategoryMapper resourceCategoryMapper;

    @Override
    public List<UmsResourceCategory> listAll() {
        return resourceCategoryMapper.selectByExample(new UmsResourceCategoryExample());
    }

    @Override
    public int update(Long id, UmsResourceCategory category) {
        category.setId(id);
        return resourceCategoryMapper.updateByPrimaryKey(category);
    }

    @Override
    public int delete(Long id) {
        return resourceCategoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int create(UmsResourceCategory category) {
        category.setCreateTime(new Date());
        return resourceCategoryMapper.insert(category);
    }
}
