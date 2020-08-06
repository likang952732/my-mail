package com.example.mymail.service.impl;

import com.example.mymail.mapper.SmsFlashPromotionProductRelationMapper;
import com.example.mymail.model.SmsFlashPromotionProductRelation;
import com.example.mymail.model.SmsFlashPromotionProductRelationExample;
import com.example.mymail.service.SmsFlashPromotionProductRelationService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2020/8/3 10:42   
 */
@Service
public class SmsFlashPromotionProductRelationServiceImpl implements SmsFlashPromotionProductRelationService {
    @Resource
    private SmsFlashPromotionProductRelationMapper flashPromotionProductRelationMapper;
    @Override
    public List<SmsFlashPromotionProductRelation> list(Long flashPromotionId, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        SmsFlashPromotionProductRelationExample example = new SmsFlashPromotionProductRelationExample();
        SmsFlashPromotionProductRelationExample.Criteria criteria = example.createCriteria();
        return flashPromotionProductRelationMapper.selectByExample(example);
    }
}
