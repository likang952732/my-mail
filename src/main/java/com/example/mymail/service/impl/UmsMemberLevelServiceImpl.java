package com.example.mymail.service.impl;

import com.example.mymail.mapper.UmsMemberLevelMapper;
import com.example.mymail.model.UmsMemberLevel;
import com.example.mymail.model.UmsMemberLevelExample;
import com.example.mymail.service.UmsMemberLevelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2020/8/4 10:24   
 */
@Service
public class UmsMemberLevelServiceImpl implements UmsMemberLevelService {
    @Resource
    private UmsMemberLevelMapper memberLevelMapper;
    @Override
    public List<UmsMemberLevel> getList(Integer defaultStatus) {
        UmsMemberLevelExample example = new UmsMemberLevelExample();
        UmsMemberLevelExample.Criteria criteria = example.createCriteria();
        criteria.andDefaultStatusEqualTo(defaultStatus);
        return memberLevelMapper.selectByExample(example);
    }
}
