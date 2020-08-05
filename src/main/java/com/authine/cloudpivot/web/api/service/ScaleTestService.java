package com.authine.cloudpivot.web.api.service;

import com.authine.cloudpivot.web.api.entity.ScaleTest;
import com.authine.cloudpivot.web.api.entity.ScaleTypeSmall;

import java.util.List;

/**
 * @Author Ke LongHai
 * @Date 2020/7/31 15:56
 * @Version 1.0
 */
public interface ScaleTestService {

    List<ScaleTest> getScaleTestOne(String id);


    List<ScaleTest> getScaleTestId(String id);


}
