package com.authine.cloudpivot.web.api.controller;

import com.authine.cloudpivot.engine.enums.ErrCode;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.entity.Test;
import com.authine.cloudpivot.web.api.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: wangyong
 * @time: 2020/4/24 12:58
 * @Description:
 */
@RestController
@RequestMapping("/controller/testController")
public class TestController extends BaseController {

    @Autowired
    TestService testService;

    @RequestMapping("/getAllTestData")
    public Object getAllTestData() {
        List<Test> allTestData = testService.getAllTestData();
        return this.getErrResponseResult(allTestData, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }

}
