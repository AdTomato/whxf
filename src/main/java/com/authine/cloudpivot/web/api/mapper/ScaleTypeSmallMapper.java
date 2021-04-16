package com.authine.cloudpivot.web.api.mapper;

import com.authine.cloudpivot.web.api.entity.ScaleTypeBig;
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
public interface ScaleTypeSmallMapper {

    /**
     * 获取量表测评小类
     *
     * @return {@link List<ScaleTypeSmall>}
     */
    List<ScaleTypeSmall> getScaleTypeSmallOne();

    /**
     * 获取量表测评大类
     *
     * @return {@link List<ScaleTypeBig>}
     */
    List<ScaleTypeBig> getScaleTypeSmallAll();



}
