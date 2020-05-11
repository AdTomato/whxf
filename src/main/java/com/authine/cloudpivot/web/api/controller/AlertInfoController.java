package com.authine.cloudpivot.web.api.controller;

import com.authine.cloudpivot.engine.enums.ErrCode;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.entity.AlertInfo;
import com.authine.cloudpivot.web.api.service.AlertInfoService;
import com.authine.cloudpivot.web.api.utils.DateUtils;
import com.authine.cloudpivot.web.api.utils.UserUtils;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

/**
 * @author wangyong
 * @time 2020/5/11 15:04
 */
@Api(value = "警情信息", tags = "警情信息")
@RestController
@Slf4j
@RequestMapping("/controller/alertInfo")
public class AlertInfoController extends BaseController {

    @Autowired
    AlertInfoService alertInfoService;

    @ApiOperation("根据消防站id获取今日警情信息")
    @GetMapping("/getStationAlertInfoByStationId")
    public ResponseResult<Object> getStationAlertInfoByStationId(@RequestParam String stationId, Date date) {
        String userId = UserUtils.getUserId(this.getUserId());

        userId = "2c9280a26706a73a016706a93ccf002b";

        AlertInfo alertInfo = alertInfoService.getStationAlertInfoByStationId(stationId, DateUtils.getYearMonthDateTime(date), userId);
        return this.getErrResponseResult(alertInfo, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }


    @ApiOperation("更新今日警情信息")
    @PutMapping("/updateStationAlertInfoByStationId")
    public ResponseResult<Object> updateStationAlertInfoByStationId(@RequestBody AlertInfo alertInfo) {
        alertInfoService.updateStationAlertInfoByStationId(alertInfo);
        return this.getErrResponseResult("更新成功", ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }

    @ApiOperation("获取一个大队的警情信息")
    @GetMapping("/getBrigadeAlertInfoByBrigadeId")
    public ResponseResult<Object> getBrigadeAlertInfoByBrigadeId(@RequestParam String brigadeId, @RequestParam Date date) {
        Map<String, Integer> alertInfos = alertInfoService.getBrigadeAlertInfoByBrigadeId(brigadeId, DateUtils.getYearMonthDateTime(date));
        return this.getErrResponseResult(alertInfos, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }

}
