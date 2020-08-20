package com.authine.cloudpivot.web.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author Ke LongHai
 * @Date 2020/8/18 17:31
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkInfo extends BaseEntity{


    //发布日期
    private Date date;

    //计划名称
    private String planName;

    //计划内容
    private String planInfo;

    //发布单位
    private String publishName;

    //评定结果（按时上报可评定为好）
    private String evaluate;
}
