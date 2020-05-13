package com.authine.cloudpivot.web.api.controller;

import com.authine.cloudpivot.engine.enums.ErrCode;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.entity.PersonlInfo;
import com.authine.cloudpivot.web.api.service.PersonlInfoService;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author weiyao
 * @time: 2020/5/11
 * @Description: 武汉消防 大屏功能 人员动态模块信息
 */
@RestController
@Slf4j
@RequestMapping("/controller/personlInfo")
public class PersonlInfoController extends BaseController {

    @Autowired
    PersonlInfoService personlInfoService;

    /**
     * 返回大屏人员(站)动态信息
     */
    @GetMapping("/getInfo")
    public ResponseResult<Object> getUserBydeptId(String deptId) {
        long startTime=System.currentTimeMillis();   //获取开始时间
        if(StringUtils.isNotEmpty(deptId)){
            PersonlInfo person=personlInfoService.getPersonlInfo(deptId);
            long endTime=System.currentTimeMillis(); //获取结束时间
            log.info(">>>>>>>>>>>>>>>>>>>>大屏站队运行时间>>>>>>>>>>>>>>>>>>>： "+(endTime-startTime)/1000+" 秒");
            return this.getErrResponseResult(person, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
        }else{
            return this.getErrResponseResult(null, 404L, "没有部门Id");
        }

    }



}
