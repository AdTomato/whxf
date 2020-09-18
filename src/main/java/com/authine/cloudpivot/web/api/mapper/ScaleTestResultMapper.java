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

    //根据用户Id 查询钉钉用户id
    String getDdIdByUserId(@Param("userId") String userId);

    //根据钉钉部门Id 查询用户部门id
    String getDeptIdByddId(@Param("deptId") String deptId);

    //查询是不是主管 1,是
    String getIsLeaderById(@Param("id") String id);
    //查询部门Id
    String getDeptIdByKey(@Param("id") String id);

    String getIsZhuGuan(@Param("roleId")String roleId,@Param("userId")String userId);

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

    //获取咨询人本身信息
    Map<String,String> getsendUserInfo(@Param("userId") String userId);

    //查询大队中队站队部门
    List<Map<String,String>> getDeptListByName(@Param("name") String name);//站队-站
    List<Map<String,String>> getDeptListByZD();//支队
    List<Map<String,String>> getDeptListByDD();//大队
    //查询用户所在部门
    List<Map<String,String>> getDeptByUserid(@Param("userid") String userid);

    //批量发布
    Integer updateResolved(List<String> ids);

}
