package com.authine.cloudpivot.web.api.controller;

import com.authine.cloudpivot.engine.enums.ErrCode;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.entity.BaseEntity;
import com.authine.cloudpivot.web.api.entity.BrigadeAnnouncement;
import com.authine.cloudpivot.web.api.entity.StationAnnouncement;
import com.authine.cloudpivot.web.api.service.AnnouncementService;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 公告controller
 *
 * @author wangyong
 * @time 2020/5/21 16:41
 */
@RestController
@RequestMapping("/controller/announcement")
@Api(value = "公告", tags = "二次开发：公告")
@Slf4j
public class AnnouncementController extends BaseController {

    @Autowired
    AnnouncementService announcementService;

    @ApiOperation(value = "获取消防站没有关闭的公告")
    @GetMapping("/getStationAnnouncement")
    public ResponseResult<Object> getStationAnnouncement(@RequestParam(required = true) @ApiParam(value = "消防站id") String stationId) {
        log.info("获取消防站公告");
        long startTime = System.currentTimeMillis();
        List<StationAnnouncement> stationAnnouncementList = announcementService.getIsNotCloseStationAnnouncementByStationId(stationId);
        List<Map<String, String>> announcements = new ArrayList<>();
        for (StationAnnouncement stationAnnouncement : stationAnnouncementList) {
            Map<String, String> map = new HashMap<>();
            map.put("title", stationAnnouncement.getTitle());
            StringBuffer sb = new StringBuffer();
            sb.append("http://121.41.27.194/form/detail?sheetCode=ls_station_announcement&");
            sb.append("objectId=");
            sb.append(stationAnnouncement.getId());
            sb.append("&");
            sb.append("schemaCode=ls_station_announcement&");
            sb.append("return=/application/large_screen/application-list/ls_station_announcement?");
            sb.append("parentId=2c90a43e71afeaba0172119450a349ce&");
            sb.append("code=ls_station_announcement&");
            sb.append("openMode&pcUrl&queryCode=&iframeAction=detail");
            map.put("url", sb.toString());
            announcements.add(map);
        }
        long endTime = System.currentTimeMillis();
        log.info("获取消防站公告接口耗时:" + (endTime - startTime) + "ms");
        if (announcements.isEmpty()) {
            return this.getErrResponseResult(null, 403L, "没有获取到公告数据");
        }
        return this.getErrResponseResult(announcements, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }

    @ApiOperation(value = "获取大队没有关闭的公告")
    @GetMapping("/getBrigadeAnnouncement")
    public ResponseResult<Object> getBrigadeAnnouncement(@RequestParam(required = true) @ApiParam(value = "大队id") String brigadeId) {

        log.info("获取大队公告");
        long startTime = System.currentTimeMillis();

        List<BrigadeAnnouncement> brigadeAnnouncementList = announcementService.getIsNotCloseBrigadeAnnouncementByBrigadeId(brigadeId);
        List<Map<String, String>> announcements = new ArrayList<>();

        for (BrigadeAnnouncement brigadeAnnouncement : brigadeAnnouncementList) {
            Map<String, String> map = new HashMap<>();
            map.put("title", brigadeAnnouncement.getTitle());
            StringBuffer sb = new StringBuffer();
            sb.append("http://121.41.27.194/form/detail?sheetCode=ls_brigade_announcement&");
            sb.append("objectId=");
            sb.append(brigadeAnnouncement.getId());
            sb.append("&");
            sb.append("schemaCode=ls_brigade_announcement&");
            sb.append("return=/application/large_screen/application-list/ls_brigade_announcement?");
            sb.append("parentId=2c90a43e71afeaba017211943ca349cc&");
            sb.append("code=ls_brigade_announcement&openMode&pcUrl&queryCode=&iframeAction=detail");
            map.put("url", sb.toString());
            announcements.add(map);
        }
        long endTime = System.currentTimeMillis();
        log.info("获取大队公告接口耗时:" + (endTime - startTime) + "ms");
        if (announcements.isEmpty()) {
            return this.getErrResponseResult(null, 403L, "没有获取到公告数据");
        }
        return this.getErrResponseResult(announcements, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }
}
