package com.authine.cloudpivot.web.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 心理危机上报
 * @Author Ke LongHai
 * @Date 2020/8/12 11:38
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadDanger extends BaseEntity implements Serializable {


    //上报时间
    private Date upTime;

    //上报人
    private String upUser;
    ;
    //心理问题描述(症状+持续时间)
    private String detail;

    //钉钉id
    private String userId;

}
