package com.example.mymail.controller;

import com.example.mymail.common.api.CommonPage;
import com.example.mymail.common.api.CommonResult;
import com.example.mymail.model.CmsSubject;
import com.example.mymail.service.CmsSubjectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
 @Description 商品专题管理
 *@author kang.li
 *@date 2020/7/31 11:30   
 */
@RestController
@RequestMapping("/subject")
public class CmsSubjectController {
    @Autowired
    private CmsSubjectService cmsSubjectService;

    @ApiOperation("分页查询专题")
    @GetMapping("/list")
    public CommonResult<CommonPage<CmsSubject>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                     @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize,
                                                     @RequestParam(value = "pageNum", defaultValue = "1")Integer pageNum) {
        List<CmsSubject> list = cmsSubjectService.list(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @ApiOperation("查询所有专题")
    @GetMapping("/listAll")
    public CommonResult<List<CmsSubject>> listAll(){
        List<CmsSubject> list = cmsSubjectService.listAll();
        return CommonResult.success(list);
    }


}
