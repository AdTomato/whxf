package com.authine.cloudpivot.web.api.service;

import com.authine.cloudpivot.web.api.entity.ScaleTypeBig;
import com.authine.cloudpivot.web.api.entity.ScaleTypeSmall;

import java.util.List;

/**
 * @Author Ke LongHai
 * @Date 2020/7/31 15:56
 * @Version 1.0
 */
public interface ScaleTypeSmallService {

    List<ScaleTypeSmall> getScaleTypeOne();

    List<ScaleTypeBig> getScaleTypeAll();
}
