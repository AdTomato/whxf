package com.authine.cloudpivot.web.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

//心理咨询系统--测评结果 weiyao
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScaleTestAcore extends BaseEntity {
    
    private String  scaleTest;//量表测评Id
    private String  userId;//测评人
    private String  testResult; //测评结果
    private String   testTime;//测评时间 Date
    private String  testDetail; //测评明细
    private String     danger;//心理危机程度0,1234-0，没有影响，轻微，中度，严重，4，非常严重

    private Integer  testScore;  //分数
    private Integer     resolved;//是否已处理 1，是，0否（默认0）

    private String  userdept;//部门
    //用户信息
    private String     userName;//用户姓名
    private String     userSex;//用户性别
    private Integer     userAge;//用户年龄
    private String     userImgUrl;//用户头像
    private String     userdeptId;//用户部门Id
    private String     userdeptName;//用户部门名称
    private String  scaleTestName;//量表测评名称
    private String  smallTypeNameId;//量表类型id
    private String  smallTypeName;//量表类型namd


}
