package com.authine.cloudpivot.web.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

//档案--咨询疏导记录 weiyao
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScaleConsultDetail extends BaseEntity {

    // owner 是咨询师
    private String  sonsultUser;//咨询人


    private String  dept;//所在部门
    private String  familyInfo; //家族遗传信息
    private Date  date;//测评时间
    private String  consultDetail; //综合分析
    private String     scaleResult;//心理测评结果
    private String  advice; //建议

    private String     ownerName;//咨询师姓名
    private String     consultType;//咨询类型

    private Float  timeNum;  //咨询时长
    private String zixunResult;//咨询结果


    //用户信息
    private String  sonsultUserDdId;//咨询人钉钉Id
    private String     userName;//用户姓名
    private String     userSex;//用户性别
    private Date     birthday;//用户生日
    private String     userImgUrl;//用户头像
    private String     identityNo;//身份证号码

    private String     userdeptName;//用户部门名称
    private String     userdeptId;//用户部门Id


    private List<ScaleTestAcore> scaleTestAcoreList;//量表测评大小类名称



}
