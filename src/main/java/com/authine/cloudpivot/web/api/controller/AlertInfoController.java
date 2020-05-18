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

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseResult<Object> updateStationAlertInfoByStationId(@RequestBody StationAlertInfo alertInfo) {
        alertInfoService.updateStationAlertInfoByStationId(alertInfo);
        return this.getErrResponseResult("更新成功", ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }

    @ApiOperation("获取大队今日/月度警情信息")
    @GetMapping("/getBrigadeAlertInfoByBrigadeId")
    public ResponseResult<Object> getBrigadeAlertInfoByBrigadeId(@RequestParam(required = true) @ApiParam("大队id") String brigadeId, @RequestParam(required = true) @ApiParam("日期") Date date) {
        List<BrigadeAlertInfo> alertInfos = alertInfoService.getMonthBrigadeAlertInfoByBrigadeId(brigadeId, date);
        BrigadeAlertInfoAnalysis brigadeAlertInfoAnalysis = new BrigadeAlertInfoAnalysis();
        initBrigadeAlertInfoAnalysis(brigadeAlertInfoAnalysis, brigadeId, date);

        Map<String, Integer> monthAlertInfoNum = new HashMap<>();
        monthAlertInfoNum.put("callPoliceTotal", 0);
        monthAlertInfoNum.put("fireAlarmNum", 0);
        monthAlertInfoNum.put("emergencyRescueNum", 0);
        monthAlertInfoNum.put("socialAssistanceNum", 0);
        monthAlertInfoNum.put("falseAlarmNum", 0);
        monthAlertInfoNum.put("otherAlertNum", 0);

        for (BrigadeAlertInfo alertInfo : alertInfos) {
            String key = getBrigadeAlertInfoAnalysisKey(alertInfo.getAlertType());
            if (!StringUtils.isEmpty(key)) {
                if (DateUtils.YearMonthDateIsSame(alertInfo.getDate(), date)) {
                    brigadeAlertInfoAnalysis.getDateAlertInfo().put(key, brigadeAlertInfoAnalysis.getDateAlertInfo().get(key) + 1);
                    brigadeAlertInfoAnalysis.getDateAlertInfo().put("callPoliceTotal", brigadeAlertInfoAnalysis.getDateAlertInfo().get("callPoliceTotal") + 1);
                }
                monthAlertInfoNum.put(key, monthAlertInfoNum.get(key) + 1);
                monthAlertInfoNum.put("callPoliceTotal", monthAlertInfoNum.get("callPoliceTotal") + 1);
                if (brigadeAlertInfoAnalysis.getMonthStreetAlert().containsKey(alertInfo.getStreet())) {
                    brigadeAlertInfoAnalysis.getMonthStreetAlert().put(alertInfo.getStreet(), brigadeAlertInfoAnalysis.getMonthStreetAlert().get(alertInfo.getStreet()) + 1);
                } else {
                    brigadeAlertInfoAnalysis.getMonthStreetAlert().put(alertInfo.getStreet(), 1);
                }
            }
        }

        brigadeAlertInfoAnalysis.getMonthAlertAnalysis().put("fireAlarmNum", monthAlertInfoNum.get("fireAlarmNum") == 0 ? 0D : DoubleUtils.doubleRound(monthAlertInfoNum.get("fireAlarmNum") / Double.parseDouble(monthAlertInfoNum.get("callPoliceTotal") + ""), 4));
        brigadeAlertInfoAnalysis.getMonthAlertAnalysis().put("emergencyRescueNum", monthAlertInfoNum.get("emergencyRescueNum") == 0 ? 0D : DoubleUtils.doubleRound(monthAlertInfoNum.get("emergencyRescueNum") / Double.parseDouble(monthAlertInfoNum.get("callPoliceTotal") + ""), 4));
        brigadeAlertInfoAnalysis.getMonthAlertAnalysis().put("socialAssistanceNum", monthAlertInfoNum.get("socialAssistanceNum") == 0 ? 0D : DoubleUtils.doubleRound(monthAlertInfoNum.get("socialAssistanceNum") / Double.parseDouble(monthAlertInfoNum.get("callPoliceTotal") + ""), 4));
        brigadeAlertInfoAnalysis.getMonthAlertAnalysis().put("falseAlarmNum", monthAlertInfoNum.get("falseAlarmNum") == 0 ? 0D : DoubleUtils.doubleRound(monthAlertInfoNum.get("falseAlarmNum") / Double.parseDouble(monthAlertInfoNum.get("callPoliceTotal") + ""), 4));
        brigadeAlertInfoAnalysis.getMonthAlertAnalysis().put("otherAlertNum", monthAlertInfoNum.get("otherAlertNum") == 0 ? 0D : DoubleUtils.doubleRound(monthAlertInfoNum.get("otherAlertNum") / Double.parseDouble(monthAlertInfoNum.get("callPoliceTotal") + ""), 4));

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
        brigadeAlertInfoAnalysis.setMonthAlertAnalysis(new HashMap<>());
        brigadeAlertInfoAnalysis.setMonthStreetAlert(new HashMap<>());
        brigadeAlertInfoAnalysis.getDateAlertInfo().put("callPoliceTotal", 0);
        brigadeAlertInfoAnalysis.getDateAlertInfo().put("fireAlarmNum", 0);
        brigadeAlertInfoAnalysis.getDateAlertInfo().put("emergencyRescueNum", 0);
        brigadeAlertInfoAnalysis.getDateAlertInfo().put("socialAssistanceNum", 0);
        brigadeAlertInfoAnalysis.getDateAlertInfo().put("falseAlarmNum", 0);
        brigadeAlertInfoAnalysis.getDateAlertInfo().put("otherAlertNum", 0);

        brigadeAlertInfoAnalysis.getMonthAlertAnalysis().put("fireAlarmNum", 0D);
        brigadeAlertInfoAnalysis.getMonthAlertAnalysis().put("emergencyRescueNum", 0D);
        brigadeAlertInfoAnalysis.getMonthAlertAnalysis().put("socialAssistanceNum", 0D);
        brigadeAlertInfoAnalysis.getMonthAlertAnalysis().put("falseAlarmNum", 0D);
        brigadeAlertInfoAnalysis.getMonthAlertAnalysis().put("otherAlertNum", 0D);
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
