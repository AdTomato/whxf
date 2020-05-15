package com.authine.cloudpivot.web.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 量化考勤周报
 *
 * @author weiyao
 * @time 2020/5/14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuantiAssessment extends BaseEntity {

    /**
     * 大队id
     */
    private String brigadeId;

    /**
     * 消防站id
     */
    private String stationId;

    /**
     * 日期
     */
    private Date date;

    /**
     * 班级
     */
    private String team;

    /**
     *姓名
     */
    private String evaluationName;

    /**
     *项目
     */
    private String project;


    /**
     * 加减分数
     */
    private Integer score;

    /**
     * 上月总分数
     */
    private Integer lastMonthScore;

    /**
     * 本月总分数
     */
    private Integer currentMonthScore;



}
