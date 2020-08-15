package com.authine.cloudpivot.web.api.controller;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.authine.cloudpivot.engine.api.facade.OrganizationFacade;
import com.authine.cloudpivot.engine.api.model.organization.DepartmentModel;
import com.authine.cloudpivot.engine.api.model.organization.UserModel;
import com.authine.cloudpivot.engine.enums.ErrCode;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.dto.ScaleTestList;
import com.authine.cloudpivot.web.api.dubbo.DubboConfigService;
import com.authine.cloudpivot.web.api.entity.HOrgUser;
import com.authine.cloudpivot.web.api.entity.ServiceHotline;
import com.authine.cloudpivot.web.api.entity.UploadDanger;
import com.authine.cloudpivot.web.api.service.HOrgUserService;
import com.authine.cloudpivot.web.api.service.ServiceHotlineService;
import com.authine.cloudpivot.web.api.service.UploadDangerService;
import com.authine.cloudpivot.web.api.utils.Constant;
import com.authine.cloudpivot.web.api.utils.DataSetUtils;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @Author Ke LongHai
 * @Date 2020/8/5 12:50
 * @Version 1.0
 */
@Api(value = "服务热线信息", tags = "服务热线信息")
@RestController
@Slf4j
@RequestMapping("/controller/ServiceHotline")
public class ServiceHotlineController extends BaseController {

    @Autowired
    private ServiceHotlineService serviceHotlineService;

    @Autowired
    private UploadDangerService uploadDangerService;

    @Autowired
    private HOrgUserService hOrgUserService;

    @Autowired
    private DubboConfigService dubboConfigService;

    @ApiOperation("获取服务热线信息")
    @GetMapping("/ServiceHotlineList")
    public ResponseResult<Object> getServiceHotlineList() {

        List<ServiceHotline> serviceHotline = serviceHotlineService.getServiceHotline();
        return this.getErrResponseResult(serviceHotline, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }

    @ApiOperation("获取心理危机上报信息")
    @GetMapping("/getUploadDanger")
    public ResponseResult<Object> getUploadDangerList() {
        List<UploadDanger> uploadDanger = uploadDangerService.getUploadDanger();
        return this.getErrResponseResult(uploadDanger, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }

    @ApiOperation("上传心理危机信息")
    @PostMapping("/insertUploadDanger")
    public ResponseResult<Object> insertUploadDangerList(@RequestBody UploadDanger uploadDanger) {
        if(uploadDanger != null){


            String userId = uploadDanger.getUserId();
//        userId = "273626253926863917";
            HOrgUser hOrgUser = hOrgUserService.getHOrgUser(userId);
            String id = hOrgUser.getId();
            String name = hOrgUser.getName();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            //[{\"id\":\"2c90a43e6f457354016f5eac4ba31571\",\"type\":3}]
            UploadDanger uploadDanger1 = new UploadDanger();
            uploadDanger1.setId(UUID.randomUUID().toString().replace("-", ""));
            uploadDanger1.setName(name+df.format(new Date()));
            uploadDanger1.setCreater(id);
            uploadDanger1.setCreatedDeptId("");
            uploadDanger1.setCreatedTime(uploadDanger.createdTime);
            uploadDanger1.setOwner("");
            uploadDanger1.setOwnerDeptId("");
            uploadDanger1.setModifier("");
            uploadDanger1.setModifiedTime(uploadDanger.modifiedTime);
            uploadDanger1.setWorkflowInstanceId("");
            uploadDanger1.setSequenceNo("");
            uploadDanger1.setSequenceStatus("");
            uploadDanger1.setOwnerDeptQueryCode("");
            uploadDanger1.setUpTime(new Date());
//        uploadDanger1.setUpUser("[{\"id\":\"+id=\",\"type\":3}]");
            //[{"id":"2c90a43e6f457354016f5eac4ba31571","type":3}]
            uploadDanger1.setUpUser("[{"+'"'+"id"+'"'+":"+'"'+id +'"'+","+'"'+"type"+'"'+":3}]");
            uploadDanger1.setDetail(uploadDanger.getDetail());
//        StringBuilder sb = new StringBuilder();

            uploadDangerService.insertUploadDanger(uploadDanger1);
            return this.getErrResponseResult(null, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
        }else{
            return this.getErrResponseResult(null, 404L, "没有数据无法上传");
        }
    }
}


