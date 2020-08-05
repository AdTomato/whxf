package com.authine.cloudpivot.web.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 量表测评
 * @Author Ke LongHai
 * @Date 2020/7/30 15:30
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScaleTest extends BaseEntity implements Serializable {

    //标题
    private String title;

    //描述
    private String description;

    //图标
    private String imgUrl;

    //测评数量
    private Integer testNum;

    //作者
    private String author;

    //一级分类名称
    private String bigTypeName;

    //二级分类名称
    private String smallTypeName;



}
