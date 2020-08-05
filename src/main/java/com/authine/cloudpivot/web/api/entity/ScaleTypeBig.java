package com.authine.cloudpivot.web.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 量表测评一级分类
 * @Author Ke LongHai
 * @Date 2020/7/30 15:18
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScaleTypeBig extends BaseEntity {

    //一级分类名称
    private String bigTypeName;

    private List<ScaleTypeSmall> scaleTypeSmallList;
}
