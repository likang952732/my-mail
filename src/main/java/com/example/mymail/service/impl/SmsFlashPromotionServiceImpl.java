package com.example.mymail.service.impl;

import com.example.mymail.mapper.SmsFlashPromotionMapper;
import com.example.mymail.model.SmsFlashPromotion;
import com.example.mymail.model.SmsFlashPromotionExample;
import com.example.mymail.service.SmsFlashPromotionService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2020/8/3 10:10   
 */
@Service
public class SmsFlashPromotionServiceImpl implements SmsFlashPromotionService {
    @Resource
    private SmsFlashPromotionMapper flashPromotionMapper;

    @Override
    public List<SmsFlashPromotion> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        SmsFlashPromotionExample example = new SmsFlashPromotionExample();
        SmsFlashPromotionExample.Criteria criteria = example.createCriteria();
        if(!StringUtils.isEmpty(keyword))
            criteria.andTitleLike("%" + keyword + "%");
        return flashPromotionMapper.selectByExample(example);
    }

    @Override
    public int update(Long id, SmsFlashPromotion flashPromotion) {
        flashPromotion.setId(id);
        return flashPromotionMapper.updateByPrimaryKeySelective(flashPromotion);
    }

    @Override
    public int create(SmsFlashPromotion flashPromotion) {
        return flashPromotionMapper.insert(flashPromotion);
    }

    @Override
    public int delete(Long id) {
        return flashPromotionMapper.deleteByPrimaryKey(id);
    }
}
