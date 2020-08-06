package com.example.mymail.service.impl;

import com.example.mymail.dao.OmsOrderReturnApplyDao;
import com.example.mymail.dto.OmsReturnApplyQueryParam;
import com.example.mymail.dto.OmsUpdateStatusParam;
import com.example.mymail.mapper.OmsOrderReturnApplyMapper;
import com.example.mymail.model.OmsOrderReturnApply;
import com.example.mymail.service.OmsOrderReturnApplyService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2020/8/3 15:07   
 */
@Service
public class OmsOrderReturnApplyServiceImpl implements OmsOrderReturnApplyService {
    @Resource
    private OmsOrderReturnApplyDao orderReturnApplyDao;
    @Resource
    private OmsOrderReturnApplyMapper orderReturnApplyMapper;

    @Override
    public List<OmsOrderReturnApply> list(OmsReturnApplyQueryParam queryParam, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        return orderReturnApplyDao.list(queryParam);
    }

    @Override
    public OmsOrderReturnApply getById(Long id) {
        return orderReturnApplyMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateStatus(Long id, OmsUpdateStatusParam updateStatusParam) {
        OmsOrderReturnApply orderReturnApply = new OmsOrderReturnApply();
        orderReturnApply.setId(id);
        orderReturnApply.setStatus(updateStatusParam.getStatus());
        orderReturnApply.setHandleTime(new Date());
        return orderReturnApplyMapper.updateByPrimaryKeySelective(orderReturnApply);
    }
}
