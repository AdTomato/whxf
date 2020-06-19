package com.authine.cloudpivot.web.api.mapper;

import com.authine.cloudpivot.web.api.entity.Unit;
import com.authine.cloudpivot.web.api.entity.Zhiqingshili;

import java.util.List;

/**
 * weiyao
 * 2020-06-19
 * 执勤实力表查询
 */
public interface ZhiqingShiliMapper {

    //weiyao 获取执勤实力表信息
    Zhiqingshili getZhiqingshiliInfo(String deptId);


}
