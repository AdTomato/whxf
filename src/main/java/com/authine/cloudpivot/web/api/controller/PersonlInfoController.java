package com.authine.cloudpivot.web.api.controller;

import com.authine.cloudpivot.engine.enums.ErrCode;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.entity.PersonlInfo;
import com.authine.cloudpivot.web.api.entity.TwoRandownInfo;
import com.authine.cloudpivot.web.api.mapper.TwoRandownMapper;
import com.authine.cloudpivot.web.api.service.DeptService;
import com.authine.cloudpivot.web.api.service.PersonlInfoService;
import com.authine.cloudpivot.web.api.utils.DateUtil;
import com.authine.cloudpivot.web.api.utils.DingDingUtil;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import com.dingtalk.api.response.OapiAttendanceScheduleListbydayResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author: weiyao
 * @time: 2020/5/11
 * @Description: 武汉消防 大屏功能 人员动态模块信息
 */
@RestController
@Slf4j
@RequestMapping("/controller/personlInfo")
public class PersonlInfoController extends BaseController {

    @Autowired
    PersonlInfoService personlInfoService;

    @Resource
    TwoRandownMapper twoRandownMapper;

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

    /**
     * 返回大屏人员(大队)动态信息
     */
    @GetMapping("/getTeamInfo")
    public ResponseResult<Object> getTeamUserById(String sourceId) {
        long startTime=System.currentTimeMillis();   //获取开始时间
        if(StringUtils.isNotEmpty(sourceId)){
            PersonlInfo person=personlInfoService.getTeamPersonlInfo(sourceId);
            long endTime=System.currentTimeMillis(); //获取结束时间
            log.info(">>>>>>>>>>>>>>>>>>>>大屏大队运行时间>>>>>>>>>>>>>>>>>>>： "+(endTime-startTime)/1000+" 秒");
            return this.getErrResponseResult(person, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
        }else{
            return this.getErrResponseResult(null, 404L, "没有部门Id");
        }

    }

    /**
     * 返回大屏==双随机公开数据
     */
    @GetMapping("/getTwoRandownInfo")
    public ResponseResult<Object> getTwoRandownInfo(String brigadeId) {
        if(StringUtils.isNotEmpty(brigadeId)){
            TwoRandownInfo info=twoRandownMapper.getTwoRandownInfo(brigadeId);
            return this.getErrResponseResult(info, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
        }else{
            return this.getErrResponseResult(null, 404L, "没有大队Id");
        }

    }

    /**
     * 返回大屏==修改双随机公开数据
     */
    @GetMapping("/updateTwoRandownInfo")
    public ResponseResult<Object> updateTwoRandownInfo(@RequestBody TwoRandownInfo info) {
        if(StringUtils.isNotEmpty(info.getId())){
            twoRandownMapper.updateTwoRandownInfo(info);
            return this.getErrResponseResult(info, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
        }else{
            return this.getErrResponseResult(null, 404L, "没有Id");
        }

    }



}
