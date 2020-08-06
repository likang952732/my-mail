package com.example.mymail.service.impl;

import com.example.mymail.mapper.OmsCompanyAddressMapper;
import com.example.mymail.model.OmsCompanyAddress;
import com.example.mymail.model.OmsCompanyAddressExample;
import com.example.mymail.service.OmsCompanyAddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/*
 @Description
 *@author kang.li
 *@date 2020/8/3 15:52   
 */
@Service
public class OmsCompanyAddressServiceImpl implements OmsCompanyAddressService {
    @Resource
    private OmsCompanyAddressMapper companyAddressMapper;

    @Override
    public List<OmsCompanyAddress> list() {
        OmsCompanyAddressExample example = new OmsCompanyAddressExample();
        return companyAddressMapper.selectByExample(example);
    }
}
