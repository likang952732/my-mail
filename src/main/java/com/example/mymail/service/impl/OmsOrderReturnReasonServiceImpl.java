package com.example.mymail.service.impl;

import com.example.mymail.mapper.OmsOrderReturnReasonMapper;
import com.example.mymail.model.OmsOrderReturnReason;
import com.example.mymail.model.OmsOrderReturnReasonExample;
import com.example.mymail.service.OmsOrderReturnReasonService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2020/8/3 17:06   
 */
@Service
public class OmsOrderReturnReasonServiceImpl implements OmsOrderReturnReasonService {
    @Resource
    private OmsOrderReturnReasonMapper orderReturnReasonMapper;

    @Override
    public List<OmsOrderReturnReason> list(Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        OmsOrderReturnReasonExample example = new OmsOrderReturnReasonExample();
        return orderReturnReasonMapper.selectByExample(example);
    }

    @Override
    public int create(OmsOrderReturnReason orderReturnReason) {
        orderReturnReason.setCreateTime(new Date());
        return orderReturnReasonMapper.insert(orderReturnReason);
    }

    @Override
    public int updateStatus(Long id, OmsOrderReturnReason orderReturnReason) {
        orderReturnReason.setId(id);
        return orderReturnReasonMapper.updateByPrimaryKeySelective(orderReturnReason);
    }

    @Override
    public OmsOrderReturnReason getById(Long id) {
        return orderReturnReasonMapper.selectByPrimaryKey(id);
    }

    @Override
    public int delete(Long id) {
        return orderReturnReasonMapper.deleteByPrimaryKey(id);
    }
}
