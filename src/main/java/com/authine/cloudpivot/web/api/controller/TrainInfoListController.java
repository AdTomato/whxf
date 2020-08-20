package com.authine.cloudpivot.web.api.controller;

import com.authine.cloudpivot.engine.enums.ErrCode;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.dto.TrainInfoList;
import com.authine.cloudpivot.web.api.dubbo.DubboConfigService;
import com.authine.cloudpivot.web.api.entity.*;
import com.authine.cloudpivot.web.api.service.HOrgUserService;
import com.authine.cloudpivot.web.api.service.ServiceHotlineService;
import com.authine.cloudpivot.web.api.service.TrainInfoListService;
import com.authine.cloudpivot.web.api.service.UploadDangerService;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Author Ke LongHai
 * @Date 2020/8/5 12:50
 * @Version 1.0
 */
@Api(value = "服务热线信息", tags = "服务热线信息")
@RestController
@Slf4j
@RequestMapping("/controller/TrainInfoList")
public class TrainInfoListController extends BaseController {


    @Autowired
    private TrainInfoListService trainInfoListService;

    @ApiOperation("获取训练情况信息")
    @GetMapping("/TrainInfoList")
    public ResponseResult<Object> getTrainInfoList(String id) {
        if(StringUtils.isNotEmpty(id)){
            List<TrainInfoList> trainInfoList = trainInfoListService.getTrainInfoList(id);
            return this.getErrResponseResult(trainInfoList, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
        }else{
            return this.getErrResponseResult(null, 404L, "该Id不存在");
        }
    }

    @ApiOperation("获取工作情况信息")
    @GetMapping("/WorkInfo")
    public ResponseResult<Object> getWorkInfoList(String id) {
        if(StringUtils.isNotEmpty(id)){
            List<WorkInfo> workInfo = trainInfoListService.getWorkInfo(id);
            return this.getErrResponseResult(workInfo, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
        }else{
            return this.getErrResponseResult(null, 404L, "该Id不存在");
        }
    }
}


