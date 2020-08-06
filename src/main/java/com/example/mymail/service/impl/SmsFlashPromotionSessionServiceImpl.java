package com.example.mymail.service.impl;

import com.example.mymail.mapper.SmsFlashPromotionSessionMapper;
import com.example.mymail.model.SmsFlashPromotionSession;
import com.example.mymail.model.SmsFlashPromotionSessionExample;
import com.example.mymail.service.SmsFlashPromotionSessionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2020/8/3 10:27   
 */
@Service
public class SmsFlashPromotionSessionServiceImpl implements SmsFlashPromotionSessionService {
    @Resource
    private SmsFlashPromotionSessionMapper flashPromotionSessionMapper;
    @Override
    public SmsFlashPromotionSession getById(Long flashPromotionId) {
        return flashPromotionSessionMapper.selectByPrimaryKey(flashPromotionId);
    }

    @Override
    public List<SmsFlashPromotionSession> list() {
        SmsFlashPromotionSessionExample example = new SmsFlashPromotionSessionExample();
        return flashPromotionSessionMapper.selectByExample(example);
    }

    @Override
    public int create(SmsFlashPromotionSession flashPromotionSession) {
        return flashPromotionSessionMapper.insert(flashPromotionSession);
    }

    @Override
    public int update(Long id, SmsFlashPromotionSession flashPromotionSession) {
        flashPromotionSession.setId(id);
        return flashPromotionSessionMapper.updateByPrimaryKeySelective(flashPromotionSession);
    }

    @Override
    public int delete(Long id) {
        return flashPromotionSessionMapper.deleteByPrimaryKey(id);
    }
}
