package com.authine.cloudpivot.web.api.controller;


import com.authine.cloudpivot.engine.enums.ErrCode;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.entity.PersonlInfo;
import com.authine.cloudpivot.web.api.entity.QuantiAssessment;
import com.authine.cloudpivot.web.api.entity.QuantiAssessmentMonthInfoList;
import com.authine.cloudpivot.web.api.entity.StationAlertInfo;
import com.authine.cloudpivot.web.api.service.QuantiAssessmentService;
import com.authine.cloudpivot.web.api.utils.DateUtils;
import com.authine.cloudpivot.web.api.utils.UserUtils;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author: weiyao
 * @time: 2020/5/14
 * @Description: 武汉消防 大屏功能 量化考勤周报
 */
@RestController
@Slf4j
@RequestMapping("/controller/quantiAssessment")
public class QuantiAssessmentController extends BaseController {

    @Resource
    QuantiAssessmentService quantiAssessmentService;

    @ApiOperation("根据消防站id获取量化考勤周报信息")
    @GetMapping("/getAssessmentInfo")
    public ResponseResult<Object> getStationAlertInfoByStationId(@RequestParam String stationId) {
        if(StringUtils.isNotEmpty(stationId)){
            List<QuantiAssessment> QuantiAssessment = quantiAssessmentService.getAssess(stationId);
            return this.getErrResponseResult(QuantiAssessment, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
        }else{
            return this.getErrResponseResult(null, 404L, "没有部门Id");
        }
    }

    @ApiOperation("更新量化考勤周报信息")
    @PutMapping("/updateQuantiAssessmentById")
    public ResponseResult<Object> updateQuantiAssessmentById(@RequestBody QuantiAssessment alertInfo) {
        quantiAssessmentService.updateQuantiAssessmentById(alertInfo);
        return this.getErrResponseResult("更新成功", ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }

    @ApiOperation("获取量化考勤周报月度详情信息")
    @GetMapping("/getAssessmentMonthDetailInfo")
    public ResponseResult<Object> getAssessmentInfoMonthDetail(@RequestParam String stationId) {
        if(StringUtils.isNotEmpty(stationId)){
            List<QuantiAssessmentMonthInfoList> list = quantiAssessmentService.getAssessMonthDetail(stationId);
            return this.getErrResponseResult(list, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
        }else{
            return this.getErrResponseResult(null, 404L, "没有部门Id");
        }
    }
}
