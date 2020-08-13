package com.authine.cloudpivot.web.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HOrgUser {

    private String id;

    private Date createdTime;

    private String creater;

    private Boolean deleted;

    private String extend1;

    private String extend2;

    private String extend3;

    private Integer extend4;

    private Integer extend5;

    private Date modifiedTime;

    private String modifier;

    private String remarks;

    private Boolean active;

    private Boolean admin;

    private String appellation;

    private Date birthday;

    private Boolean boss;

    private String departmentId;

    private Date departureDate;

    private String dingtalkId;

    private String email;

    private String employeeNo;

    private Integer employeeRank;

    private Date entryDate;

    private String gender;

    private String identityNo;

    private String imgUrl;

    private Boolean leader;

    private String managerId;

    private String mobile;

    private String name;

    private String officePhone;

    private String password;

    private String username;

    private String privacyLevel;

    private String secretaryId;

    private Long sortKey;

    private String sourceId;

    //private String sourceParentId;

    private String status;

    private String userId;


}