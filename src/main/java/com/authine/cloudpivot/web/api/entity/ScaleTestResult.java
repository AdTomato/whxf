package com.authine.cloudpivot.web.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 子表量表测评结果
 * @Author Ke LongHai
 * @Date 2020/7/30 15:32
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScaleTestResult extends BaseEntity{

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

    //最低分
    private String min_score;

    //最高分
    private String max_score;

    //结果
    private String result;




}
