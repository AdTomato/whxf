package com.authine.cloudpivot.web.api.service;

import java.util.List;
import java.util.Map;

/**
 * @author: wangyong
 * @time: 2020/4/26 14:05
 * @Description:
 */
public interface LotteryService {

    /**
     * 获取抽签项目id以及项目名称
     *
     * @return 项目id以及项目名称
     * @author wangyong
     */
    List<Map<String, String>> getLotteryNameAndId();

}
