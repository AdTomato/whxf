package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.entity.DrawRecord;
import com.authine.cloudpivot.web.api.mapper.DrawRecordMapper;
import com.authine.cloudpivot.web.api.service.DrawRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: wangyong
 * @time: 2020/4/27 10:29
 * @Description:
 */
@Service
public class DrawRecordServiceImpl implements DrawRecordService {

    @Resource
    DrawRecordMapper drawRecordMapper;

    /**
     * 存储抽签记录
     *
     * @param drawResult 抽签记录
     * @author wangyong
     */
    @Override
    public void saveDrawResult(DrawRecord drawResult) {
        drawRecordMapper.saveDrawResult(drawResult);
    }
}
