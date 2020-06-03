package com.authine.cloudpivot.web.api.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

//角色请假信息
//weiyao
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleVacationInfo extends BaseEntity {

    //角色姓名
    private String roleName;
    //统计日期
    private Date vacationDate;
    //总人数
    private Integer allroleNum;
    //请假人数
    private Integer vacationNum;
    //在岗人数
    private Integer inworkNum;

    /*
    子表请假人员信息
     */
    //请假人姓名
    private String vacationName;
    //请假人钉钉Id
    private String userId;

    private String parentId;

    private Integer sortKey;
}
