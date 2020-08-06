package com.example.mymail.dao;

import com.example.mymail.model.PmsProductLadder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/*
 @Description 自定义阶梯价格
 *@author kang.li
 */
public interface PmsProductLadderDao {
    int insertList(@Param("list") List<PmsProductLadder> productLadderList);
}
