package com.authine.cloudpivot.web.api.service;

import com.authine.cloudpivot.web.api.entity.QuantiAssessment;
import com.authine.cloudpivot.web.api.entity.QuantiAssessmentMonthInfoList;

import java.util.Date;
import java.util.List;

/*
 @author:weiyao
 @time:2020-05-15
 @desc:量化考评周报
  */
public interface QuantiAssessmentService {

    List<QuantiAssessment> getAssess(String stationId);

    void updateQuantiAssessmentById(QuantiAssessment quantiAssessment);

    List<QuantiAssessmentMonthInfoList> getAssessMonthDetail(String stationId);
}
