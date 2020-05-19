package com.authine.cloudpivot.web.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: wangyong
 * @time: 2020/4/28 13:02
 * @Description: 人员，部门
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Unit {

    private String id;

    private Integer type;

    private String name;

    private String imgUrl;

    private String sourceId;

    private String userId;

}
