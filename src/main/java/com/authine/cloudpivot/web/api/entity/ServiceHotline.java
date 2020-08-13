package com.authine.cloudpivot.web.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author Ke LongHai
 * @Date 2020/8/12 11:38
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceHotline  extends BaseEntity implements Serializable {


    //所属部门
    private String departmentOfChoice;

    //部门名称
    private String contactAddress;
    ;
    //联系方式
    private String contactWay;

}
