package com.authine.cloudpivot.web.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

    /*
    团队心理档案
    weiyao
     **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamRecord {

    private String  teamName;//部门名称
    private Integer allNum;//总人数
    private Integer cycpNum;//参与测评人数
    private Integer wjyjNum;//危机预警总人数
    private Integer wjyj0;//健康人数
    private Integer wjyj1;//轻度人数
    private Integer wjyj2;//中度人数
    private Integer wjyj3;//重度人数
    private Integer wcyNum;//部门未参与量表测评人数
    private Integer xlsdNum;//参与心理疏导人数


}
