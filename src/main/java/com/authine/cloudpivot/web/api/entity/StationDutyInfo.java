package com.authine.cloudpivot.web.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 消防站值班信息主表
 *
 * @author wangyong
 * @time 2020/5/13 14:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StationDutyInfo extends BaseEntity{

    /**
     * 消防站id
     */
    private String stationId;

    /**
     * 日期
     */
    private Date date;

}
