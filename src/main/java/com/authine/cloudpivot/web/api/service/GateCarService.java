package com.authine.cloudpivot.web.api.service;

import com.authine.cloudpivot.web.api.entity.GateCarAccessRecord;
import com.authine.cloudpivot.web.api.entity.GateCarInfo;

import java.util.List;

/**
 * @author wangyong
 * @time 2020/5/29 13:11
 */
public interface GateCarService {


    /**
     * 批量插入车辆信息
     *
     * @param gateCarInfoList 车辆信息
     * @author wangyong
     */
    void insertGateInfos(List<GateCarInfo> gateCarInfoList);

    /**
     * 批量更新车辆信息
     *
     * @param gateCarInfoList 车辆信息
     * @author wangyong
     */
    void updateGateInfos(List<GateCarInfo> gateCarInfoList);

    /**
     * 批量插入车辆进出记录
     *
     * @param gateCarAccessRecordList 车辆进出记录
     * @author wangyong
     */
    void insertGateCarAccessRecords(List<GateCarAccessRecord> gateCarAccessRecordList);


    /**
     * 查找以及存在的过车流水号
     *
     * @param passRecordCodeList 过车流水号集合
     * @return 以及存在系统中的过车流水号
     * @author wangyong
     */
    List<String> getPresenceGateCarAccessRecord(List<String> passRecordCodeList);

}
