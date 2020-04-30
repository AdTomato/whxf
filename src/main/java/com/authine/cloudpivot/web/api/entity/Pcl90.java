package com.authine.cloudpivot.web.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//武汉消防PCL90心里测评表
//weiyao
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pcl90 extends BaseEntity {

    private Float  count;  //总分

    /*
    因子分：
    (1)躯体化:
    (2)强迫症状:
    (3)人际关系敏
    (4)抑郁:
    (5)焦虑:
    (6)敌对:
    (7)恐怖:
    (8)偏执:
    (9)精神病性:
   （10）睡眠和饮食
     */
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

}
