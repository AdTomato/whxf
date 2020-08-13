package com.authine.cloudpivot.web.api.service;


import com.authine.cloudpivot.web.api.entity.ScaleConsultDetail;
import com.authine.cloudpivot.web.api.entity.ScaleTestAcore;
import com.authine.cloudpivot.web.api.entity.TeamRecord;

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

    //查询测评结果
    List<ScaleTestAcore> getScaleTestResultInfo(ScaleTestAcore info);

    //查询档案
    List<ScaleConsultDetail>  getScaleConsultDetail(String deptId, String userId);

    //返回部门测评档案
    TeamRecord getDeptNumInfo(String deptId) ;


}
