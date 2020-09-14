package com.authine.cloudpivot.web.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author weiyao
 * @Date 2020/9/9
 * @desc
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CePingUserInfo {

    private String deptId;//部门id
    private String parentId;//

    private String name;//部门名称

    private Integer count;//总数
    private Integer leaf;//0 有子部门 1 无子部门

    private Integer personCount;//部门总人数

    private float prob;//使用率 ==count/personCount





}
