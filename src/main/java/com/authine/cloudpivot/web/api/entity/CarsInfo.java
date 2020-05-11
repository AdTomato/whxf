package com.authine.cloudpivot.web.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 车辆信息主表信息
 *
 * @author wangyong
 * @time 2020/5/11 9:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarsInfo extends BaseEntity {

    /**
     * 站名称
     */
    private String station_id;

}
