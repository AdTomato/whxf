package com.authine.cloudpivot.web.api.controller;

import com.authine.cloudpivot.engine.enums.ErrCode;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.entity.LargeScreenAuthority;
import com.authine.cloudpivot.web.api.service.OrgService;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.OBJ_ADAPTER;
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
@Api(value = "自建组织表单", tags = "二次开发：自建组织表单")
@RestController
@RequestMapping("/controller/org")
@Slf4j
public class OrgController extends BaseController {

    @Autowired
    OrgService orgService;

    /**
     * 判断登陆人的权限
     *
     * @param userId 用户id
     * @return 登录人权限
     * @author wangyong
     */
    @ApiOperation("判断登陆人的权限")
    @GetMapping("/getUserAuthority")
    public ResponseResult<Object> getUserAuthority(@RequestParam(required = false) @ApiParam(value = "登陆人的id") String userId) {

        if (StringUtils.isEmpty(userId)) {
            // 登陆人id为空
            userId = this.getUserId();
            if (StringUtils.isEmpty(userId)) {
                // 没有查询到用户的id
                return this.getErrResponseResult(null, 405L, "没有查询到用户id");
            }
        }

        LargeScreenAuthority largeScreenAuthority = new LargeScreenAuthority();
        largeScreenAuthority.setIsDetachment(false);
        largeScreenAuthority.setIsBrigade(false);
        largeScreenAuthority.setIsStation(false);

        // 支队
        String detachmentId = orgService.getDetachmentIdByUserId(userId);
        if (StringUtils.isEmpty(detachmentId)) {
            // 没有支队的权限
            // 查询是否有站的权限
            List<Map<String, String>> brigadeList = orgService.getBrigadeIdByUserId(userId);
            if (brigadeList.isEmpty()) {
                // 没有大队权限
                // 查询是否有站的权限
                List<Map<String, String>> stationList = orgService.getStationIdByUserId(userId);
                if (stationList.isEmpty()) {
                    // 没有消防站的权限
                    // 该用户没有查看大屏的权限
                    return this.getErrResponseResult(null, 406L, "没有查询大屏的权限");
                } else {
                    largeScreenAuthority.setIsStation(true);
                    largeScreenAuthority.setStationData(stationList);
                }
            } else {
                // 为大队权限
                largeScreenAuthority.setIsBrigade(true);
                largeScreenAuthority.setBrigadeData(brigadeList);
            }
        } else {
            // 为支队权限
            largeScreenAuthority.setIsDetachment(true);
            largeScreenAuthority.setDetachmentData(orgService.getAllBrigadeNameList());
        }
        return this.getErrResponseResult(largeScreenAuthority, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }

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
    public ResponseResult<Object> getAllStationListByBrigadeId(@RequestParam @ApiParam(value = "大队id", required = true) String brigadeId) {
        List<Map<String, String>> stationList = orgService.getAllStationListByBrigadeId(brigadeId);
        return this.getErrResponseResult(stationList, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }

    @ApiOperation(value = "获取大屏的标题")
    @GetMapping("/getTitle")
    public ResponseResult<Object> getTitle(@RequestParam @ApiParam(value = "id值", required = true) String id) {
        String title = orgService.getTitle(id);
        if (StringUtils.isEmpty(title)) {
            return this.getErrResponseResult(null, 407L, "没有获取到大屏的标题");
        }
        return this.getErrResponseResult(title, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }
}
