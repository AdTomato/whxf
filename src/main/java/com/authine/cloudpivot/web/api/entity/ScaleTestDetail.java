package com.authine.cloudpivot.web.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 子表量表测评内容
 * @Author Ke LongHai
 * @Date 2020/8/1 14:25
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScaleTestDetail extends BaseEntity{

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

    //标题
    private String topic;

    //选项及结果
    private String optionResult;

    //选项及结果集合
    private List<OptionAndScore> optionAndScoreList;

}
