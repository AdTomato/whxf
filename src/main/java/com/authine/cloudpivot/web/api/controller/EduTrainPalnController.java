package com.authine.cloudpivot.web.api.controller;


import com.authine.cloudpivot.engine.enums.ErrCode;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.dubbo.DubboConfigService;
import com.authine.cloudpivot.web.api.entity.StationEduTrainPaln;
import com.authine.cloudpivot.web.api.mapper.EduTrainPalnMapper;
import com.authine.cloudpivot.web.api.mapper.OrgMapper;
import com.authine.cloudpivot.web.api.service.EduTrainPalnService;
import com.authine.cloudpivot.web.api.utils.DateUtils;
import com.authine.cloudpivot.web.api.utils.UserUtils;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
/**
 * 教育训练计划控制层
 *
 * @author Ke Longhai
 * @time 2020/5/14
 */

@Api(value = "训练信息", tags = "训练信息")
@RestController
@Slf4j
@RequestMapping("/controller/Education")
public class EduTrainPalnController extends BaseController {

    @Autowired
    private EduTrainPalnService eduTrainPalnService;


    /**
     * 根据消防站id获取今日教育训练计划
     *
     * @param stationId 消防站id
     * @return 今日教育训练计划
     * @author Ke Longhai
     */
    @ApiOperation("根据消防站id获取今日教育训练安排信息")
    @GetMapping("/getStationEduTrainPalnByStationId")
    public ResponseResult<Object> getStationEduTrainPalnByStationId(@RequestParam String stationId) {
        String userId = UserUtils.getUserId(this.getUserId());

        userId = "2c9280a26706a73a016706a93ccf002b";

       /* Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String todayDate = sdf.format(d);
        System.out.println("格式化后的日期：" + todayDate);*/
        StationEduTrainPaln eduTrainPaln = eduTrainPalnService.getStationEduTrainPalnByStationId(stationId, userId);
        return this.getErrResponseResult(eduTrainPaln, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }

    /**
     * 更新消防站的今日教育训练计划
     *
     * @param eduTrainPaln 今日教育训练计划
     * @author Ke Longhai
     */
    @ApiOperation("更新今日教育安排信息")
    @PutMapping("/updateStationEduTrainPalnByStationId")
    public ResponseResult<Object> updateStationAlertInfoByStationId(@RequestBody StationEduTrainPaln eduTrainPaln) {
        eduTrainPalnService.updateStationEduTrainPalnByStationId(eduTrainPaln);
        return this.getErrResponseResult("更新成功", ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }


}



