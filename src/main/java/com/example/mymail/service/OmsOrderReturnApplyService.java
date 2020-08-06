package com.example.mymail.service;

import com.example.mymail.dto.OmsReturnApplyQueryParam;
import com.example.mymail.dto.OmsUpdateStatusParam;
import com.example.mymail.model.OmsOrderReturnApply;

import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2020/8/3 15:06   
 */
public interface OmsOrderReturnApplyService {

    List<OmsOrderReturnApply> list(OmsReturnApplyQueryParam queryParam, Integer pageSize, Integer pageNum);

    OmsOrderReturnApply getById(Long id);

    int updateStatus(Long id, OmsUpdateStatusParam updateStatusParam);
}
