package com.authine.cloudpivot.web.api.service;

import com.authine.cloudpivot.web.api.entity.PsychologyManData;

import java.util.List;

/**
 * @Author Ke LongHai
 * @Date 2020/8/5 12:49
 * @Version 1.0
 */
public interface PsychologyManDataService {

    PsychologyManData getPsychologyManDataId(String id);

    List<PsychologyManData> getPsychologyManData(String id);



}
