package com.authine.cloudpivot.web.api.mapper;

import com.authine.cloudpivot.web.api.entity.ScaleConsultDetail;
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
    public List<Map<String,String>> getResultByScore(@Param("parentId")String parentId,@Param("score") Integer score);

    //插入量表测评结果
    Integer insertScaleTestAcore(ScaleTestAcore info);

    //根据钉钉Id 查询用户id
    String getIdByddId(@Param("userId") String userId);

    //根据钉钉Id 查询用户部门id
    String getDeptIdByddId(@Param("deptId") String deptId);

    //查询结果
    List<ScaleTestAcore> getScaleTestResultInfo(ScaleTestAcore scaleTestAcore);

    //测试json
    String getoptionResult(String id);

    List<ScaleConsultDetail>  getScaleConsultDetail(@Param("deptId")String deptId,@Param("userId")String userId);

    List<ScaleTestAcore> getScaleAcoreByUserId(@Param("userId") String userId);

    //单个用户，没有咨询记录
    List<ScaleConsultDetail> getScaleConsultDetailByPerson(@Param("userId") String userId);

    //查询部门下人员Id集合
    List<String> getDeptUserIdList(@Param("deptId") String deptId);

    //查询测评人数
    Integer getcepingNum(@Param("deptId") String deptId,@Param("danger") String danger);
    //查询疏导人数
    Integer getshudaoNum(@Param("deptId") String deptId);

    //查询部门名称
    String getdeptName(@Param("deptId") String deptId);

}
