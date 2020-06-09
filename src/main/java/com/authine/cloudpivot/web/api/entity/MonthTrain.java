package com.authine.cloudpivot.web.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 月度训练登记
 *
 * @author weiyao
 * @time 2020/6/8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthTrain extends BaseEntity {
    private String dept;//部门
    private Date trainDate;//日期
    private String leaderName;//主管人员
    private Integer numAll;//总人数
    private Float NumberCancun1;
    private Float canxunProb1;
    private Float hegeProb1;
    private Float youxiuProb1;
    private Float NumberCancun2;
    private Float canxunProb2;
    private Float hegeProb2;
    private Float youxiuProb2;
    private Float NumberCancun3;
    private Float canxunProb3;
    private Float hegeProb3;
    private Float youxiuProb3;
    private Float NumberCancun4;
    private Float canxunProb4;
    private Float hegeProb4;
    private Float youxiuProb4;
    private Float NumberCancun5;
    private Float canxunProb5;
    private Float hegeProb5;
    private Float youxiuProb5;
    private Float NumberCancun6;
    private Float canxunProb6;
    private Float hegeProb6;
    private Float youxiuProb6;
    //中队
    private Float NumberCancun7;
    private Float canxunProb7;
    private Float hegeProb7;
    private Float youxiuProb7;
    private Float NumberCancun8;
    private Float canxunProb8;
    private Float hegeProb8;
    private Float youxiuProb8;
    private Float NumberCancun9;
    private Float canxunProb9;
    private Float hegeProb9;
    private Float youxiuProb9;
    //统计
    private Integer numBigPerson;//大队人数
    private Integer numCenPerson;//中队人数
    private Float BNumberCancun1;
    private Float BcanxunProb1;
    private Float BhegeProb1;
    private Float ByouxiuProb1;
    private Float BNumberCancun2;
    private Float BcanxunProb2;
    private Float BhegeProb2;
    private Float ByouxiuProb2;
    private Float BNumberCancun3;
    private Float BcanxunProb3;
    private Float BhegeProb3;
    private Float ByouxiuProb3;
    private Float BNumberCancun4;
    private Float BcanxunProb4;
    private Float BhegeProb4;
    private Float ByouxiuProb4;
    private Float BNumberCancun5;
    private Float BcanxunProb5;
    private Float BhegeProb5;
    private Float ByouxiuProb5;
    private Float BNumberCancun6;
    private Float BcanxunProb6;
    private Float BhegeProb6;
    private Float ByouxiuProb6;

    public MonthTrain(MonthTrain mt){
        this.BNumberCancun1=mt.getNumberCancun1();
        this.BcanxunProb1=mt.getCanxunProb1();
        this.BhegeProb1=mt.getHegeProb1();
        this.ByouxiuProb1=mt.getYouxiuProb1();
        this.BNumberCancun2=mt.getNumberCancun2();
        this.BcanxunProb2=mt.getCanxunProb2();
        this.BhegeProb2=mt.getHegeProb2();
        this.ByouxiuProb2=mt.getYouxiuProb2();
        this.BNumberCancun3=mt.getNumberCancun3();
        this.BcanxunProb3=mt.getCanxunProb3();
        this.BhegeProb3=mt.getHegeProb3();
        this.ByouxiuProb3=mt.getYouxiuProb3();
        this.BNumberCancun4=mt.getNumberCancun4();
        this.BcanxunProb4=mt.getCanxunProb4();
        this.BhegeProb4=mt.getHegeProb4();
        this.ByouxiuProb4=mt.getYouxiuProb4();
        this.BNumberCancun5=mt.getNumberCancun5();
        this.BcanxunProb5=mt.getCanxunProb5();
        this.BhegeProb5=mt.getHegeProb5();
        this.ByouxiuProb5=mt.getYouxiuProb5();
        this.BNumberCancun6=mt.getNumberCancun6();
        this.BcanxunProb6=mt.getCanxunProb6();
        this.BhegeProb6=mt.getHegeProb6();
        this.ByouxiuProb6=mt.getYouxiuProb6();

    }
}
