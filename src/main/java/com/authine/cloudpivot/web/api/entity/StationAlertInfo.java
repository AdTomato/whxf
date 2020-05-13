package com.authine.cloudpivot.web.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 消防站警情信息
 *
 * @author wangyong
 * @time 2020/5/13 13:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StationAlertInfo extends BaseEntity{

    /**
     * 消防站id
     */
    private String stationId;

    /**
     * 日期
     */
    private Date date;

    /**
     * 接警处总量
     */
    private Integer callPoliceTotal;

    /**
     * 火灾报警
     */
    private Integer fireAlarmNum;

    /**
     * 抢险救援
     */
    private Integer emergencyRescueNum;

    /**
     * 社会救助
     */
    private Integer socialAssistanceNum;

    /**
     * 虚假报警
     */
    private Integer falseAlarmNum;

    /**
     * 其他警情
     */
    private Integer otherAlertNum;
}
