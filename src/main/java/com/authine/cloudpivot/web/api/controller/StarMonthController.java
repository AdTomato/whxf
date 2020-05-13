package com.authine.cloudpivot.web.api.controller;

import com.authine.cloudpivot.engine.enums.ErrCode;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.entity.StationStarMonth;
import com.authine.cloudpivot.web.api.service.StarMonthService;
import com.authine.cloudpivot.web.api.utils.DateUtil;
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
 * 每月之星controller
 *
 * @author wangyong
 * @time 2020/5/13 17:31
 */
@Api(value = "每月之星", tags = "二次开发：每月之星")
@Slf4j
@RestController
@RequestMapping("/controller/starMonth")
public class StarMonthController extends BaseController {

    @Autowired
    StarMonthService starMonthService;

    @ApiOperation(value = "根据消防站id、日期获取每月之星")
    @GetMapping("/getStationStarMonthByStationId")
    public ResponseResult<Object> getStationStarMonthByStationId(@ApiParam(value = "消防站id") String stationId, @ApiParam(value = "日期") Date date) {
        StationStarMonth stationStarMonth = starMonthService.getStationStarMonthByStationId(stationId, DateUtils.getYearMonthTime(date));

        return this.getErrResponseResult(stationStarMonth, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }

}
