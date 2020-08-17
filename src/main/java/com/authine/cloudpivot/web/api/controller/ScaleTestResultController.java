package com.authine.cloudpivot.web.api.controller;

import com.authine.cloudpivot.engine.enums.ErrCode;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.entity.ScaleConsultDetail;
import com.authine.cloudpivot.web.api.entity.ScaleTestAcore;
import com.authine.cloudpivot.web.api.entity.TeamRecord;
import com.authine.cloudpivot.web.api.mapper.ScaleTestResultMapper;
import com.authine.cloudpivot.web.api.service.ScaleTestResultService;
import com.authine.cloudpivot.web.api.utils.DingDingUtil;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import com.dingtalk.api.response.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
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

    @Resource
    ScaleTestResultMapper scaleTestResultMapper;


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

        if(scaleTestAcore!=null && StringUtils.isNotEmpty(scaleTestAcore.getUserId())){
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

    //发送预约待办
    @GetMapping("/sendWorkRecord")
    public ResponseResult<Object> sendWorkRecord(@RequestParam String sendId,@RequestParam String acceptId) {

        if(StringUtils.isNotEmpty(sendId) || StringUtils.isNotEmpty(acceptId) ){
           Map<String,String> map= scaleTestResultMapper.getsendUserInfo(sendId);
           String mess="来自 "+map.get("deptName") +"部门;号码为 "+map.get("mobile") +";姓名为 "+map.get("name") +
           " 对您发起心理预约";
          //  OapiWorkrecordAddResponse rsp= DingDingUtil.sendWorkRecord("张静","我下午三点的预约","19431116101255531");
            OapiMessageCorpconversationAsyncsendV2Response rsp=  DingDingUtil.sendMessage(acceptId,DingDingUtil.getToken(),mess);
            return this.getErrResponseResult(rsp.getErrmsg(), ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
        }else{
            return this.getErrResponseResult(null, 404L, "缺少用户参数");
        }

    }

    //获取用户性别，出生，身份证号码
    @GetMapping("/getUserInfoById")
    public ResponseResult<Object> getUserInfoById(@RequestParam String userId) {

        if(StringUtils.isNotEmpty(userId) ){
           String ddUserId= scaleTestResultMapper.getDdIdByUserId(userId);
           //生日，性别，身份证号码
            OapiSmartworkHrmEmployeeListResponse us = DingDingUtil.getEmployeeInfo(ddUserId, DingDingUtil.getToken(),"sys02-birthTime,sys02-sexType,sys02-certNo");
            List<OapiSmartworkHrmEmployeeListResponse.EmpFieldInfoVO> result = us.getResult();
            String birthday="";
            Map<String,String> map=new HashMap<>();
            if (result.get(0).getFieldList().size() > 0) {
                List<OapiSmartworkHrmEmployeeListResponse.EmpFieldVO>  lis=result.get(0).getFieldList();
                for(OapiSmartworkHrmEmployeeListResponse.EmpFieldVO li :lis){
                    //sys02-birthTime,sys02-sexType,sys02-certNo
                    if("sys02-birthTime".equals(li.getFieldCode())){
                        map.put("birthday",li.getLabel());
                    }else if("sys02-sexType".equals(li.getFieldCode())) {
                        map.put("sex", li.getLabel());
                    }else if("sys02-certNo".equals(li.getFieldCode())) {
                        map.put("cardNo", li.getLabel());
                    }
                }

            }

            return this.getErrResponseResult(map, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
        }else{
            return this.getErrResponseResult(null, 404L, "缺少用户参数");
        }

    }

}
