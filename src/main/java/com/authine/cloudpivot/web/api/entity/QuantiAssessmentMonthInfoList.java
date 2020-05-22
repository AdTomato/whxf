package com.authine.cloudpivot.web.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 量化考勤周报月度详情集合
 *
 * @author weiyao
 * @time 2020/5/22
 */
@Data
@AllArgsConstructor
public class QuantiAssessmentMonthInfoList {

    /**
     * 班级名称
     */
    private String team;

    //评分详情
    private List<QuantiAssessmentMonthInfo> quantiAssessmentMonthInfoList;

    public QuantiAssessmentMonthInfoList(){
        this.quantiAssessmentMonthInfoList=new ArrayList<>();
    }




}
