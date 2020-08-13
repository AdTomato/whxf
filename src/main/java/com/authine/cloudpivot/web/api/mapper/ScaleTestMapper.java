package com.authine.cloudpivot.web.api.mapper;

import com.authine.cloudpivot.web.api.entity.ScaleTest;
import com.authine.cloudpivot.web.api.entity.ScaleTypeSmall;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 量表测评分类mapper
 * @Author Ke LongHai
 * @Date 2020/7/31 15:35
 * @Version 1.0
 */
@Mapper
public interface ScaleTestMapper {

    List<ScaleTest> getScaleTestOne(String id);


    List<ScaleTest> getScaleTestId(String id);

    String getImgUrl(String id);






}
