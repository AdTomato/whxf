package com.authine.cloudpivot.web.api.mapper;

import com.authine.cloudpivot.web.api.dto.ScaleTestList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 量表测评分类mapper
 * @Author Ke LongHai
 * @Date 2020/7/31 15:35
 * @Version 1.0
 */
@Mapper
public interface ScaleTestListMapper {

    List<String> getScaleTestDetailByParentId(String parentId);

    List<String> getScaleTestResultByParentId(String parentId);

    List<String> getScaleTestAcoresByParentId(String parentId);

    List<ScaleTestList> getScaleTestList(String id);


}
