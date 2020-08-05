package com.authine.cloudpivot.web.api.mapper;

import com.authine.cloudpivot.web.api.entity.ScaleTestAcore;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author: weiyao
 * @time: 2020/8/1
 * @Description: 心理咨询系统--测评结果
 */
public interface ScaleTestResultMapper {

    /**
     * 根据分数得出测评结果
     */
    public String getResultByScore(@Param("parentId")String parentId,@Param("score") Integer score);

    //插入量表测评结果
    Integer insertScaleTestAcore(ScaleTestAcore info);

    //根据钉钉Id 查询用户id
    String getIdByddId(@Param("userId") String userId);

    //查询结果
    List<ScaleTestAcore> getScaleTestResultInfo(ScaleTestAcore scaleTestAcore);

    //测试json
    String getoptionResult(String id);

}
