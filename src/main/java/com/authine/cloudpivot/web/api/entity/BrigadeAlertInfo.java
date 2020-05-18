package com.authine.cloudpivot.web.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 大队今日警情信息
 *
 * @author wangyong
 * @time 2020/5/18 10:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrigadeAlertInfo extends BaseEntity {

    /**
     * 大队id
     */
    private String brigadeId;

    /**
     * 日期
     */
    private Date date;

    /**
     * 街道
     */
    private String street;

    /**
     * 警情类型
     */
    private String alertType;

    /**
     * 数量
     */
    private Integer quantity;

}
