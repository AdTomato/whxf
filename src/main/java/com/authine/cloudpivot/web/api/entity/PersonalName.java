package com.authine.cloudpivot.web.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: wangyong
 * @time: 2020/4/26 14:26
 * @Description: 抽签人员名称
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalName extends BaseEntity {

    private String brigadeName;
    private String jobTitle;
    private String personalName;

}
