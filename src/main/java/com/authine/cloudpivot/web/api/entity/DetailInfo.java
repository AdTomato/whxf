package com.authine.cloudpivot.web.api.entity;

import lombok.*;

import java.io.Serializable;

/**
 * 龙虎榜中子表的成绩信息
 *
 * @author Ke Longhai
 * @time 2020/5/15
 */

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class DetailInfo  {

    /**
     * id
     */
    private String id;

    /**
     * 排序字段
     */
    private Double sortKey;

    /**
     * 子表数据的父id
     */
    private String parentId;

    /**
     * 训练科目
     */
    private String trainSubject;

    /**
     * 姓名
     */
    private String trainName;

    /**
     * 成绩
     */
    private String result;


}
