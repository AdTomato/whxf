package com.authine.cloudpivot.web.api.controller;

import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.entity.ScaleTestAcore;
import com.authine.cloudpivot.web.api.service.ScaleTestResultService;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: weiyao
 * @Date: 2020-08-01
 * @Description: 武汉心里测评结果
 */
@RestController
@RequestMapping("/controller/scaleResult")
public class ScaleTestResultController extends BaseController {

    @Autowired
    ScaleTestResultService scaleTestResultService;


    //weiyao 抑郁 修改或新增测评结果
    @GetMapping("/getResultByScore")
    public ResponseResult<String> getResultByScore(@RequestParam String parentId,  @RequestParam Integer score) {

        if(StringUtils.isNotEmpty(parentId) && score!=null){
            String res=scaleTestResultService.getResultByScore(parentId,score);
            return this.getOkResponseResult("success", res);
        }else{
            return this.getErrResponseResult(null, 404L, "参数错误");
        }
    }

    //weiyao 插入量化测评详情结果
    @GetMapping("/insertScaleTestAcore")
    public ResponseResult<String> insertScaleTestAcore(@RequestBody ScaleTestAcore scaleTestAcore) {

        if(scaleTestAcore!=null && scaleTestAcore.getUserId()!=null){
            String res=scaleTestResultService.insertScaleTestAcore(scaleTestAcore);
            return this.getOkResponseResult("success", res);
        }else{
            return this.getErrResponseResult(null, 404L, "参数错误");
        }
    }



}
