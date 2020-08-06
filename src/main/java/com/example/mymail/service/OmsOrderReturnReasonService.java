package com.example.mymail.service;

import com.example.mymail.model.OmsOrderReturnReason;

import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2020/8/3 17:05   
 */
public interface OmsOrderReturnReasonService {
    List<OmsOrderReturnReason> list(Integer pageSize, Integer pageNum);

    int create(OmsOrderReturnReason orderReturnReason);

    int updateStatus(Long ids, OmsOrderReturnReason orderReturnReason);

    OmsOrderReturnReason getById(Long id);

    int delete(Long id);
}
