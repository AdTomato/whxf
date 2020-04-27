package com.authine.cloudpivot.web.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: wangyong
 * @time: 2020/4/27 10:19
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrawRecord extends BaseEntity{

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 大队名称
     */
    private String brigadeName;

    /**
     * 抽签时间
     */
    private Date drawDate;

    /**
     * 考核结果
     */
    private String drawResult;

}
