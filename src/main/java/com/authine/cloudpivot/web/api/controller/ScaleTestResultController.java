package com.authine.cloudpivot.web.api.controller;

import com.authine.cloudpivot.engine.enums.ErrCode;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.entity.ScaleConsultDetail;
import com.authine.cloudpivot.web.api.entity.ScaleTestAcore;
import com.authine.cloudpivot.web.api.entity.TeamRecord;
import com.authine.cloudpivot.web.api.service.ScaleTestResultService;
import com.authine.cloudpivot.web.api.utils.DingDingUtil;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import com.dingtalk.api.response.OapiUserGetuserinfoResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: weiyao
 * @Date: 2020-08-01
 * @Description: 武汉心里测评结果
 */
@RestController
@RequestMapping("/controller/scaleResult")
public class ScaleTestResultController extends BaseController {

    @Autowired
    ScaleTestResultService scaleTestResultService;


    //weiyao 根据分数查询结果
    @GetMapping("/getResultByScore")
    public ResponseResult<Object> getResultByScore(@RequestParam String parentId,  @RequestParam Integer score) {

        if(StringUtils.isNotEmpty(parentId) && score!=null){
            //结果
            //危机程度，0，正常，1，轻度，2，中度，3，重度
            List<Map<String,String>>  res=scaleTestResultService.getResultByScore(parentId,score);
            if(res !=null && res.size()>0)
            return this.getOkResponseResult(res.get(0), "succeed");
            else{
                return this.getErrResponseResult(null, 404L, "没有找到结果");
            }
        }else{
            return this.getErrResponseResult(null, 404L, "参数错误");
        }
    }

    //weiyao 插入量化测评详情结果
    @PostMapping("/insertScaleTestAcore")
    public ResponseResult<String> insertScaleTestAcore(@RequestBody ScaleTestAcore scaleTestAcore) {

        if(scaleTestAcore!=null && scaleTestAcore.getUserId()!=null){
            String res=scaleTestResultService.insertScaleTestAcore(scaleTestAcore);
            return this.getOkResponseResult("success", res);
        }else{
            return this.getErrResponseResult(null, 404L, "参数错误");
        }
    }

    //返回登录用户userId
    @GetMapping("/getUserIdByCode")
    public ResponseResult<Object> getUserIdByCode(@RequestParam String code) {

        if(StringUtils.isNotEmpty(code) ){
            OapiUserGetuserinfoResponse rsp=DingDingUtil.getUserIdByCode(code);
            return this.getErrResponseResult(rsp, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
        }else{
            return this.getErrResponseResult(null, 404L, "没有参数code");
        }
    }
    //weiyao 根据分数查询结果
    @PostMapping("/getScaleTestResultInfo")
    public ResponseResult<List<ScaleTestAcore>> getScaleTestResultInfo(@RequestBody ScaleTestAcore scaleTestAcore) {

        if(scaleTestAcore!=null){
            //结果
            //危机程度，0，正常，1，轻度，2，中度，3，重度
            List<ScaleTestAcore>  rsp=scaleTestResultService.getScaleTestResultInfo(scaleTestAcore);
                return this.getOkResponseResult(rsp, "succeed");

        }else{
            return this.getErrResponseResult(null, 404L, "参数错误");
        }
    }

    //weiyao 查询档案信息
    @GetMapping("/getScaleConsultDetail")
    public ResponseResult<List<ScaleConsultDetail>> getScaleConsultDetail(@RequestParam String deptId,  @RequestParam String userId) {

            List<ScaleConsultDetail>  rsp=scaleTestResultService.getScaleConsultDetail(deptId,userId);
            return this.getOkResponseResult(rsp, "succeed");

    }

    //weiyao 查询部门测评人数 档案
    @GetMapping("/getDeptNumInfo")
    public ResponseResult<Object> getDeptNumInfo(@RequestParam String deptId) {

        if(StringUtils.isNotEmpty(deptId) ){
            TeamRecord rsp=scaleTestResultService.getDeptNumInfo(deptId);
            return this.getErrResponseResult(rsp, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
        }else{
            return this.getErrResponseResult(null, 404L, "没有部门参数");
        }

    }

}
