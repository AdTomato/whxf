package com.authine.cloudpivot.web.api.entity;

import lombok.*;

import java.util.Date;

/**
 * 本周重点工作 主表
 * @Author Ke LongHai
 * @Date 2020/5/18 9:03
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeekWork extends BaseEntity {


    /**
     * 大队名称
     */
    private String brigadeId;

    /**
     * 日期
     */
    private Date date;

}
