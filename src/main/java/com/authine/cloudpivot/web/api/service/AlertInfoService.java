package com.authine.cloudpivot.web.api.service;

import com.authine.cloudpivot.web.api.entity.AlertInfo;
import com.authine.cloudpivot.web.api.entity.BrigadeAlertInfo;
import com.authine.cloudpivot.web.api.entity.StationAlertInfo;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 接警信息service接口
 *
 * @author wangyong
 * @time 2020/5/11 14:44
 */
public interface AlertInfoService {

    /**
     * 插入消防站的今日警情信息
     *
     * @param alertInfo 今日警情信息
     * @author wangyong
     */
    void insertStationAlertInfo(StationAlertInfo alertInfo);

    /**
     * 根据消防站id获取今日警情信息
     *
     * @param stationId 消防站id
     * @param date      时间
     * @param userId    用户id
     * @return 今日警情信息
     * @author wangyong
     */
    StationAlertInfo getStationAlertInfoByStationId(String stationId, Date date, String userId);

    /**
     * 更新消防站的今日警情信息
     *
     * @param alertInfo 今日警情信息
     * @author wangyong
     */
    void updateStationAlertInfoByStationId(StationAlertInfo alertInfo);


    /**
     * 根据大队id获取某一日的大队警情信息
     *
     * @param brigadeId 大队id
     * @param date      时间
     * @return 大队某日警情信息
     * @author wangyong
     */
    List<BrigadeAlertInfo> getDateBrigadeAlertInfoByBrigadeId(String brigadeId, Date date);

    /**
     * 根据大队id获取某一月的大队警情信息
     *
     * @param brigadeId 大队id
     * @param date      时间
     * @return 大队某月警情信息
     * @author wangyong
     */
    List<BrigadeAlertInfo> getMonthBrigadeAlertInfoByBrigadeId(String brigadeId, Date date);


}
