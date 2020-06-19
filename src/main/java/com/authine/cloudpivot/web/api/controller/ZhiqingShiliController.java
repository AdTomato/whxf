package com.authine.cloudpivot.web.api.controller;


import com.authine.cloudpivot.engine.enums.ErrCode;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.entity.QuantiAssessment;
import com.authine.cloudpivot.web.api.entity.QuantiAssessmentMonthInfoList;
import com.authine.cloudpivot.web.api.entity.Zhiqingshili;
import com.authine.cloudpivot.web.api.service.QuantiAssessmentService;
import com.authine.cloudpivot.web.api.service.ZhiqingShiliService;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: weiyao
 * @time: 2020/6/19
 * @Description: 武汉消防 执勤实力
 */
@RestController
@Slf4j
@RequestMapping("/controller/zhiqingshili")
public class ZhiqingShiliController extends BaseController {

    @Resource
    ZhiqingShiliService zhiqingShiliService;

    @ApiOperation("根据部门id获取执勤实力信息")
    @GetMapping("/getZhiqinshiliInfo")
    public ResponseResult<Object> getZhiqinshiliInfo(@RequestParam String deptId) {
        if(StringUtils.isNotEmpty(deptId)){
            deptId="[{\"id\":\""+ deptId  +"\",\"type\":1}]";
            Zhiqingshili  entity= zhiqingShiliService.getZhiqingshiliInfo(deptId);
            if(entity==null){
                entity=new Zhiqingshili();
            }
            return this.getErrResponseResult(entity, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
        }else{
            return this.getErrResponseResult(null, 404L, "没有部门Id");
        }
    }

}
