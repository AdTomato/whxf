package com.authine.cloudpivot.web.api.controller;

import com.authine.cloudpivot.engine.enums.ErrCode;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.dto.StationDutyInfoDto;
import com.authine.cloudpivot.web.api.service.DutyInfoService;
import com.authine.cloudpivot.web.api.utils.DateUtils;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author wangyong
 * @time 2020/5/13 14:50
 */
@Api(value = "值班信息", tags = "二次开发：值班信息")
@RestController
@Slf4j
@RequestMapping("/controller/stationDutyInfo")
public class DutyInfoController extends BaseController {

    @Autowired
    DutyInfoService dutyInfoService;

    /**
     * 根据消防站id，日期获取消防站今日值班信息
     *
     * @param stationId 消防站id
     * @param date      日期
     * @return 消防站今日值班信息
     * @author wangyong
     */
    @ApiOperation(value = "根据消防站id获取消防站的今日值班信息")
    @GetMapping("/getStationDutyInfoByStationId")
    public ResponseResult<Object> getStationDutyInfoByStationId(@ApiParam(value = "消防站id") String stationId, @ApiParam(value = "日期") Date date) {
        StationDutyInfoDto stationDutyInfo = dutyInfoService.getStationDutyInfoByStationId(stationId, DateUtils.getYearMonthDateTime(date));
        return this.getErrResponseResult(stationDutyInfo, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }

}
