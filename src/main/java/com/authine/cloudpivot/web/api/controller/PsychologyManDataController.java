package com.authine.cloudpivot.web.api.controller;

import com.authine.cloudpivot.engine.enums.ErrCode;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.entity.PsychologyManData;
import com.authine.cloudpivot.web.api.service.PsychologyManDataService;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author Ke LongHai
 * @Date 2020/8/5 12:50
 * @Version 1.0
 */
@Api(value = "心理咨询师资料信息", tags = "心理咨询师资料信息")
@RestController
@Slf4j
@RequestMapping("/controller/PsychologyManData")
public class PsychologyManDataController extends BaseController {

    @Autowired
    private PsychologyManDataService psychologyManDataService;

    @ApiOperation("获取具体心理咨询师资料信息不传参则查询所有信息")
    @GetMapping("/getPsychologyManDataId")
    public ResponseResult<Object> getPsychologyManDataList(String id) {
        if(StringUtils.isNotEmpty(id)){
            PsychologyManData psychologyManData= psychologyManDataService.getPsychologyManDataId(id);
            return this.getErrResponseResult(psychologyManData, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
        }else{
            List<PsychologyManData> psychologyManData = psychologyManDataService.getPsychologyManData(id);
            return this.getErrResponseResult(psychologyManData, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
        }
    }


}
