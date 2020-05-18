package com.authine.cloudpivot.web.api.service;

import com.authine.cloudpivot.web.api.dto.WeekFocusDto;

import java.util.List;

/**本周重点工作Service接口
 * @Author Ke LongHai
 * @Date 2020/5/18 10:10
 * @Version 1.0
 */
public interface WeekWorkService {
    /**
     *根据大队id获取该队的本周重点工作信息
     * @param brigadeId
     * @return  大队本周重点工作信息
     */
    List<WeekFocusDto> getWeekWorksByBrigadeId(String brigadeId);

    /**
     *  根据大队id更新该队的本周重点工作信息的状态
     * @param id    工作重点id
     * @param status    工作完成进度的状态
     */
    void updateWorksStatusById(String id, String status);

}
