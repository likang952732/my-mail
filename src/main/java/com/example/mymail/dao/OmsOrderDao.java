package com.example.mymail.dao;

import com.example.mymail.dto.OmsOrderQueryParam;
import com.example.mymail.model.OmsOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2020/8/3 11:25   
 */
public interface OmsOrderDao {
    List<OmsOrder> list(@Param("queryParam") OmsOrderQueryParam queryParam);
}
