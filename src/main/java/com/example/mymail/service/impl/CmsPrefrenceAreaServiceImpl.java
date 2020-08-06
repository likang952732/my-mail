package com.example.mymail.service.impl;

import com.example.mymail.mapper.CmsPrefrenceAreaMapper;
import com.example.mymail.model.CmsPrefrenceArea;
import com.example.mymail.model.CmsPrefrenceAreaExample;
import com.example.mymail.service.CmsPrefrenceAreaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2020/8/4 11:03   
 */
@Service
public class CmsPrefrenceAreaServiceImpl implements CmsPrefrenceAreaService {
    @Resource
    private CmsPrefrenceAreaMapper prefrenceAreaMapper;
    @Override
    public List<CmsPrefrenceArea> listAll() {
        CmsPrefrenceAreaExample example = new CmsPrefrenceAreaExample();
        return prefrenceAreaMapper.selectByExample(example);
    }
}
