package com.authine.cloudpivot.web.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author weiyao
 * @Date 2020/8/19
 * @desc 全员考评返回用户基本信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoByCheck {

    private String userId;//用户云枢Id
    private String dduserId;//用户钉钉Id

    private String userName;//用户名
    private String userSex;//性别
    private Integer age;//年龄
    private String birthday;//生日

    private String deptId;//部门Id
    private String deptName;//部门名称

    private String role;//角色
    private String position;//职务

    private String imgUrl;//头像




}
