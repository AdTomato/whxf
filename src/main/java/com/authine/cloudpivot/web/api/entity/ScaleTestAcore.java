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
private Date  testTime;//测评时间
private String  testDetail; //测评明细
private String     danger;//心理危机程度

private Integer  testScore;  //分数

}
