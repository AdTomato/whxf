package com.authine.cloudpivot.web.api.controller;

import com.authine.cloudpivot.engine.enums.ErrCode;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.entity.Unit;
import com.authine.cloudpivot.web.api.service.DeptService;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: weiyao
 * @time: 2020/4/27
 * @Description: 查询部门人员
 */
@RestController
@Slf4j
@RequestMapping("/controller/dept")
public class DeptController extends BaseController {

    @Autowired
    DeptService deptService;

    /**
     * 查询部门人员
     */
    @GetMapping("/getUserBydeptId")
    public ResponseResult<Object> getUserBydeptId(String deptId) {
        if (StringUtils.isNotEmpty(deptId)) {
            List<String> userIdList = deptService.getUserBydeptId(deptId);
            return this.getErrResponseResult(userIdList, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
        } else {
            return this.getErrResponseResult(null, 404L, "没有Id");
        }
    }

    @GetMapping("/getUsersByDeptId")
    public ResponseResult<Object> getUsersByDeptId(@RequestParam String deptId) {
        List<String> userIdList = deptService.getUserBydeptId(deptId);
        if (userIdList == null || userIdList.size() == 0) {
            return this.getErrResponseResult(null, 404L, "没有查询到数据");
        }
        List<Unit> users = deptService.getUsersById(userIdList);
        return this.getErrResponseResult(users, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }


}
