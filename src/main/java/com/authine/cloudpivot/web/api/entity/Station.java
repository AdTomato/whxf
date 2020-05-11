package com.authine.cloudpivot.web.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 消防站
 *
 * @author wangyong
 * @time 2020/5/11 11:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Station extends BaseEntity {

    /**
     * 大队id
     */
    private String brigadeId;

    /**
     * 消防站名称
     */
    private String stationName;

}
