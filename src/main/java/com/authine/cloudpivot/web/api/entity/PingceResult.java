package com.authine.cloudpivot.web.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

//武汉消防心里测评 结果 表
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PingceResult extends  BaseEntity {

    private Float yiyuScore; //抑郁测评分数
    private Float jiaolvScore; //焦虑测评分数
    private Float scoreResult; //总分
    private String testName;  //测评人员
    private String testResult;  //测评结果
    private Date yiyutestDate;  //抑郁测评时间
    private Date jiaolvTestDate; //焦虑测评时间


    public PingceResult(XinliSas xinliSas){
        //评测人员
        this.testName="[{\"id\":\""+xinliSas.getCreater()+"\",\"type\":3}]";
        this.setCreater(xinliSas.getCreater());
        this.setName(xinliSas.getName());
        this.setCreatedDeptId(xinliSas.getCreatedDeptId());
        this.setOwner(xinliSas.getOwner());
        this.setOwnerDeptId(xinliSas.getOwnerDeptId());
      //  this.setCreatedTime();
        this.setModifier(xinliSas.getModifier());
        this.setModifiedTime(xinliSas.getModifiedTime());
        this.setSequenceNo(xinliSas.getSequenceNo());
        this.setSequenceStatus(xinliSas.getSequenceStatus());
        this.setOwnerDeptQueryCode(xinliSas.getOwnerDeptQueryCode());
        String id= UUID.randomUUID().toString().replace("-","");
        this.setId(id);
    }

}
