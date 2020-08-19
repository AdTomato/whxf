package com.authine.cloudpivot.web.api.mapper;

import com.authine.cloudpivot.web.api.entity.ScaleConsultDetail;
import com.authine.cloudpivot.web.api.entity.ScaleTestAcore;
import com.authine.cloudpivot.web.api.entity.UserInfoByCheck;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author: weiyao
 * @time: 2020/8/18
 * @Description: 全员考评
 */
public interface AllCheckMapper {


    //获取大队站队所有部门
    List<Map<String,String>> getDeptListByName(@Param("name") String name);

    //查询支队部门
    List<Map<String,String>> getDeptListByZD();

    //返回部门人员集合
    List<UserInfoByCheck> getUserListByDeptId(@Param("deptId")String deptId);

}
