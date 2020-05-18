package com.authine.cloudpivot.web.api.mapper;

import com.authine.cloudpivot.web.api.dto.BrigadeDutyInfoDto;
import com.authine.cloudpivot.web.api.dto.StationDutyInfoDto;
import com.authine.cloudpivot.web.api.entity.StationDutyCadre;

import java.util.Date;
import java.util.List;

/**
 * 值班信息mapper
 *
 * @author wangyong
 * @time 2020/5/13 14:53
 */
public interface DutyInfoMapper {

    /**
     * 根据消防站id，日期获取消防站的值班信息
     *
     * @param stationId 消防站id
     * @param date      日期
     * @return 消防站值班信息
     * @author wangyong
     */
    StationDutyInfoDto getStationDutyInfoByStationId(String stationId, Date date);

    List<String> getStationDutyCadreByParentId(String parentId);

    List<String> getBrigadeHeadquarterByParentId(String parentId);

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
