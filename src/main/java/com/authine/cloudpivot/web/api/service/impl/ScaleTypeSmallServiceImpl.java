package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.entity.ScaleTypeBig;
import com.authine.cloudpivot.web.api.entity.ScaleTypeSmall;
import com.authine.cloudpivot.web.api.mapper.ScaleTypeSmallMapper;
import com.authine.cloudpivot.web.api.service.ScaleTypeSmallService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Ke LongHai
 * @Date 2020/7/31 15:57
 * @Version 1.0
 */
@Service
public class ScaleTypeSmallServiceImpl implements ScaleTypeSmallService {

    @Resource
    ScaleTypeSmallMapper scaleTypeMapper;

    @Override
    public List<ScaleTypeSmall> getScaleTypeOne() {
        return scaleTypeMapper.getScaleTypeSmallOne();
    }

    @Override
    public List<ScaleTypeBig> getScaleTypeAll() {
        return scaleTypeMapper.getScaleTypeSmallAll();
    }
}
