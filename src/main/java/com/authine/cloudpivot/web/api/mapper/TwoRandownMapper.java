package com.authine.cloudpivot.web.api.mapper;

import com.authine.cloudpivot.web.api.entity.TwoRandownInfo;

import java.util.List;
import java.util.Map;

/**
 * @author: weiyao
 * @time: 2020/5/18
 * @Description: 双随机公开
 */
public interface TwoRandownMapper {

    /**
     * 根据双随机公开数据
     */
    TwoRandownInfo getTwoRandownInfo(String brigadeId);

    void updateTwoRandownInfo(TwoRandownInfo twoRandownInfo);

}
