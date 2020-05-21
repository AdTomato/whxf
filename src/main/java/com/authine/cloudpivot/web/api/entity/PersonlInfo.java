package com.authine.cloudpivot.web.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: weiyao
 * @time: 2020/5/11
 * @Description:武汉消防 大屏功能 人员动态模块信息
 */
@Data
@AllArgsConstructor
public class PersonlInfo {

    //总人数
    private Integer numAll;
    //指挥员人数（干部人数）
    private Integer numtype1;
    //消防员人数（文员人数）
    private Integer numtype2;
    //在岗人数
    private Integer numZaigang;
    //公差人数
    private Integer numGongchai;
    //休假人数
    private Integer numXiujia;

    //详细人员集合
    List<BaseEntity> userNames1;
    List<BaseEntity> userNames2;
    List<BaseEntity> userNames3;
    List<BaseEntity> userNames4;
    List<BaseEntity> userNames5;
    List<BaseEntity> userNames6;
    //生日集合
    List<String> birthdayNames;
    //公告信息
    List<Notice> notice;


    public  PersonlInfo(){
        this.numAll=0;

        this.numtype1=0;

        this.numtype2=0;

        this.numZaigang=0;

        this.numGongchai=0;

        this.numXiujia=0;

        this.userNames1=new ArrayList<>();
        this.userNames2=new ArrayList<>();
        this.userNames3=new ArrayList<>();
        this.userNames4=new ArrayList<>();
        this.userNames5=new ArrayList<>();
        this.userNames6=new ArrayList<>();
        this.birthdayNames=new ArrayList<>();
        this.notice=new ArrayList<>();
    }


}
