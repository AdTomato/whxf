package com.authine.cloudpivot.web.api.service;

import com.authine.cloudpivot.web.api.entity.PsychologyManData;

import java.util.List;

/**
 * @Author Ke LongHai
 * @Date 2020/8/5 12:49
 * @Version 1.0
 */
public interface PsychologyManDataService {

    /**
     * 获得心理学的人数据id
     *
     * @param id id
     * @return {@link PsychologyManData}
     */
    PsychologyManData getPsychologyManDataId(String id);

    List<PsychologyManData> getPsychologyManData(String id);

    //weiyao
    List<PsychologyManData> getPsychologyManDataList(PsychologyManData  info);



}
