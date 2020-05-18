package com.authine.cloudpivot.web.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 大队值班信息中指挥长子表
 *
 * @author wangyong
 * @time 2020/5/18 13:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Commander {

    private String id;
    private String parentId;
    private Double sortKey;
    private String dutyName;

}
