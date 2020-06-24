package com.authine.cloudpivot.web.api.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
@Time 2020-06-19
@ Author: weiyao
@ desc: 执勤实力表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Zhiqingshili {

    private String id;
    //部门名称
    private String deptName;
    //部门控件json
    private String dept;
    //执勤人数--总计（人）
    private Float numAll1;
    //执勤车辆--车辆数（两）
    private Float numAll2;
    //灭火药剂--总量（吨）
    private Float numMhAll;
    //灭火药剂--水（吨）
    private Float numWater;
    //灭火药剂--泡沫（吨）
    private Float numPaomo;
    //灭火药剂--干粉（吨）
    private Float numGanfen;

    //战斗编成--》编成数
    private Float numBianchen;
    //战斗编成--》水枪数
    private Float numSq;
    //出枪数量（把）
    private Float numChuqiang;
    //供水能力--高度
    private Float numGsHigh;
    //供水能力--长度
    private Float numGsWidth;
    //登高能力
    private Float heigth;

}
