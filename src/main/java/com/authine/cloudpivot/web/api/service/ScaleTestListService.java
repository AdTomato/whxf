package com.authine.cloudpivot.web.api.service;

import com.authine.cloudpivot.web.api.dto.ScaleTestList;
import com.authine.cloudpivot.web.api.entity.ScaleTest;
import com.authine.cloudpivot.web.api.entity.ScaleTestAcore;

import java.util.List;

/**
 * @Author Ke LongHai
 * @Date 2020/7/31 15:56
 * @Version 1.0
 */
public interface ScaleTestListService {

    List<ScaleTestList> getScaleTestList(String id);


}
