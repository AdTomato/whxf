package com.authine.cloudpivot.web.api.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * @author: weiyao
 * @time: 2020/5/08
 * @Description: 钉钉创建日程
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCalendarVo implements Serializable {
    private static final long serialVersionUID = -4568928073579442976L;
    /**
     * 创建者userid
     */
    @ApiModelProperty(value="创建者userId")
    private String  creator_userid;

    /**
     * 日程主题
     */
    @ApiModelProperty(value="日程主题")
    private String  summary ;

    /**
     * 开始时间
     */
    @ApiModelProperty(value="开始时间")
    private Date start_time;

    /**
     *  结束时间
     */
    @ApiModelProperty(value="结束时间")
    private Date end_time;

    /**
     * 开始前多久提醒（分钟）
     */
    @ApiModelProperty(value="开始前多久提醒（分钟）")
    private Integer minutes;


    /**
     * 地点
     */
    @ApiModelProperty(value="地点")
    private String location;

    /**
     * 接收者
     */
    @ApiModelProperty(value="接收者")
    private List<String> receiver_userids;

    /**
     * 日程来源
     */
    @ApiModelProperty(value="日程来源")
    private String title;

    /**
     * 点击日程跳转目标地址
     */
    private String url;

    /**
     * 备注
     */
    @ApiModelProperty(value="备注")
    private String description;


}
