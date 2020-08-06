package com.example.mymail.controller;

import com.example.mymail.common.api.CommonPage;
import com.example.mymail.common.api.CommonResult;
import com.example.mymail.dto.OmsReturnApplyQueryParam;
import com.example.mymail.dto.OmsUpdateStatusParam;
import com.example.mymail.model.OmsOrderReturnApply;
import com.example.mymail.service.OmsOrderReturnApplyService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 @Description 订单退货申请管理
 *@author kang.li
 *@date 2020/8/3 14:46   
 */
@RestController
@RequestMapping("/returnApply")
public class OmsOrderReturnApplyController {
    @Autowired
    private OmsOrderReturnApplyService omsOrderReturnApplyService;

    @ApiOperation("分页查询订单退货申请")
    @GetMapping("/list")
    public CommonResult<CommonPage<OmsOrderReturnApply>> list(OmsReturnApplyQueryParam queryParam,
                                                              @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                              @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<OmsOrderReturnApply> list = omsOrderReturnApplyService.list(queryParam, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @ApiOperation("查询订单退货申请详情")
    @GetMapping("/{id}")
    public CommonResult<OmsOrderReturnApply> getById(@PathVariable("id")Long id){
        OmsOrderReturnApply orderReturnApply = omsOrderReturnApplyService.getById(id);
        return CommonResult.success(orderReturnApply);
    }

    @ApiOperation("修改订单退货申请状态")
    @PostMapping("update/status/{id}")
    public CommonResult updateStatu(@PathVariable("id")Long id, @RequestBody OmsUpdateStatusParam updateStatusParam) {
        int count = omsOrderReturnApplyService.updateStatus(id, updateStatusParam);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }
}
