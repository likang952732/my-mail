package com.example.mymail.service;

import com.example.mymail.dto.OmsOrderQueryParam;
import com.example.mymail.model.OmsOrder;

import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2020/8/3 11:19   
 */
public interface OmsOrderService {
    List<OmsOrder> list(OmsOrderQueryParam queryParam, Integer pageSize, Integer pageNum);

    OmsOrder getById(Long id);

    int update(Long id, OmsOrder order);
}
