package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.dto.WeekFocusDto;
import com.authine.cloudpivot.web.api.entity.WeekFocus;
import com.authine.cloudpivot.web.api.mapper.WeekWorkMapper;
import com.authine.cloudpivot.web.api.service.WeekWorkService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Ke LongHai
 * @Date 2020/5/18 10:20
 * @Version 1.0
 */
@Service
@Transactional
public class WeekWorkServiceImpl implements WeekWorkService {

    @Resource
    WeekWorkMapper weekWorkMapper;

    /**
     *根据大队id获取该队的本周重点工作信息
     *
     * @param brigadeId
     * @return  大队本周所有重点工作信息
     */
    @Override
    public  List<WeekFocusDto> getWeekWorksByBrigadeId(String brigadeId) {
        return weekWorkMapper.getWeekWorksByBrigadeId(brigadeId);
    }



    /**
     *  根据大队id更新该队的本周重点工作信息的状态
     *
     * @param id    工作重点id
     * @param status    工作完成进度的状态
     */
    @Override
    public void updateWorksStatusById(String id, String status) {
        weekWorkMapper.updateWorksStatusById(id, status);
    }
}
