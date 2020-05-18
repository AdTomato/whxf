package com.authine.cloudpivot.web.api.service;

import com.authine.cloudpivot.web.api.dto.DetailInfoDto;

import java.util.List;

/**
 * 车辆信息service接口
 *
 * @author Ke Longhai
 * @time 2020/5/11
 */
public interface TrainResultService {

    /**
     * 根据消防站id获取该消防站的车辆信息
     *
     * @param stationId 消防站id
     * @return 龙虎榜信息
     * @author Ke Longhai
     */
    List<DetailInfoDto> getTrainResultsByStationId(String stationId);


    void updateSubjectsStatusById(String id, String subject);


    void updateResultsStatusById(String id, String result);


}
