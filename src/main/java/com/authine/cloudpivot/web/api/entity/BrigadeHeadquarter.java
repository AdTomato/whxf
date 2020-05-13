package com.authine.cloudpivot.web.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 消防站值班信息中大队全勤指挥部值班信息
 *
 * @author wangyong
 * @time 2020/5/13 14:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrigadeHeadquarter {

    /**
     * id值
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
     * 值班人名称
     */
    private String dutyName;

}
