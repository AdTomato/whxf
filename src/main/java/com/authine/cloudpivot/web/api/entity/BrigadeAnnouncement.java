package com.authine.cloudpivot.web.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangyong
 * @time 2020/5/21 16:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrigadeAnnouncement extends BaseEntity {

    /**
     * 大队id
     */
    private String brigadeId;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String announcement;

    /**
     * 是否关闭
     */
    private Boolean isClose;


}
