package com.authine.cloudpivot.web.api.mapper;

import java.util.List;
import java.util.Map;

/**
 * @author: wangyong
 * @time: 2020/4/26 14:02
 * @Description: 抽签项目
 */
public interface LotteryMapper {

    /**
     * 获取抽签项目id以及项目名称
     *
     * @return 项目id以及项目名称
     * @author wangyong
     */
    List<Map<String, String>> getLotteryNameAndId();

}
