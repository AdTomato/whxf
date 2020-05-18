package com.authine.cloudpivot.web.api.entity;

import lombok.*;

/**
 *
 * 本周重点工作----工作重点子表
 * @Author Ke LongHai
 * @Date 2020/5/18 9:15
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class WeekFocus {

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
     * 工作内容
     */
    private String workContent;

    /**
     * 状态
     */
    private String status;



}
