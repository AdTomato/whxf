package com.authine.cloudpivot.web.api.mapper;

import com.authine.cloudpivot.web.api.entity.StationStarMonth;

import java.util.Date;

/**
 * 每月之星mapper
 *
 * @author wangyong
 * @time 2020/5/13 17:21
 */
public interface StarMonthMapper {

    /**
     * 根据消防站id、日期获取消防站每月之星
     *
     * @param stationId 消防站id
     * @param date      日期
     * @return 每月之星
     * @author wangyong
     */
    StationStarMonth getStationStarMonthByStationId(String stationId, Date date);

}
