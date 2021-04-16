package com.authine.cloudpivot.web.api.mapper;

import com.authine.cloudpivot.web.api.entity.PsychologyManData;

import java.util.List;

/**
 * 心理咨询师接口
 * @Author Ke LongHai
 * @Date 2020/8/5 12:49
 * @Version 1.0
 */
public interface PsychologyManDataMapper {


    /**
     * 根据id查询具体心理咨询师
     *
     * @param id id
     * @return {@link PsychologyManData}
     */
    PsychologyManData getPsychologyManDataId(String id);

    List<PsychologyManData> getPsychologyManData(String id);


    /**
     * 获得心理咨询师列表
     *
     * @param info 信息
     * @return {@link List<PsychologyManData>}
     */
    List<PsychologyManData> getPsychologyManDataList(PsychologyManData info);
}
