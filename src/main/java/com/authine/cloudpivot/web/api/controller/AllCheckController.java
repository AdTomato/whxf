package com.authine.cloudpivot.web.api.controller;

import com.authine.cloudpivot.engine.enums.ErrCode;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.entity.UserInfoByCheck;
import com.authine.cloudpivot.web.api.service.AllCheckService;
import com.authine.cloudpivot.web.api.utils.DingDingUtil;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import com.dingtalk.api.response.OapiSmartworkHrmEmployeeListResponse;
import com.dingtalk.api.response.OapiUserGetResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author: weiyao
 * @time: 2020/8/19
 * @Description: 全员考评
 */
@RestController
@Slf4j
@RequestMapping("/controller/allCheck")
public class AllCheckController extends BaseController {

    @Autowired
    AllCheckService allCheckService;

    /**
     * 查询支队，大队等部门
     */
    @GetMapping("/getDeptName")
    public ResponseResult<Object> getDeptName(String name) {
        if (StringUtils.isNotEmpty(name)) {
            List<Map<String,String>> list=allCheckService.getDeptListByName(name);
            return this.getErrResponseResult(list, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
        } else {
            return this.getErrResponseResult(null, 404L, "没有名称");
        }
    }

    /**
     * 根据部门查询用户信息
     */
    @GetMapping("/getUserByDept")
    public ResponseResult<Object> getUserByDept(String deptId) {
            List<UserInfoByCheck> list=allCheckService.getUserListByDept(deptId);
            return this.getErrResponseResult(list, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }

    /**
     * 返回用户详细信息年龄，职务
     */
    @GetMapping("/getUserDetailInfoByDd")
    public ResponseResult<Object> getUserDetailInfoByDd(String dduserId) {
        if (StringUtils.isNotEmpty(dduserId)) {
            Map<String,Object> map=new HashMap<>();
            //获取用户详情，部门信息
            OapiUserGetResponse userDetail=DingDingUtil.getUserDetail(dduserId,DingDingUtil.getToken());
            map.put("position",userDetail.getPosition());//职务
            map.put("age",userDetail.getPosition());//职务

            OapiSmartworkHrmEmployeeListResponse us = DingDingUtil.getEmployeeInfo(dduserId, DingDingUtil.getToken(),"sys02-birthTime");
            List<OapiSmartworkHrmEmployeeListResponse.EmpFieldInfoVO> result = us.getResult();
            String birthday="";
            if (result.get(0).getFieldList().size() > 0) {
                List<OapiSmartworkHrmEmployeeListResponse.EmpFieldVO>  lis=result.get(0).getFieldList();
                for(OapiSmartworkHrmEmployeeListResponse.EmpFieldVO li :lis){
                    //sys02-birthTime,sys02-sexType,sys02-certNo
                    if("sys02-birthTime".equals(li.getFieldCode())){
                        birthday=li.getLabel();
                        if(birthday !=null && birthday.length()>4){
                           int byear=Integer.parseInt(birthday.substring(0,4));
                            Calendar cal = Calendar.getInstance();
                            int  age = cal.get(Calendar.YEAR)-byear;//当前年份

                            map.put("age",age);
                        }
                    }
                }
            }

            return this.getErrResponseResult(map, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
        }else{
            return this.getErrResponseResult(null, 404L, "钉钉Id为空");
        }

    }



}
