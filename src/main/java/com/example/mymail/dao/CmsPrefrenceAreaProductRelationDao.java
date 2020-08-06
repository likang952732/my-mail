package com.example.mymail.dao;

import com.example.mymail.model.CmsPrefrenceAreaProductRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/*
 @Description 自定义优选和商品关系操作
 *@author kang.li
 *@date 2020/8/4 15:06   
 */
public interface CmsPrefrenceAreaProductRelationDao {
    /**
     * 批量创建
     */
    int insertList(@Param("list") List<CmsPrefrenceAreaProductRelation> prefrenceAreaProductRelationList);
}
