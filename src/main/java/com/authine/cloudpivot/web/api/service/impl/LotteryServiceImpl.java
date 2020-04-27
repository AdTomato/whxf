package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.mapper.LotteryMapper;
import com.authine.cloudpivot.web.api.service.LotteryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author: wangyong
 * @time: 2020/4/26 14:07
 * @Description: 抽签项目模块
 */
@Service
public class LotteryServiceImpl implements LotteryService {

    @Resource
    LotteryMapper lotteryMapper;

    /**
     * 获取抽签项目id以及项目名称
     *
     * @return 项目id以及项目名称
     * @author wangyong
     */
    @Override
    public List<Map<String, String>>  getLotteryNameAndId() {
        return lotteryMapper.getLotteryNameAndId();
    }
}
