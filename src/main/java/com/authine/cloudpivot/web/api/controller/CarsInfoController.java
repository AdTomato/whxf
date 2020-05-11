package com.authine.cloudpivot.web.api.controller;

import com.authine.cloudpivot.engine.enums.ErrCode;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.dto.VehicleInfoDto;
import com.authine.cloudpivot.web.api.service.CarsInfoService;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 车辆信息控制层
 *
 * @author wangyong
 * @time 2020/5/11 10:39
 */

@Api(value = "车辆信息接口", tags = "车辆信息接口")
@RestController
@Slf4j
@RequestMapping("/controller/carsInfo")
public class CarsInfoController extends BaseController {

    @Autowired
    CarsInfoService carsInfoService;

    /**
     * 根据消防站的id获取该消防站的所有车辆信息
     *
     * @param stationId 消防站id
     * @return 消防站所有车辆信息
     * @author wangyong
     */
    @ApiOperation(value = "查询消防站所有车辆信息接口")
    @GetMapping("/getCarsInfos")
    public ResponseResult<Object> getCarsInfos(@RequestParam String stationId) {

        VehicleInfoDto carsInfos = carsInfoService.getCarsInfosByStationId(stationId);

        return this.getErrResponseResult(carsInfos, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }

    /**
     * 根据id更新消防车的状态
     *
     * @param carsId 消防车id
     * @param status 消防车状态 在位 保修 出动
     * @return 更新结果
     * @author wangyong
     */
    @ApiOperation(value = "更新消防车辆的状态")
    @PutMapping("/updateCarsStatus")
    public ResponseResult<Object> updateCarsStatus(@RequestParam String carsId, @RequestParam String status) {
        carsInfoService.updateCarsStatusById(carsId, status);
        return this.getErrResponseResult("更新成功", ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }

}
