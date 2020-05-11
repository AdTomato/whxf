package com.authine.cloudpivot.web.api.service;

import com.authine.cloudpivot.web.api.entity.AlertInfo;

import java.util.Date;
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
    void insertStationAlertInfo(AlertInfo alertInfo);

    /**
     * 根据消防站id获取今日警情信息
     *
     * @param stationId 消防站id
     * @param date      时间
     * @param userId    用户id
     * @return 今日警情信息
     * @author wangyong
     */
    AlertInfo getStationAlertInfoByStationId(String stationId, Date date, String userId);

    /**
     * 更新消防站的今日警情信息
     *
     * @param alertInfo 今日警情信息
     * @author wangyong
     */
    void updateStationAlertInfoByStationId(AlertInfo alertInfo);

    /**
     * 获取一个大队的警情信息
     *
     * @param brigadeId 大队id
     * @param date      时间
     * @return 警情信息
     * @author wangyong
     */
    Map<String, Integer> getBrigadeAlertInfoByBrigadeId(String brigadeId, Date date);

}
