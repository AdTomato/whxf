package com.authine.cloudpivot.web.api.service;

import com.authine.cloudpivot.web.api.entity.DrawRecord;

/**
 * @author: wangyong
 * @time: 2020/4/27 10:29
 * @Description:
 */
public interface DrawRecordService {

    /**
     * 存储抽签记录
     *
     * @param drawResult 抽签记录
     * @author wangyong
     */
    void saveDrawResult(DrawRecord drawResult);

}
