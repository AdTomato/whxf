package com.authine.cloudpivot.web.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 月度训练登记-个人详情
 *
 * @author weiyao
 * @time 2020/6/10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthTrainPerson extends BaseEntity {
    private String dept;//部门
    private Date trainDate;//日期

    private String trainNames;//姓名
    private String position;//职别
    private Integer age;//年龄
    private String parentId;
    private Integer sortKey;

    //中队
    private String grade1;//成绩
    private String standard1;//及格标准
    private Float score1;//得分
    private String grade2;
    private String standard2;
    private Float score2;
    private String grade3 ;
    private String standard3 ;
    private Float score3 ;
    private String grade4 ;
    private String standard4 ;
    private Float score4 ;
    private String grade5 ;
    private String standard5 ;
    private Float score5 ;
    private String grade6 ;
    private String standard6 ;
    private Float score6 ;
    private String grade7 ;
    private String standard7 ;
    private Float score7 ;
    private String grade8 ;
    private String standard8 ;
    private Float score8 ;
    private String grade9 ;
    private String standard9 ;
    private Float score9 ;

    //大队
    private String Bgrade1;//成绩
    private String Bstandard1;//及格标准
    private Float  Bscore1;//得分
    private String Bgrade2;
    private String Bstandard2;
    private Float  Bscore2;
    private String Bgrade3 ;
    private String Bstandard3 ;
    private Float  Bscore3 ;
    private String Bgrade4 ;
    private String Bstandard4 ;
    private Float  Bscore4 ;
    private String Bgrade5 ;
    private String Bstandard5 ;
    private Float  Bscore5 ;
    private String Bgrade6 ;
    private String Bstandard6 ;
    private Float  Bscore6 ;

    public MonthTrainPerson(MonthTrainPerson mp){
        this.Bgrade1=mp.getGrade1();//成绩
        this.Bstandard1=mp.getStandard1();//及格标准
        this.Bscore1=mp.getScore1();//得分
        this.Bgrade2=mp.getGrade2();
        this.Bstandard2=mp.getStandard2();
        this.Bscore2=mp.getScore2();
        this.Bgrade3=mp.getGrade3() ;
        this.Bstandard3=mp.getStandard3() ;
        this.Bscore3=mp.getScore3() ;
        this.Bgrade4=mp.getGrade4() ;
        this.Bstandard4=mp.getStandard4() ;
        this.Bscore4 =mp.getScore4();
        this.Bgrade5=mp.getGrade5() ;
        this.Bstandard5=mp.getStandard5() ;
        this.Bscore5=mp.getScore5() ;
        this.Bgrade6 =mp.getGrade6();
        this.Bstandard6=mp.getStandard6() ;
        this.Bscore6=mp.getScore6() ;

        this.trainNames=mp.getTrainNames();
        this.age=mp.getAge();
        this.position=mp.getPosition();
    }

}
