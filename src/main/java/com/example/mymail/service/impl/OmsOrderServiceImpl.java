package com.example.mymail.service.impl;

import com.example.mymail.dao.OmsOrderDao;
import com.example.mymail.dto.OmsOrderQueryParam;
import com.example.mymail.mapper.OmsOrderMapper;
import com.example.mymail.model.OmsOrder;
import com.example.mymail.service.OmsOrderService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2020/8/3 11:19   
 */
@Service
public class OmsOrderServiceImpl implements OmsOrderService {
    @Resource
    private OmsOrderDao orderDao;

    @Resource
    private OmsOrderMapper orderMapper;


    @Override
    public List<OmsOrder> list(OmsOrderQueryParam queryParam, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        return orderDao.list(queryParam);
    }

    @Override
    public OmsOrder getById(Long id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(Long id, OmsOrder order) {
        order.setId(id);
        return orderMapper.updateByPrimaryKeySelective(order);
    }
}
