package com.authine.cloudpivot.web.api.controller;

import com.authine.cloudpivot.engine.enums.ErrCode;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.entity.AlertInfo;
import com.authine.cloudpivot.web.api.entity.BrigadeAlertInfo;
import com.authine.cloudpivot.web.api.entity.BrigadeAlertInfoAnalysis;
import com.authine.cloudpivot.web.api.entity.StationAlertInfo;
import com.authine.cloudpivot.web.api.service.AlertInfoService;
import com.authine.cloudpivot.web.api.utils.DateUtils;
import com.authine.cloudpivot.web.api.utils.DoubleUtils;
import com.authine.cloudpivot.web.api.utils.UserUtils;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author wangyong
 * @time 2020/5/11 15:04
 */
@Api(value = "警情信息", tags = "二次开发：警情信息")
@RestController
@Slf4j
@RequestMapping("/controller/alertInfo")
public class AlertInfoController extends BaseController {

    @Autowired
    AlertInfoService alertInfoService;

    @ApiOperation("根据消防站id获取今日警情信息")
    @GetMapping("/getStationAlertInfoByStationId")
    public ResponseResult<Object> getStationAlertInfoByStationId(@RequestParam String stationId, Date date) {
        String userId = UserUtils.getUserId(this.getUserId());

        userId = "2c9280a26706a73a016706a93ccf002b";

        StationAlertInfo alertInfo = alertInfoService.getStationAlertInfoByStationId(stationId, DateUtils.getYearMonthDateTime(date), userId);
        return this.getErrResponseResult(alertInfo, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }


    @ApiOperation("更新今日警情信息")
    @PutMapping("/updateStationAlertInfoByStationId")
    public ResponseResult<Object> updateStationAlertInfoByStationId(@RequestBody StationAlertInfo alertInfo, @RequestParam String consumerType, @RequestParam String password) {
        String pwd = UserUtils.getConsumerPassword(alertInfo.getStationId(), this.getUserId(), consumerType);
        if (StringUtils.isEmpty(pwd) || !pwd.equals(password)) {
            this.getErrResponseResult(null, 407L, "密码错误");
        }
        alertInfoService.updateStationAlertInfoByStationId(alertInfo);
        return this.getErrResponseResult("更新成功", ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }

    @ApiOperation("获取大队今日/月度警情信息")
    @GetMapping("/getBrigadeAlertInfoByBrigadeId")
    public ResponseResult<Object> getBrigadeAlertInfoByBrigadeId(@RequestParam(required = true) @ApiParam("大队id") String brigadeId, @RequestParam(required = true) @ApiParam("日期") Date date) {
        List<BrigadeAlertInfo> alertInfos = alertInfoService.getMonthBrigadeAlertInfoByBrigadeId(brigadeId, date);
        BrigadeAlertInfoAnalysis brigadeAlertInfoAnalysis = new BrigadeAlertInfoAnalysis();
        initBrigadeAlertInfoAnalysis(brigadeAlertInfoAnalysis, brigadeId, date);

        Map<String, Map<String, Object>> monthAlertInfoNum = new HashMap<>();

        monthAlertInfoNum.put("fireAlarmNum", new HashMap<>());
        monthAlertInfoNum.get("fireAlarmNum").put("name", "火灾扑救");
        monthAlertInfoNum.get("fireAlarmNum").put("value", 0);

        monthAlertInfoNum.put("emergencyRescueNum", new HashMap<>());
        monthAlertInfoNum.get("emergencyRescueNum").put("name", "抢险救援");
        monthAlertInfoNum.get("emergencyRescueNum").put("value", 0);

        monthAlertInfoNum.put("socialAssistanceNum", new HashMap<>());
        monthAlertInfoNum.get("socialAssistanceNum").put("name", "社会救助");
        monthAlertInfoNum.get("socialAssistanceNum").put("value", 0);

        monthAlertInfoNum.put("falseAlarmNum", new HashMap<>());
        monthAlertInfoNum.get("falseAlarmNum").put("name", "虚假报警");
        monthAlertInfoNum.get("falseAlarmNum").put("value", 0);

        monthAlertInfoNum.put("otherAlertNum", new HashMap<>());
        monthAlertInfoNum.get("otherAlertNum").put("name", "其他警情");
        monthAlertInfoNum.get("otherAlertNum").put("value", 0);

        Map<String, Integer> monthStreetAlert = new HashMap<>();

        for (BrigadeAlertInfo alertInfo : alertInfos) {
            String key = getBrigadeAlertInfoAnalysisKey(alertInfo.getAlertType());
            if (!StringUtils.isEmpty(key)) {
                if (DateUtils.YearMonthDateIsSame(alertInfo.getDate(), date)) {
                    brigadeAlertInfoAnalysis.getDateAlertInfo().put(key, brigadeAlertInfoAnalysis.getDateAlertInfo().get(key) + 1);
                    brigadeAlertInfoAnalysis.getDateAlertInfo().put("callPoliceTotal", brigadeAlertInfoAnalysis.getDateAlertInfo().get("callPoliceTotal") + 1);
                }
                Map<String, Object> monthData = monthAlertInfoNum.get(key);
                monthData.put("value", Integer.parseInt(monthData.get("value") + "") + 1);
                if (monthStreetAlert.containsKey(alertInfo.getStreet())) {
                    monthStreetAlert.put(alertInfo.getStreet(), monthStreetAlert.get(alertInfo.getStreet()) + 1);
                } else {
                    monthStreetAlert.put(alertInfo.getStreet(), 1);
                }
            }
        }

        for (String street : monthStreetAlert.keySet()) {
            brigadeAlertInfoAnalysis.getStreets().add(street);
            brigadeAlertInfoAnalysis.getAlertNums().add(Integer.parseInt(monthStreetAlert.get(street) + ""));
        }

        brigadeAlertInfoAnalysis.getMonthAlertAnalysis().add(monthAlertInfoNum.get("fireAlarmNum"));
        brigadeAlertInfoAnalysis.getMonthAlertAnalysis().add(monthAlertInfoNum.get("emergencyRescueNum"));
        brigadeAlertInfoAnalysis.getMonthAlertAnalysis().add(monthAlertInfoNum.get("socialAssistanceNum"));
        brigadeAlertInfoAnalysis.getMonthAlertAnalysis().add(monthAlertInfoNum.get("falseAlarmNum"));
        brigadeAlertInfoAnalysis.getMonthAlertAnalysis().add(monthAlertInfoNum.get("otherAlertNum"));


        return this.getErrResponseResult(brigadeAlertInfoAnalysis, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }

    /**
     * 初始化大队警情分析
     *
     * @param brigadeAlertInfoAnalysis 大队警情分析
     * @param brigadeId                大队id
     * @param date                     日期
     * @author wangyong
     */
    private void initBrigadeAlertInfoAnalysis(BrigadeAlertInfoAnalysis brigadeAlertInfoAnalysis, String brigadeId, Date date) {
        brigadeAlertInfoAnalysis.setBrigadeId(brigadeId);
        brigadeAlertInfoAnalysis.setDate(date);
        brigadeAlertInfoAnalysis.setDateAlertInfo(new HashMap<>());
        brigadeAlertInfoAnalysis.setMonthAlertAnalysis(new ArrayList<>());
//        brigadeAlertInfoAnalysis.setMonthStreetAlert(new HashMap<>());
        brigadeAlertInfoAnalysis.setStreets(new ArrayList<>());
        brigadeAlertInfoAnalysis.setAlertNums(new ArrayList<>());
        brigadeAlertInfoAnalysis.getDateAlertInfo().put("callPoliceTotal", 0);
        brigadeAlertInfoAnalysis.getDateAlertInfo().put("fireAlarmNum", 0);
        brigadeAlertInfoAnalysis.getDateAlertInfo().put("emergencyRescueNum", 0);
        brigadeAlertInfoAnalysis.getDateAlertInfo().put("socialAssistanceNum", 0);
        brigadeAlertInfoAnalysis.getDateAlertInfo().put("falseAlarmNum", 0);
        brigadeAlertInfoAnalysis.getDateAlertInfo().put("otherAlertNum", 0);

    }

    private String getBrigadeAlertInfoAnalysisKey(String alertType) {
        String result = "";
        switch (alertType) {
            case "火灾扑救":
                result = "fireAlarmNum";
                break;
            case "抢险救援":
                result = "emergencyRescueNum";
                break;
            case "社会救助":
                result = "socialAssistanceNum";
                break;
            case "虚假报警":
                result = "falseAlarmNum";
                break;
            case "其他警情":
                result = "otherAlertNum";
                break;
        }
        return result;
    }

}
