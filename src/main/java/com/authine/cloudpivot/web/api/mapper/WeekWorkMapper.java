package com.authine.cloudpivot.web.api.mapper;

import com.authine.cloudpivot.web.api.dto.WeekFocusDto;
import com.authine.cloudpivot.web.api.entity.WeekFocus;

import java.util.List;

/**
 * 本周重点工作Mapper
 * @Author Ke LongHai
 * @Date 2020/5/18 9:43
 * @Version 1.0
 */
public interface WeekWorkMapper {

    /**
     *根据大队ID获取该大队的本周重点工作信息
     * @param brigadeId     大队ID
     * @return  重点工作信息
     */
    List<WeekFocusDto> getWeekWorksByBrigadeId (String brigadeId);

    /**
     * 根据父id查询本周重点工作信息
     *
     * @param id 父id
     * @return 本周重点工作信息
     */
    List<WeekFocus> getWeekFocusByParentId(String id);


    /**
     *  根据大队id更新该队的本周重点工作信息的状态
     *
     * @param id    工作重点id
     * @param status    工作完成进度的状态
     */
    void updateWorksStatusById(String id, String status);

}
