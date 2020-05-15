package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.entity.QuantiAssessment;
import com.authine.cloudpivot.web.api.mapper.QuantiAssessmentMapper;
import com.authine.cloudpivot.web.api.service.QuantiAssessmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/*
 @author:weiyao
 @time:2020-05-15
 @desc:量化考评周报
  */
@Service
public class QuantiAssessmentServiceImpl implements QuantiAssessmentService {

    @Resource
    QuantiAssessmentMapper quantiAssessmentMapper;

    @Override
    public List<QuantiAssessment> getAssess(String stationId) {
        return quantiAssessmentMapper.getAssess(stationId);
    }

    @Override
    public void updateQuantiAssessmentById(QuantiAssessment quantiAssessment) {
        quantiAssessmentMapper.updateQuantiAssessmentById(quantiAssessment);
    }
}
