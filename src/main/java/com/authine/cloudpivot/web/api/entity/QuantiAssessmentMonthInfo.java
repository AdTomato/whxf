package com.authine.cloudpivot.web.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 量化考勤周报月度详情
 *
 * @author weiyao
 * @time 2020/5/22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuantiAssessmentMonthInfo {

    /**
     * 姓名
     */
    private String name;

    /**
     * 政治思想加减分
     */
    private Integer zzsxScore;

    /**
     *服从命令加减分
     */
    private Integer fcmlScore;

    /**
     * 履行职责加减分
     */
    private Integer lxzzScore;

    /**
     * 遵章守纪加减分
     */
    private Integer zzsjScore;

    /**
     * 作风养成加减分
     */
    private Integer zfycScore;

    /**
     * 一日生活制度加减分
     */
    private Integer shzdScore;

    /**
     * 军事训练加减分
     */
    private Integer jsxlScore;

    /**
     * 灭火救援加减分
     */
    private Integer mhjyScore;

    /**
     * 上月总分数
     */
    private Integer lastMonthScore;

    /**
     * 本月总分数
     */
    private Integer currentMonthScore;



}
