package com.authine.cloudpivot.web.api.controller;

import com.authine.cloudpivot.engine.enums.ErrCode;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.dto.DetailInfoDto;
import com.authine.cloudpivot.web.api.service.TrainResultService;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 龙虎榜信息控制层
 *
 * @author Ke Longhai
 * @time 2020/5/11
 */

@Api(value = "龙虎榜信息接口", tags = "龙虎榜信息接口")
@RestController
@Slf4j
@RequestMapping("/controller/trainResult")
public class TrainResultController extends BaseController {

    @Autowired
    TrainResultService trainResultService;

    /**
     * 根据消防站的id获取该消防站的所有龙虎榜信息
     *
     * @param stationId 消防站id
     * @return 消防站所有龙虎榜信息
     * @author Ke longhai
     */
    @ApiOperation(value = "查询消防站所有龙虎榜信息接口")
    @GetMapping("/getTrainResults")
    public ResponseResult<Object> getTrainResults(@RequestParam String stationId) {


        List<DetailInfoDto> trainResults = trainResultService.getTrainResultsByStationId(stationId);

        return this.getErrResponseResult(trainResults, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }

    /**
     * 根据id更新龙虎榜的科目信息
     *
     * @param Id 成绩信息id
     * @param subject 科目
     * @return 更新结果
     * @author Ke Longhai
     */
    @ApiOperation(value = "更新龙虎榜的科目信息")
    @PutMapping("/updateSubjectsStatus")
    public ResponseResult<Object> updateSubjectsStatus(@RequestParam String Id, @RequestParam String subject) {
        trainResultService.updateSubjectsStatusById(Id, subject);
        return this.getErrResponseResult("更新成功", ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }

    @ApiOperation(value = "更新龙虎榜的成绩")
    @PutMapping("/updateResultsStatus")
    public ResponseResult<Object> updateResultStatus(@RequestParam String Id, @RequestParam String result) {
        trainResultService.updateResultsStatusById(Id, result);
        return this.getErrResponseResult("更新成功", ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }


}
