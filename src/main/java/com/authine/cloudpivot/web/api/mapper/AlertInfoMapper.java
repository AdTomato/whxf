package com.authine.cloudpivot.web.api.mapper;

import com.authine.cloudpivot.web.api.entity.AlertInfo;
import com.authine.cloudpivot.web.api.entity.BrigadeAlertInfo;
import com.authine.cloudpivot.web.api.entity.Station;
import com.authine.cloudpivot.web.api.entity.StationAlertInfo;

import java.util.Date;
import java.util.List;
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
    void insertStationAlertInfo(StationAlertInfo alertInfo);

    /**
     * 根据消防站id获取今日警情信息
     *
     * @param stationId 消防站id
     * @param date      时间
     * @return 今日警情信息
     * @author wangyong
     */
    StationAlertInfo getStationAlertInfoByStationId(String stationId, Date date);

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
     * @return 大队警情信息
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
