package com.example.mymail.service.impl;

import com.example.mymail.dto.PmsProductAttributeParam;
import com.example.mymail.mapper.PmsProductAttributeCategoryMapper;
import com.example.mymail.mapper.PmsProductAttributeMapper;
import com.example.mymail.model.PmsProductAttribute;
import com.example.mymail.model.PmsProductAttributeCategory;
import com.example.mymail.model.PmsProductAttributeExample;
import com.example.mymail.service.PmsProductAttributeService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2020/8/4 10:00   
 */
@Service
public class PmsProductAttributeServiceImpl implements PmsProductAttributeService {
    @Resource
    private PmsProductAttributeMapper productAttributeMapper;
    @Resource
    private PmsProductAttributeCategoryMapper productAttributeCategoryMapper;
    @Override
    public List<PmsProductAttribute> getList(Long cid, Integer type, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        PmsProductAttributeExample example = new PmsProductAttributeExample();
        PmsProductAttributeExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("sort desc");
        example.createCriteria().andProductAttributeCategoryIdEqualTo(cid);
        criteria.andTypeEqualTo(type);
        return productAttributeMapper.selectByExample(example);
    }

    @Override
    public PmsProductAttribute getById(Long id) {
        return productAttributeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int create(PmsProductAttributeParam productAttributeParam) {
        PmsProductAttribute pmsProductAttribute = new PmsProductAttribute();
        BeanUtils.copyProperties(productAttributeParam, pmsProductAttribute);
        int count = productAttributeMapper.insertSelective(pmsProductAttribute);
        //新增商品属性以后需要更新商品属性分类数量
        PmsProductAttributeCategory pmsProductAttributeCategory = productAttributeCategoryMapper.selectByPrimaryKey(pmsProductAttribute.getProductAttributeCategoryId());
        if(pmsProductAttribute.getType()==0){
            pmsProductAttributeCategory.setAttributeCount(pmsProductAttributeCategory.getAttributeCount()+1);
        }else if(pmsProductAttribute.getType()==1){
            pmsProductAttributeCategory.setParamCount(pmsProductAttributeCategory.getParamCount()+1);
        }
        productAttributeCategoryMapper.updateByPrimaryKey(pmsProductAttributeCategory);
        return count;
    }

    @Override
    public int update(Long id, PmsProductAttribute productAttribute) {
        productAttribute.setId(id);
        return productAttributeMapper.updateByPrimaryKeySelective(productAttribute);
    }

    @Override
    public int delete(Long id) {
        return productAttributeMapper.deleteByPrimaryKey(id);
    }
}
