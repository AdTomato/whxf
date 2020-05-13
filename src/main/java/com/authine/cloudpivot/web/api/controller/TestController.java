package com.authine.cloudpivot.web.api.controller;

import com.authine.cloudpivot.engine.enums.ErrCode;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.entity.Test;
import com.authine.cloudpivot.web.api.service.OrgService;
import com.authine.cloudpivot.web.api.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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

    @Autowired
    OrgService orgService;

    @RequestMapping("/getAllTestData")
    public Object getAllTestData() {
//        List<Test> allTestData = testService.getAllTestData();
//        String id = orgService.getDetachmentIdByUserId("2c90a43e6efe8b04016effb119271c6f");
        List<Map<String, String>> id = orgService.getStationIdByUserId("2c90a43e6efe8b04016effb119271c6f");
        return this.getErrResponseResult(id, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }

}
