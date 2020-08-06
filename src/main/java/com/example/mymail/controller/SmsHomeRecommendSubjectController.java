package com.example.mymail.controller;

import com.example.mymail.common.api.CommonPage;
import com.example.mymail.common.api.CommonResult;
import com.example.mymail.model.SmsHomeRecommendSubject;
import com.example.mymail.service.SmsHomeRecommendSubjectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 @Description 首页专题推荐管理
 *@author kang.li
 *@date 2020/7/31 10:56   
 */
@RestController
@RequestMapping("/home/recommendSubject")
public class SmsHomeRecommendSubjectController {
    @Autowired
    private SmsHomeRecommendSubjectService homeRecommendSubjectService;

    @ApiOperation("分页查询专题推荐")
    @GetMapping("/list")
    public CommonResult<CommonPage<SmsHomeRecommendSubject>> list(@RequestParam(value = "subjectName", required = false) String subjectName,
                                                                  @RequestParam(value = "recommendStatus", required = false) Integer recommendStatus,
                                                                  @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize,
                                                                  @RequestParam(value = "pageNum", defaultValue = "1")Integer pageNum) {
        List<SmsHomeRecommendSubject> list = homeRecommendSubjectService.list(subjectName, recommendStatus, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @ApiOperation("添加专题推荐")
    @PostMapping("/create")
    public CommonResult create(@RequestBody List<SmsHomeRecommendSubject> recommendSubjectList) {
        int count = homeRecommendSubjectService.create(recommendSubjectList);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("修改状态")
    @PostMapping("/update/recommendStatus")
    public CommonResult recommendStatus(@RequestParam("ids") Long ids, @RequestParam("recommendStatus") Integer recommendStatus) {
        SmsHomeRecommendSubject subject = new SmsHomeRecommendSubject();
        subject.setRecommendStatus(recommendStatus);
        int count = homeRecommendSubjectService.update(ids, subject);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("修改排序")
    @PostMapping("/update/sort/{id}")
    public CommonResult updateSort(@PathVariable("id")Long id, @RequestParam("sort") Integer sort) {
        SmsHomeRecommendSubject subject = new SmsHomeRecommendSubject();
        subject.setSort(sort);
        int count = homeRecommendSubjectService.update(id, subject);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("删除专题推荐")
    @PostMapping("/delete")
    public CommonResult delete(@RequestParam("ids") Long ids) {
        int count = homeRecommendSubjectService.delete(ids);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }
}
