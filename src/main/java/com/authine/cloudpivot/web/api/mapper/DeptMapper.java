package com.authine.cloudpivot.web.api.mapper;

import java.util.List;

/**
 * weiyao
 * 2020-04-27
 * 查询部门人员
 */
public interface DeptMapper {
    //查询部门下面人员
    List<String> getUserBydeptId(String deptId);
}
