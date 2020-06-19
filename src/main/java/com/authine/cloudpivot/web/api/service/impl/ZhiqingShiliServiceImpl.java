package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.entity.Zhiqingshili;
import com.authine.cloudpivot.web.api.mapper.ZhiqingShiliMapper;
import com.authine.cloudpivot.web.api.service.ZhiqingShiliService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * weiyao
 * 2020-06-19
 * 执勤实力表查询
 */
@Service
public class ZhiqingShiliServiceImpl implements ZhiqingShiliService {

    @Resource
    ZhiqingShiliMapper zhiqingShiliMapper;


    @Override
    public Zhiqingshili getZhiqingshiliInfo(String deptId) {
        return zhiqingShiliMapper.getZhiqingshiliInfo(deptId);
    }
}
