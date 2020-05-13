package com.authine.cloudpivot.web.api.entity;

import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 消防站值班信息中队站值班干部
 * @author wangyong
 * @time 2020/5/13 14:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StationDutyCadre {

    /**
     * id
     */
    private String id;

    /**
     * 父id
     */
    private String parentId;

    /**
     * 排序字段
     */
    private Double sortKey;

    /**
     * 值班人员姓名
     */
    private String dutyName;

}
