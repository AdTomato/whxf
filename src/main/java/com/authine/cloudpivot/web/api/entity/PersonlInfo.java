package com.authine.cloudpivot.web.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
    List<String> userNames1;
    List<String> userNames2;
    List<String> userNames3;
    List<String> userNames4;
    List<String> userNames5;
    List<String> userNames6;
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
