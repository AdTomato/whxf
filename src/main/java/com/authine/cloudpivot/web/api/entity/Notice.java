package com.authine.cloudpivot.web.api.entity;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 公告
 */
@Data
public class Notice {

    private String id;
    
    /**
     * 发布人员姓名
     */
    @ApiModelProperty(value = "发布人员姓名")
    private String gtmName;


    /**
     * 公告创建时间
     */
    @ApiModelProperty(value = "公告创建时间")
    private Date gmt_create;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String title;


    /**
     * 公告地址
     */
    @ApiModelProperty(value = "公告地址")
    private String url;
}
