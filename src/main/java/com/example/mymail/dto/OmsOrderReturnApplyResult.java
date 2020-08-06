package com.example.mymail.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 申请信息封装
 * Created by macro on 2018/10/18.
 */
public class OmsOrderReturnApplyResult extends com.example.mymail.model.OmsOrderReturnApply {
    @Getter
    @Setter
    private com.example.mymail.model.OmsCompanyAddress companyAddress;
}
