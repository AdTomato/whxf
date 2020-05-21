package com.authine.cloudpivot.web.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangyong
 * @time 2020/5/21 16:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StationAnnouncement extends BaseEntity{

    /**
     * 消防站id
     */
    private String stationId;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String announcementInfo;

    /**
     * 是否关闭
     */
    private String isClose;

}
