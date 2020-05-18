package com.authine.cloudpivot.web.api.entity;

import lombok.*;

import java.util.Date;

/**
 * 龙虎榜主表
 *
 * @author Ke Longhai
 * @time 2020/5/15 9:07
 */


@AllArgsConstructor
@NoArgsConstructor
public class TrainResult extends BaseEntity {

    /**
     * 站名称
     */
    private String stationId;

    //日期
    private Date date;


}
