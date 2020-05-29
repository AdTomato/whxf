package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.entity.GateCarAccessRecord;
import com.authine.cloudpivot.web.api.entity.GateCarInfo;
import com.authine.cloudpivot.web.api.mapper.GateCarMapper;
import com.authine.cloudpivot.web.api.service.GateCarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author wangyong
 * @time 2020/5/29 13:12
 */
@Service
@Slf4j
public class GateCarServiceImpl implements GateCarService {

    @Resource
    GateCarMapper gateCarMapper;

    /**
     * 批量创建闸机车辆信息
     *
     * @param gateCarInfoList 车辆信息
     * @author wangyong
     */
    @Override
    public void insertGateInfos(List<GateCarInfo> gateCarInfoList) {
        log.info("批量创建闸机车辆信息");
        gateCarMapper.insertGateInfos(gateCarInfoList);
    }

    /**
     * 批量更新闸机信息
     *
     * @param gateCarInfoList 车辆信息
     * @author wangyong
     */
    @Override
    public void updateGateInfos(List<GateCarInfo> gateCarInfoList) {
        log.info("批量更新闸机信息");
        gateCarMapper.updateGateInfos(gateCarInfoList);
    }

    /**
     * 批量创建闸机车辆进出记录信息
     *
     * @param gateCarAccessRecordList 车辆进出记录
     * @author wangyong
     */
    @Override
    public void insertGateCarAccessRecords(List<GateCarAccessRecord> gateCarAccessRecordList) {
        log.info("批量创建闸机车辆进出记录信息");
        gateCarMapper.insertGateCarAccessRecords(gateCarAccessRecordList);
    }


    /**
     * 查找以及存在的过车流水号
     *
     * @param passRecordCodeList 过车流水号集合
     * @return 以及存在系统中的过车流水号
     * @author wangyong
     */
    public List<String> getPresenceGateCarAccessRecord(List<String> passRecordCodeList) {
        return gateCarMapper.getPresenceGateCarAccessRecord(passRecordCodeList);
    }
}
