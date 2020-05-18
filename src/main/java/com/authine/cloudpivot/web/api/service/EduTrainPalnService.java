package com.authine.cloudpivot.web.api.service;

import com.authine.cloudpivot.web.api.entity.StationEduTrainPaln;

import java.util.Date;

public interface EduTrainPalnService {

    /**
     * 插入消防站的今日教育训练计划
     *
     * @param eduTrainPaln  今日教育训练计划
     * @author Ke Longhai
     */
    void insertStationEduTrainPaln(StationEduTrainPaln eduTrainPaln );

    /**
     * 根据消防站id获取今日教育训练计划
     *
     * @param stationId 消防站id
     * @return 今日教育训练计划
     * @author Ke Longhai
     */
    StationEduTrainPaln getStationEduTrainPalnByStationId(String stationId, String userId);

    /**
     * 更新消防站的今日教育训练计划
     *
     * @param eduTrainPaln 今日教育训练计划
     * @author Ke Longhai
     */
    void updateStationEduTrainPalnByStationId(StationEduTrainPaln eduTrainPaln);

}
