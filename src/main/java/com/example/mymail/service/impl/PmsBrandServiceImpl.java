package com.example.mymail.service.impl;

import com.example.mymail.mapper.PmsBrandMapper;
import com.example.mymail.model.PmsBrand;
import com.example.mymail.model.PmsBrandExample;
import com.example.mymail.service.PmsBrandService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2020/7/31 16:19   
 */
@Service
public class PmsBrandServiceImpl implements PmsBrandService {
    @Resource
    private PmsBrandMapper brandMapper;
    @Override
    public List<PmsBrand> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        PmsBrandExample example = new PmsBrandExample();
        PmsBrandExample.Criteria criteria = example.createCriteria();
        if(!StringUtils.isEmpty(keyword))
            criteria.andNameLike("%" + keyword + "%");
        return brandMapper.selectByExample(example);
    }

    @Override
    public int updateFactoryStatu(Long id, PmsBrand brand) {
        brand.setId(id);
        return brandMapper.updateByPrimaryKeySelective(brand);
    }

    @Override
    public int updateShowStatus(Long id, PmsBrand brand) {
        brand.setId(id);
        return brandMapper.updateByPrimaryKeySelective(brand);
    }

    @Override
    public int update(Long id, PmsBrand brand) {
        brand.setId(id);
        return brandMapper.updateByPrimaryKeySelective(brand);
    }

    @Override
    public PmsBrand getById(Long id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    @Override
    public int create(PmsBrand brand) {
        return brandMapper.insert(brand);
    }
}
