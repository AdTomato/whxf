package com.authine.cloudpivot.web.api.entity;

import com.github.javafaker.Bool;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.omg.CORBA.OBJ_ADAPTER;

/**
 * 大屏的权限
 *
 * @author wangyong
 * @time 2020/5/13 11:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LargeScreenAuthority {

    /**
     * 是否为支队权限
     */
    private Boolean isDetachment;

    /**
     * 是否为大队权限
     */
    private Boolean isBrigade;

    /**
     * 是否为消防站权限
     */
    private Boolean isStation;

    /**
     * 支队数据
     */
    private Object detachmentData;

    /**
     * 大队数据
     */
    private Object brigadeData;

    /**
     * 消防站数据
     */
    private Object stationData;

}
