package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.dto.VehicleInfoDto;
import com.authine.cloudpivot.web.api.mapper.CarsInfoMapper;
import com.authine.cloudpivot.web.api.service.CarsInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author wangyong
 * @time 2020/5/11 10:38
 */
@Service
@Transactional
public class CarsInfoServiceImpl implements CarsInfoService {

    @Resource
    CarsInfoMapper carsInfoMapper;

    /**
     * 根据消防站id获取该消防站的车辆信息
     *
     * @param stationId 消防站id
     * @return 消防站车辆信息
     * @author wangyong
     */
    @Override
    public VehicleInfoDto getCarsInfosByStationId(String stationId) {
        return carsInfoMapper.getCarsInfosByStationId(stationId);
    }

    /**
     * 根据id更新消防车的状态
     *
     * @param id     消防车id
     * @param status 消防车状态 在位 保修 出动
     * @author wangyong
     */
    @Override
    public void updateCarsStatusById(String id, String status) {
        carsInfoMapper.updateCarsStatusById(id, status);
    }
}
