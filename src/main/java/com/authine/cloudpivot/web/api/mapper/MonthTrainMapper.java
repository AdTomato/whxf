package com.authine.cloudpivot.web.api.mapper;


import com.authine.cloudpivot.web.api.entity.MonthTrain;
import com.authine.cloudpivot.web.api.entity.MonthTrainPerson;
import com.authine.cloudpivot.web.api.entity.PingceResult;
import com.authine.cloudpivot.web.api.entity.XinliSas;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: weiyao
 * @Date: 2020-06-8
 * @Description:
 */
public interface MonthTrainMapper {

    //查询大队训练结果
    MonthTrain getMonthTrainBig(String id);

    //查询中队训练结果
    MonthTrain getMonthTrainCen(String id);

    //查询是否有该月大队统计
    MonthTrain getMonthTrainBigProNum(@Param("trainDate") Date trainDate, @Param("dept")String dept);

    //查询是否有该月中队统计
    MonthTrain getMonthTrainCenProNum(@Param("trainDate") Date trainDate, @Param("dept")String dept);

    //插入大队统计
    Integer insertMonthTrainBigPro(MonthTrain monthTrain);

    //插入中队统计
    Integer insertMonthTrainCenPro(MonthTrain monthTrain);

    //更新大队统计
    Integer updateMonthTrainBigPro(MonthTrain monthTrain);

    //更新中队统计
    Integer updateMonthTrainCenPro(MonthTrain monthTrain);

    //查询父部门Id
    String getParentDeptId(String deptid);


    //获取大(中)队 子表个人信息
    List<MonthTrainPerson> getMonthTrainPersonInfoByBig(String parentId);
    List<MonthTrainPerson> getMonthTrainPersonInfoByCen(String parentId);

    //查询是否有个人详情统计
    MonthTrainPerson getMonthTrainPerson(@Param("trainDate") Date trainDate, @Param("trainNames")String trainNames);

    //插入个人详情统计
    Integer insertMonthTrainPerson(MonthTrainPerson monthTrainPerson);

    //更新个人详情统计
    Integer updateMonthTrainPerson(MonthTrainPerson monthTrainPerson);

    //查询大队平均分组成(中队)
    List<MonthTrain> getMonthTrainBigListByDT(@Param("trainDate") Date trainDate, @Param("bigDept")String bigDept);

    //查询中队平均分组成
    List<MonthTrain> getMonthTrainCenListDT(@Param("trainDate") Date trainDate, @Param("dept")String dept);

    //更新大队平均分
    Integer updateAvgByBig(Map<String, Object> map);

    //更新中队平均分
    Integer updateAvgByCen(Map<String, Object> map);
}
