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


    /**
     * 获得量表测评信息
     *
     * @param id id
     * @return {@link List<ScaleTest>}
     */
    List<ScaleTest> getScaleTestId(String id);

    /**
     * 获取图片详情
     *
     * @param id id
     * @return {@link String}
     */
    String getImgUrl(String id);






}
