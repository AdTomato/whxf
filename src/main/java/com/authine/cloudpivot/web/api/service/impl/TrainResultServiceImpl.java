package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.dto.DetailInfoDto;
import com.authine.cloudpivot.web.api.mapper.TrainResultMapper;
import com.authine.cloudpivot.web.api.service.TrainResultService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Ke Longhai
 * @time 2020/5/11 10:38
 */
@Service
@Transactional
public class TrainResultServiceImpl implements TrainResultService {

    @Resource
    TrainResultMapper trainResultMapper;

    /**
     * 根据消防站id获取该龙虎榜
     *
     * @param stationId 消防站id
     * @return 龙虎榜信息
     * @author Ke Longhai
     */
    @Override
    public List<DetailInfoDto> getTrainResultsByStationId(String stationId) {
        return trainResultMapper.getTrainResultsByStationId(stationId);
    }

    /**
     * 根据id更新消防车的状态
     *
     * @param id     成绩表id
     * @param  subject  科目
     * @author Ke Longhai
     */
    @Override
    public void updateSubjectsStatusById(String id, String subject) {
        trainResultMapper.updateSubjectsStatusById(id, subject);

    }

    @Override
    public void updateResultsStatusById(String id, String result) {
        trainResultMapper.updateResultsStatusById(id, result);
    }
}
