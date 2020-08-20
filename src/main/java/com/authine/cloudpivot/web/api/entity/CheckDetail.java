package com.authine.cloudpivot.web.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Ke LongHai
 * @Date 2020/8/18 17:39
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckDetail {

    private String id;

    private String parentId;

    //项目
    private String projectName;

    //结果
    private String result;

}
