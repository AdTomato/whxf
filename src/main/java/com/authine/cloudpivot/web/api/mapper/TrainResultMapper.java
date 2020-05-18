package com.authine.cloudpivot.web.api.mapper;

import com.authine.cloudpivot.web.api.dto.DetailInfoDto;
import com.authine.cloudpivot.web.api.entity.DetailInfo;

import java.util.List;

/**
 * 龙虎榜信息mapper
 *
 * @author Ke Longhai
 * @time 2020/5/15
 */
public interface TrainResultMapper {

    /**
     * 根据消防站id获取该消防站的龙虎榜信息
     *
     * @param stationId 消防站id
     * @return 消防站车辆信息
     * @author Ke Longhai
     */
    List<DetailInfoDto> getTrainResultsByStationId(String stationId);

    /**
     * 根据父id查询龙虎榜信息
     *
     * @param id 父id
     * @return 龙虎榜信息
     * @author Ke Longhai
     */
    List<DetailInfo> getDetailInfoByParentId(String id);

    /**
     * 根据id更新成绩的表科目
     *
     * @param id     成绩id
     * @param subject   科目
     * @author Ke Longhai
     */
    void updateSubjectsStatusById(String id, String subject);

    /**
     * 根据id更新成绩的表成绩
     *
     * @param id     成绩id
     * @param result   成绩
     * @author Ke Longhai
     */
    void updateResultsStatusById(String id, String result);
}
