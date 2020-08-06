package com.example.mymail.service.impl;

import com.example.mymail.mapper.SmsHomeRecommendSubjectMapper;
import com.example.mymail.model.SmsHomeRecommendSubject;
import com.example.mymail.model.SmsHomeRecommendSubjectExample;
import com.example.mymail.service.SmsHomeRecommendSubjectService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2020/7/31 10:58   
 */
@Service
public class SmsHomeRecommendSubjectServiceImpl implements SmsHomeRecommendSubjectService {
    @Resource
    private SmsHomeRecommendSubjectMapper recommendSubjectMapper;

    @Override
    public List<SmsHomeRecommendSubject> list(String name, Integer status, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        SmsHomeRecommendSubjectExample example = new SmsHomeRecommendSubjectExample();
        SmsHomeRecommendSubjectExample.Criteria criteria = example.createCriteria();
        if(!StringUtils.isEmpty(name))
            criteria.andSubjectNameLike("%" + name + "%");
        if(!StringUtils.isEmpty(status))
            criteria.andRecommendStatusEqualTo(status);
        return recommendSubjectMapper.selectByExample(example);
    }

    @Override
    public int update(Long id, SmsHomeRecommendSubject subject) {
        subject.setId(id);
        return recommendSubjectMapper.updateByPrimaryKey(subject);
    }

    @Override
    public int delete(Long id) {
        return recommendSubjectMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int create(List<SmsHomeRecommendSubject> recommendSubjectList) {
        int count = recommendSubjectList == null ? 0 : recommendSubjectList.size();
        for (SmsHomeRecommendSubject recommendSubject : recommendSubjectList) {
            recommendSubject.setSort(0);
            recommendSubject.setRecommendStatus(1);
            recommendSubjectMapper.insert(recommendSubject);
        }
        return count;
    }
}
