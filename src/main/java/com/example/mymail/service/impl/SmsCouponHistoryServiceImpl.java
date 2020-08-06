package com.example.mymail.service.impl;

import com.example.mymail.mapper.SmsCouponHistoryMapper;
import com.example.mymail.model.SmsCouponHistory;
import com.example.mymail.model.SmsCouponHistoryExample;
import com.example.mymail.service.SmsCouponHistoryService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2020/8/3 9:39   
 */
@Service
public class SmsCouponHistoryServiceImpl implements SmsCouponHistoryService {
    @Resource
    private SmsCouponHistoryMapper couponHistoryMapper;
    @Override
    public List<SmsCouponHistory> list(Integer useStatus, String orderSn, Long couponId, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        SmsCouponHistoryExample example = new SmsCouponHistoryExample();
        SmsCouponHistoryExample.Criteria criteria = example.createCriteria();
        if(!StringUtils.isEmpty(useStatus))
            criteria.andUseStatusEqualTo(useStatus);
        if(!StringUtils.isEmpty(orderSn)){
            criteria.andOrderSnEqualTo(orderSn);
        }
        criteria.andCouponIdEqualTo(couponId);
        return couponHistoryMapper.selectByExample(example);
    }
}
