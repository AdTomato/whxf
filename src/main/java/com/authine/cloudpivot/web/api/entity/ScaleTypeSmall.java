package com.authine.cloudpivot.web.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 量表测评二级分类
 * @Author Ke LongHai
 * @Date 2020/7/30 15:30
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScaleTypeSmall {

    //二级分类名称
    private String smallId;

    //二级分类名称
    private String smallTypeName;

}
