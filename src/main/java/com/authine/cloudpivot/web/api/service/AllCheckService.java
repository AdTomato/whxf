package com.authine.cloudpivot.web.api.service;

import com.authine.cloudpivot.web.api.entity.ScaleTest;
import com.authine.cloudpivot.web.api.entity.UserInfoByCheck;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author weiyao
 * @Date 2020/8/19
 * @Version 1.0
 */
public interface AllCheckService {

    //获取大队站队所有部门
    List<Map<String,String>> getDeptListByName(String name);

    //根据部门查询用户信息
    List<UserInfoByCheck> getUserListByDept(String deptId);


}
