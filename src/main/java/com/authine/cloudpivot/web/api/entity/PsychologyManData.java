package com.authine.cloudpivot.web.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 心理咨询师资料
 * @Author Ke LongHai
 * @Date 2020/8/5 12:39
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PsychologyManData extends BaseEntity implements Serializable {

    //用户
    private String counselorName;
    //姓名
    private String userName;
    //个人标签
    private String manTable;

   //职业资格
    private String jopQualification;
    //从业年限
    private String jobYear;
    //擅长
    private String beGood;
    //自我介绍
    private String oneSelf1;
    //平台荐语
    private String platformBlurb1;
    //联系方式
    private String contactWay;

    //照片
    private String photo;

}
