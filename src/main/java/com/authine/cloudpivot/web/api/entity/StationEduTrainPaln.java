package com.authine.cloudpivot.web.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class StationEduTrainPaln extends BaseEntity {

    /**
     * 大队id
     */
    private String brigadeId;

    /**
     * 消防站
     */
    private String stationId;

    /**
     * 日期
     */
    private Date date;

    /**
     * 早操
     */
    private String morningExercises;

    /**
     * 早上
     */
    private String morning;

    /**
     * 上午
     */
    private String afternoon;

    /**
     * 晚上
     */
    private String night;

}
