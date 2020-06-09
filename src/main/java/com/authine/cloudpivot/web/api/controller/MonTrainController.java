package com.authine.cloudpivot.web.api.controller;

import com.authine.cloudpivot.engine.enums.ErrCode;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.entity.Unit;
import com.authine.cloudpivot.web.api.service.DeptService;
import com.authine.cloudpivot.web.api.service.MonthTrainService;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: weiyao
 * @time: 2020/6/8
 * @Description: 月度训练登记功能
 */
@RestController
@Slf4j
@RequestMapping("/controller/monthTrain")
public class MonTrainController extends BaseController {

    @Autowired
    MonthTrainService monthTrainService;

    /**
     * 月度训练中队统计
     */
    @GetMapping("/upsetMonthTrainCen")
    public ResponseResult<Object> upsetMonthTrainCen(String id) {
        if (StringUtils.isNotEmpty(id)) {
            String rsc=monthTrainService.upsetMonthTrainCen(id);
            return this.getErrResponseResult(rsc, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
        } else {
            return this.getErrResponseResult(null, 404L, "没有Id");
        }
    }

    /**
     * 月度训练大队统计
     */
    @GetMapping("/upsetMonthTrainBig")
    public ResponseResult<Object> upsetMonthTrainBig(@RequestParam String id) {
        if (StringUtils.isNotEmpty(id)) {
            String rsc=monthTrainService.upsetMonthTrainBig(id);
            return this.getErrResponseResult(rsc, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
        } else {
            return this.getErrResponseResult(null, 404L, "没有Id");
        }
    }


}
