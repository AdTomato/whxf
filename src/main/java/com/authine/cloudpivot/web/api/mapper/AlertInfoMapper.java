package com.authine.cloudpivot.web.api.mapper;

import com.authine.cloudpivot.web.api.entity.AlertInfo;

import java.util.Date;
import java.util.Map;

/**
 * 警情信息mapper
 *
 * @author wangyong
 * @time 2020/5/11 13:48
 */
public interface AlertInfoMapper {

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
     * @return 今日警情信息
     * @author wangyong
     */
    AlertInfo getStationAlertInfoByStationId(String stationId, Date date);

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
     * @param data      时间
     * @return 警情信息
     * @author wangyong
     */
    Map<String, Integer> getBrigadeAlertInfoByBrigadeId(String brigadeId, Date date);

}
