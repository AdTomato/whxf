package com.authine.cloudpivot.web.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 大队值班信息
 *
 * @author wangyong
 * @time 2020/5/18 13:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrigadeDutyInfo extends BaseEntity{

    /**
     * 大队id
     */
    private String brigadeId;

    /**
     * 时间
     */
    private Date date;

    private String userName1;

    private String userName2;

    private String userName3;

    private String userName4;

    private String userName5;

    private String userName6;

}
