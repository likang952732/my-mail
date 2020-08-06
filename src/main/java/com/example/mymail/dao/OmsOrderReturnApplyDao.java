package com.example.mymail.dao;

import com.example.mymail.dto.OmsReturnApplyQueryParam;
import com.example.mymail.model.OmsOrderReturnApply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2020/8/3 15:15   
 */
public interface OmsOrderReturnApplyDao {
    List<OmsOrderReturnApply> list(@Param("queryParam") OmsReturnApplyQueryParam queryParam);
}
