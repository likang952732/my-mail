package com.example.mymail.service.impl;

import com.example.mymail.mapper.CmsSubjectMapper;
import com.example.mymail.model.CmsSubject;
import com.example.mymail.model.CmsSubjectExample;
import com.example.mymail.service.CmsSubjectService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2020/7/31 11:33   
 */
@Service
public class CmsSubjectServiceImpl implements CmsSubjectService {
    @Resource
    private CmsSubjectMapper subjectMapper;


    @Override
    public List<CmsSubject> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        CmsSubjectExample example = new CmsSubjectExample();
        CmsSubjectExample.Criteria criteria = example.createCriteria();
        if(!StringUtils.isEmpty(keyword))
            criteria.andTitleLike("%" + keyword + "%");
        return subjectMapper.selectByExample(example);
    }

    @Override
    public List<CmsSubject> listAll() {
        CmsSubjectExample example = new CmsSubjectExample();
        return subjectMapper.selectByExample(example);
    }
}
