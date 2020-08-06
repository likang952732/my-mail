package com.example.mymail.service.impl;

import com.example.mymail.mapper.SmsHomeAdvertiseMapper;
import com.example.mymail.model.SmsHomeAdvertise;
import com.example.mymail.model.SmsHomeAdvertiseExample;
import com.example.mymail.service.SmsHomeAdvertiseService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2020/7/31 9:51   
 */
@Service
public class SmsHomeAdvertiseServiceImpl implements SmsHomeAdvertiseService {
    @Resource
    private SmsHomeAdvertiseMapper advertiseMapper;

    @Override
    public List<SmsHomeAdvertise> list(String name, Integer type, String endTime, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        SmsHomeAdvertiseExample example = new SmsHomeAdvertiseExample();
        SmsHomeAdvertiseExample.Criteria criteria = example.createCriteria();
        if(!StringUtils.isEmpty(name))
            criteria.andNameLike("%" + name + "%");
        if(!StringUtils.isEmpty(type))
            criteria.andTypeEqualTo(type);
        if (!StringUtils.isEmpty(endTime)) {
            String startStr = endTime + " 00:00:00";
            String endStr = endTime + " 23:59:59";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date start = null;
            try {
                start = sdf.parse(startStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date end = null;
            try {
                end = sdf.parse(endStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (start != null && end != null) {
                criteria.andEndTimeBetween(start, end);
            }
        }
        example.setOrderByClause("sort desc");
        return advertiseMapper.selectByExample(example);
    }

    @Override
    public int update(Long id, SmsHomeAdvertise advertise) {
        advertise.setId(id);
        return advertiseMapper.updateByPrimaryKey(advertise);
    }

    @Override
    public SmsHomeAdvertise getHomeAdvertiseById(Long id) {
        return advertiseMapper.selectByPrimaryKey(id);
    }

    @Override
    public int delete(List<Long> ids) {
        SmsHomeAdvertiseExample example = new SmsHomeAdvertiseExample();
        SmsHomeAdvertiseExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        return advertiseMapper.deleteByExample(example);
    }

    @Override
    public int create(SmsHomeAdvertise advertise) {
        return advertiseMapper.insert(advertise);
    }
}
