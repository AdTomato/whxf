package com.authine.cloudpivot.web.api.mapper;

import com.authine.cloudpivot.web.api.entity.DrawRecord;

/**
 * @author: wangyong
 * @time: 2020/4/27 10:24
 * @Description: 抽签记录
 */
public interface DrawRecordMapper {

    /**
     * 存储抽签记录
     *
     * @param drawResult 抽签记录
     * @author wangyong
     */
    void saveDrawResult(DrawRecord drawResult);

}
