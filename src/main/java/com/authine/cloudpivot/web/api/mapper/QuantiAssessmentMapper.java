package com.authine.cloudpivot.web.api.mapper;

import com.authine.cloudpivot.web.api.entity.QuantiAssessment;

import java.util.Date;
import java.util.List;

/*
 @author:weiyao
 @time:2020-05-15
 @desc:量化考评周报
  */
public interface QuantiAssessmentMapper {

    //获取车辆信息
    List<QuantiAssessment> getAssess(String id);

    //更新
    void updateQuantiAssessmentById(QuantiAssessment quantiAssessment);
}
