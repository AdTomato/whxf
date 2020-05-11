package com.authine.cloudpivot.web.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 大队
 *
 * @author wangyong
 * @time 2020/5/11 11:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Brigade extends BaseEntity {

    /**
     * 大队名称
     */
    private String brigadeName;

}
