package com.authine.cloudpivot.web.api.entity;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 大队警情分析
 *
 * @author wangyong
 * @time 2020/5/18 11:17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrigadeAlertInfoAnalysis {

    /**
     * 大队id
     */
    private String brigadeId;

    /**
     * 日期
     */
    private Date date;

    /**
     * 每日警情数据
     */
    Map<String, Integer> dateAlertInfo;

    /**
     * 月度警情分析
     */
    List<Map<String, Object>> monthAlertAnalysis;

    /**
     * 月度街道警情
     */
//    Map<String, Integer> monthStreetAlert;

    /**
     * 街道列表
     */
    List<String> streets;

    /**
     * 警情列表
     */
    List<Integer> alertNums;

}
