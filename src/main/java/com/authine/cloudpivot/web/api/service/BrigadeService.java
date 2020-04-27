package com.authine.cloudpivot.web.api.service;

import java.util.List;
import java.util.Map;

/**
 * @author: wangyong
 * @time: 2020/4/26 14:13
 * @Description: 大队service
 */
public interface BrigadeService {

    /**
     * 根据抽签项目id获取大队id以及大队名称列表
     *
     * @param lotteryId 抽签项目id
     * @return 大队id，大队名称列表
     * @author wangyong
     */
    List<Map<String, String>> getBrigadeNameAndId(String lotteryId);

}
