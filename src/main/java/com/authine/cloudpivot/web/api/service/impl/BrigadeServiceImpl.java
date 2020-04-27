package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.mapper.BrigadeMapper;
import com.authine.cloudpivot.web.api.service.BrigadeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author: wangyong
 * @time: 2020/4/26 14:15
 * @Description: 大队service 接口
 */
@Service
public class BrigadeServiceImpl implements BrigadeService {

    @Resource
    BrigadeMapper brigadeMapper;

    /**
     * 根据抽签项目id获取大队id以及大队名称列表
     *
     * @param lotteryId 抽签项目id
     * @return 大队id，大队名称列表
     * @author wangyong
     */
    @Override
    public List<Map<String, String>> getBrigadeNameAndId(String lotteryId) {
        return brigadeMapper.getBrigadeNameAndId(lotteryId);
    }
}
