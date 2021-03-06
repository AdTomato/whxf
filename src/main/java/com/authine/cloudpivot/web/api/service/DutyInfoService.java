package com.authine.cloudpivot.web.api.service;

import com.authine.cloudpivot.web.api.dto.BrigadeDutyInfoDto;
import com.authine.cloudpivot.web.api.dto.StationDutyInfoDto;

import java.util.Date;

/**
 * 值班信息service接口
 * @author wangyong
 * @time 2020/5/13 15:04
 */
public interface DutyInfoService {

    /**
     * 根据消防站id，日期获取消防站的值班信息
     *
     * @param stationId 消防站id
     * @param date      日期
     * @return 消防站值班信息
     * @author wangyong
     */
    StationDutyInfoDto getStationDutyInfoByStationId(String stationId, Date date);


    /**
     * 根据大队id，日期获取大队的值班信息
     *
     * @param brigadeId 大队id
     * @param date      日期
     * @return 大队值班信息
     * @author wangyong
     */
    BrigadeDutyInfoDto getBrigadeDutyInfoByBrigadeId(String brigadeId, Date date);

}

