package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.entity.StationStarMonth;
import com.authine.cloudpivot.web.api.mapper.StarMonthMapper;
import com.authine.cloudpivot.web.api.service.StarMonthService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 每月之星service
 *
 * @author wangyong
 * @time 2020/5/13 17:30
 */
@Service
public class StarMonthServiceIml implements StarMonthService {

    @Resource
    StarMonthMapper starMonthMapper;


    /**
     * 根据消防站id、日期获取消防站每月之星
     *
     * @param stationId 消防站id
     * @param date      日期
     * @return 每月之星
     * @author wangyong
     */
    @Override
    public StationStarMonth getStationStarMonthByStationId(String stationId, Date date) {
        return starMonthMapper.getStationStarMonthByStationId(stationId, date);
    }
}
