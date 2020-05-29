package com.authine.cloudpivot.web.api.controller;

import com.authine.cloudpivot.engine.api.facade.OrganizationFacade;
import com.authine.cloudpivot.engine.api.model.organization.DepartmentModel;
import com.authine.cloudpivot.engine.api.model.organization.UserModel;
import com.authine.cloudpivot.engine.enums.ErrCode;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.entity.GateCarAccessRecord;
import com.authine.cloudpivot.web.api.params.PlateOperObject;
import com.authine.cloudpivot.web.api.service.GateCarService;
import com.authine.cloudpivot.web.api.utils.Constant;
import com.authine.cloudpivot.web.api.utils.DataSetUtils;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangyong
 * @time 2020/5/29 13:16
 */
@RestController
@RequestMapping("/controller/gateCar")
@Slf4j
@Api(value = "闸机车辆", tags = "二次开发：闸机车辆")
public class GateCarController extends BaseController {

    @Autowired
    GateCarService gateCarService;

    @ApiOperation(value = "插入/更新车辆信息")
    @PostMapping("/insertOrUpdateGateCarInfo")
    public ResponseResult<Object> insertOrUpdateGateCarInfo(@RequestBody List<PlateOperObject> plateOperObjectList) {
        if (plateOperObjectList == null || plateOperObjectList.isEmpty()) {
            return this.getErrResponseResult(null, 407L, "数据不能为空");
        }

        for (PlateOperObject plateOperObject : plateOperObjectList) {

        }
        return this.getErrResponseResult(null, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }

    @ApiOperation(value = "插入车辆进出记录")
    @PostMapping("/insertCarAccessRecord")
    public ResponseResult<Object> insertCarAccessRecord(@RequestBody List<GateCarAccessRecord> gateCarAccessRecordList) {
        log.info("插入车辆进出记录接口运行中");
        long startTime = System.currentTimeMillis();
        String userId = Constant.ADMIN_ID;
//        userId = "2c90a43e6efe8b04016effb119271c6f";
        OrganizationFacade organizationFacade = this.getOrganizationFacade();
        UserModel user = organizationFacade.getUserById(userId);
        DepartmentModel department = organizationFacade.getDepartment(user.getDepartmentId());
        Map<String, GateCarAccessRecord> data = new HashMap<>();
        List<String> passRecordCodeList = new ArrayList<>();
        for (GateCarAccessRecord gateCarAccessRecord : gateCarAccessRecordList) {
            if (!StringUtils.isEmpty(gateCarAccessRecord.getPassRecordCode())) {
                data.put(gateCarAccessRecord.getPassRecordCode(), gateCarAccessRecord);
                passRecordCodeList.add(gateCarAccessRecord.getPassRecordCode());
            }
        }
        List<GateCarAccessRecord> insertData = new ArrayList<>();
        passRecordCodeList = gateCarService.getPresenceGateCarAccessRecord(passRecordCodeList);
        for (String passRecordCode : data.keySet()) {
            GateCarAccessRecord record = data.get(passRecordCode);
            if (passRecordCodeList.contains(passRecordCode)) {
                continue;
            }
            DataSetUtils.setBaseData(record, user, department, passRecordCode, Constant.COMPLETED_STATUS);
            if (!StringUtils.isEmpty(record.getPassAction())) {
                record.setPassAction("0".equals(record.getPassAction()) ? "入场" : "出场");
            }
            record.setIsShow(1);
            insertData.add(record);
        }

        if (!insertData.isEmpty()) {
            gateCarService.insertGateCarAccessRecords(insertData);
        }

        long endTime = System.currentTimeMillis();
        log.info("插入车辆进出记录接口总共运行时长为=========>{}{}", (endTime - startTime), "ms");

        return this.getErrResponseResult(null, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }

}
