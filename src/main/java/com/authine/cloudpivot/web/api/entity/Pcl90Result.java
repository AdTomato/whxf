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
public class Pcl90Result extends  BaseEntity {

    private String testName;  //测评人员
    private Date testDate;  //测评时间

    private Float scoreResult; //总分
    private String testResult;  //测评结果

    private Float  F1;
    private Float  F2;
    private Float  F3;
    private Float  F4;
    private Float  F5;
    private Float  F6;
    private Float  F7;
    private Float  F8;
    private Float  F9;
    private Float  F10;

    private String f1Remark;
    private String f2Remark;
    private String f3Remark;
    private String f4Remark;
    private String f5Remark;
    private String f6Remark;
    private String f7Remark;
    private String f8Remark;
    private String f9Remark;
    private String f10Remark;



    public Pcl90Result(Pcl90 pcl){
        //评测人员
        this.testName="[{\"id\":\""+pcl.getCreater()+"\",\"type\":3}]";
        this.setCreater(pcl.getCreater());
        this.setName(pcl.getName());
        this.setCreatedDeptId(pcl.getCreatedDeptId());
        this.setOwner(pcl.getOwner());
        this.setOwnerDeptId(pcl.getOwnerDeptId());
      //  this.setCreatedTime();
        this.setModifier(pcl.getModifier());
        this.setModifiedTime(pcl.getModifiedTime());
        this.setSequenceNo(pcl.getSequenceNo());
        this.setSequenceStatus(pcl.getSequenceStatus());
        this.setOwnerDeptQueryCode(pcl.getOwnerDeptQueryCode());
        String id= UUID.randomUUID().toString().replace("-","");
        this.setId(id);
    }

}
