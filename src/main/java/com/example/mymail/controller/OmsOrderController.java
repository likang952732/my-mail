package com.example.mymail.controller;

import com.example.mymail.common.api.CommonPage;
import com.example.mymail.common.api.CommonResult;
import com.example.mymail.dto.OmsOrderQueryParam;
import com.example.mymail.model.OmsOrder;
import com.example.mymail.service.OmsOrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 @Description 订单管理
 *@author kang.li
 *@date 2020/8/3 11:17   
 */
@RestController
@RequestMapping("/order")
public class OmsOrderController {
    @Autowired
    private OmsOrderService orderService;

    @ApiOperation("查询订单")
    @GetMapping("/list")
    public CommonResult<CommonPage<OmsOrder>> list(OmsOrderQueryParam queryParam,
                                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<OmsOrder> orderList = orderService.list(queryParam, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(orderList));
    }

    @ApiOperation("查询订单信息")
    @GetMapping("/{id}")
    public CommonResult<OmsOrder> getById(@PathVariable("id")Long id) {
        OmsOrder order = orderService.getById(id);
        return CommonResult.success(order);
    }

    @ApiOperation("备注订单")
    @PostMapping("/update/note")
    public CommonResult update(@RequestParam("id")Long id, @RequestParam("note")String note, @RequestParam("status")Integer status) {
        OmsOrder order = new OmsOrder();
        order.setNote(note);
        order.setStatus(status);
        int count = orderService.update(id, order);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }
}
