package com.authine.cloudpivot.web.api.mapper;

import com.authine.cloudpivot.web.api.entity.QuantiAssessment;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/*
 @author:weiyao
 @time:2020-05-15
 @desc:量化考评周报
  */
public interface QuantiAssessmentMapper {

    //获取信息
    List<QuantiAssessment> getAssess(@Param("stationId")String stationId);

    //更新
    void updateQuantiAssessmentById(QuantiAssessment quantiAssessment);

    //查询部门集合当月
    List<String> getTeamforMonth(@Param("stationId")String stationId);

    //根据部门名查询姓名集合
    List<String> getNameforTeam(@Param("stationId")String stationId,@Param("team")String team);

    // <!--根据姓名查询各项分数总和-->
    Integer getSumScoreforNameCurrentMonth(@Param("stationId")String stationId,@Param("evaluationName")String evaluationName,@Param("project")String project);

    // <!--根据姓名查询各项分数总和上月-->
    Integer getSumScoreforNameLastMonth(@Param("stationId")String stationId,@Param("evaluationName")String evaluationName);
}
