package com.authine.cloudpivot.web.api.service;


import com.authine.cloudpivot.web.api.entity.ScaleTestAcore;

import java.util.List;
import java.util.Map;

/**
 * @author: weiyao
 * @time: 2020/8/1
 * @Description: 心理咨询系统--测评结果
 */
public interface ScaleTestResultService {

    /**
     * 根据分数得出测评结果
     */
    List<Map<String,String>> getResultByScore(String parentId, Integer score);

    /**
     * 插入统计结果
     */
    String insertScaleTestAcore(ScaleTestAcore info);


}
