package com.authine.cloudpivot.web.api.controller;

import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.service.*;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author: weiyao
 * @Date: 2020-04-26
 * @Description: 武汉心里测评结果
 */
@RestController
@RequestMapping("/controller/xinlipingce")
public class XinliPingCeController extends BaseController {

    @Autowired
    UpsetPingCeResultService upsetPingCeResultService;


    //weiyao 抑郁 修改或新增测评结果
    @GetMapping("/upsetResultBySas")
    public ResponseResult<String> upsetResultBySas( String id) {

        if(StringUtils.isNotEmpty(id)){
            String res=upsetPingCeResultService.upsetResultBySas(id);
            return this.getOkResponseResult("success", res);//测试git
        }else{
            return this.getErrResponseResult(null, 404L, "没有Id");
        }
    }

    //weiyao 焦虑 修改或新增测评结果
    @GetMapping("/upsetResultBySds")
    public ResponseResult<String> upsetResultBySds( String id) {

        if(StringUtils.isNotEmpty(id)){
            String res=upsetPingCeResultService.upsetResultBySds(id);
            return this.getOkResponseResult("success", res);
        }else{
            return this.getErrResponseResult(null, 404L, "没有Id");
        }
    }

    //weiyao 焦虑 修改或新增测评结果
    @GetMapping("/upsetScl90Result")
    public ResponseResult<String> upsetScl90Result( String id) {

        if(StringUtils.isNotEmpty(id)){
            String res=upsetPingCeResultService.upsetScl90Result(id);
            return this.getOkResponseResult("success", res);
        }else{
            return this.getErrResponseResult(null, 404L, "没有Id");
        }
    }

}
