package com.example.mymail.dao;

import com.example.mymail.model.PmsMemberPrice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/*
 @Description 自定义会员价格
 *@author kang.li
 *@date 2020/8/4 13:52   
 */
public interface PmsMemberPriceDao {
    /**
     * 批量创建
     */
    int insertList(@Param("list") List<PmsMemberPrice> memberPriceList);
}
