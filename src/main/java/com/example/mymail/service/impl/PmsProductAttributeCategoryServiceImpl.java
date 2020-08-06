package com.example.mymail.service.impl;

import com.example.mymail.dao.PmsProductAttributeCategoryDao;
import com.example.mymail.dto.PmsProductAttributeCategoryItem;
import com.example.mymail.mapper.PmsProductAttributeCategoryMapper;
import com.example.mymail.model.PmsProductAttributeCategory;
import com.example.mymail.model.PmsProductAttributeCategoryExample;
import com.example.mymail.service.PmsProductAttributeCategoryService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2020/8/4 10:40   
 */
@Service
public class PmsProductAttributeCategoryServiceImpl implements PmsProductAttributeCategoryService {
    @Resource
    private PmsProductAttributeCategoryMapper pmsProductAttributeCategoryMapper;
    @Resource
    private PmsProductAttributeCategoryDao productAttributeCategoryDao;
    @Override
    public List<PmsProductAttributeCategory> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        PmsProductAttributeCategoryExample example = new PmsProductAttributeCategoryExample();
        PmsProductAttributeCategoryExample.Criteria criteria = example.createCriteria();
        if(!StringUtils.isEmpty(keyword))
            criteria.andNameLike(keyword);
        return pmsProductAttributeCategoryMapper.selectByExample(example);
    }

    @Override
    public List<PmsProductAttributeCategoryItem> getListWithAttr() {
        return productAttributeCategoryDao.getListWithAttr();
    }

    @Override
    public int create(PmsProductAttributeCategory productAttributeCategory) {
        return pmsProductAttributeCategoryMapper.insert(productAttributeCategory);
    }

    @Override
    public int delete(Long id) {
        return pmsProductAttributeCategoryMapper.deleteByPrimaryKey(id);
    }

}
