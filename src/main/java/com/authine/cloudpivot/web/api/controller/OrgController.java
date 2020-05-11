package com.authine.cloudpivot.web.api.controller;

import com.authine.cloudpivot.engine.enums.ErrCode;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.service.OrgService;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 组织控制层
 *
 * @author wangyong
 * @time 2020/5/11 11:50
 */
@Api(value = "自建组织表单", tags = "自建组织表单")
@RestController
@RequestMapping("/controller/org")
@Slf4j
public class OrgController extends BaseController {

    @Autowired
    OrgService orgService;

    /**
     * 获取所有的大队名称列表
     *
     * @return 队名称，大队id
     * @author wangyong
     */
    @ApiOperation("获取大队名称列表")
    @GetMapping("/getAllBrigadeNameList")
    public ResponseResult<Object> getAllBrigadeNameList() {
        List<Map<String, String>> brigadeNameList = orgService.getAllBrigadeNameList();
        return this.getErrResponseResult(brigadeNameList, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }

    /**
     * 根据大队名称获取大队id
     *
     * @param brigadeName 大队名称
     * @return 大队id
     * @author wangyong
     */
    @ApiOperation("根据大队名称获取大队id")
    @GetMapping("/getBrigadeIdByName")
    public ResponseResult<Object> getBrigadeIdByName(@RequestParam String brigadeName) {
        String brigadeId = orgService.getBrigadeIdByName(brigadeName);
        return this.getErrResponseResult(brigadeId, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }

    /**
     * 获取消防站名称列表
     *
     * @return 消防站名称，消防站id
     * @author wangyong
     */
    @ApiOperation("获取消防站名称列表")
    @GetMapping("/getAllStationList")
    public ResponseResult<Object> getAllStationList() {
        List<Map<String, String>> allStationList = orgService.getAllStationList();
        return this.getErrResponseResult(allStationList, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }

    /**
     * 根据消防站名称获取消防站id
     *
     * @param stationName 消防站名称
     * @return 消防站id
     * @author wangyong
     */
    @ApiOperation("根据消防站名称获取消防站id")
    @GetMapping("/getStationIdByName")
    public ResponseResult<Object> getStationIdByName(@RequestParam String stationName) {
        String stationId = orgService.getStationIdByName(stationName);
        return this.getErrResponseResult(stationId, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }

    /**
     * 根据大队id获取大队下面的所有消防站名称
     *
     * @param brigadeId 大队id
     * @return 消防站名称，id
     * @author wangyong
     */
    @ApiOperation("根据大队id获取大队下面的消防站")
    @GetMapping("/getAllStationListByBrigadeId")
    public ResponseResult<Object> getAllStationListByBrigadeId(@RequestParam String brigadeId) {
        List<Map<String, String>> stationList = orgService.getAllStationListByBrigadeId(brigadeId);
        return this.getErrResponseResult(stationList, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }
}
