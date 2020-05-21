package com.authine.cloudpivot.web.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 消防站每月之星
 *
 * @author wangyong
 * @time 2020/5/13 17:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StationStarMonth extends BaseEntity{

    /**
     * 消防站id
     */
    private String stationId;

    /**
     * 学习之星
     */
    private Unit learningStar;

    /**
     * 学习之星头像
     */
    private String learningStarImg;

    /**
     * 纪律之星
     */
    private Unit disciplineStar;

    /**
     * 纪律之星头像
     */
    private String disciplineStarImg;

    /**
     * 训练之星
     */
    private Unit trainStar;

    /**
     * 训练之星头像
     */
    private String trainStarImg;

    /**
     * 内务之星
     */
    private Unit houseStar;

    /**
     * 内务之星头像
     */
    private String houseStarImg;

}
