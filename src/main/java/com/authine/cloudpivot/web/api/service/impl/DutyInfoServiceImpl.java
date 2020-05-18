package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.dto.BrigadeDutyInfoDto;
import com.authine.cloudpivot.web.api.dto.StationDutyInfoDto;
import com.authine.cloudpivot.web.api.mapper.DutyInfoMapper;
import com.authine.cloudpivot.web.api.service.DutyInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 值班信息service
 *
 * @author wangyong
 * @time 2020/5/13 15:06
 */
@Service
public class DutyInfoServiceImpl implements DutyInfoService {

    @Resource
    DutyInfoMapper dutyInfoMapper;

    /**
     * 根据消防站id，日期获取消防站的值班信息
     *
     * @param stationId 消防站id
     * @param date      日期
     * @return 消防站值班信息
     * @author wangyong
     */
    @Override
    public StationDutyInfoDto getStationDutyInfoByStationId(String stationId, Date date) {
        return dutyInfoMapper.getStationDutyInfoByStationId(stationId, date);
    }


    /**
     * 根据大队id，日期获取大队的值班信息
     *
     * @param brigadeId 大队id
     * @param date      日期
     * @return 大队值班信息
     * @author wangyong
     */
    @Override
    public BrigadeDutyInfoDto getBrigadeDutyInfoByBrigadeId(String brigadeId, Date date) {
        return dutyInfoMapper.getBrigadeDutyInfoByBrigadeId(brigadeId, date);
    }
}
