package com.example.mymail.controller;
import com.example.mymail.common.api.CommonPage;
import com.example.mymail.common.api.CommonResult;
import com.example.mymail.model.OmsOrderReturnReason;
import com.example.mymail.service.OmsOrderReturnReasonService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/*
 @Description 退货原因管理
 *@author kang.li
 *@date 2020/8/3 17:00   
 */
@RestController
@RequestMapping("/returnReason")
public class OmsOrderReturnReasonController {
    @Autowired
    private OmsOrderReturnReasonService omsOrderReturnReasonService;

    @ApiOperation("分页查询")
    @GetMapping("/list")
    public CommonResult<CommonPage<OmsOrderReturnReason>> list(@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<OmsOrderReturnReason> list = omsOrderReturnReasonService.list(pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @ApiOperation("获取退货原因")
    @GetMapping("/{id}")
    public CommonResult<OmsOrderReturnReason> getById(@PathVariable("id")Long id) {
        OmsOrderReturnReason orderReturnReason = omsOrderReturnReasonService.getById(id);
        return CommonResult.success(orderReturnReason);
    }


    @ApiOperation("添加")
    @PostMapping("/create")
    public CommonResult create(@RequestBody OmsOrderReturnReason omsOrderReturnReason) {
        int count = omsOrderReturnReasonService.create(omsOrderReturnReason);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("")
    @PostMapping("/update/status")
    public CommonResult updateStatus(@RequestParam("ids")Long ids, @RequestParam("status")Integer status) {
        OmsOrderReturnReason orderReturnReason = new OmsOrderReturnReason();
        orderReturnReason.setStatus(status);
        int count = omsOrderReturnReasonService.updateStatus(ids, orderReturnReason);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("")
    @PostMapping("/update/{id}")
    public CommonResult updateStatus(@PathVariable("id")Long id, @RequestBody OmsOrderReturnReason orderReturnReason ) {
        int count = omsOrderReturnReasonService.updateStatus(id, orderReturnReason);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("删除")
    @PostMapping("/delete")
    public CommonResult delete(@RequestParam("ids")Long ids) {
        int count = omsOrderReturnReasonService.delete(ids);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }
}
