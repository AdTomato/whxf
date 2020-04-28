package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.entity.Unit;
import com.authine.cloudpivot.web.api.mapper.DeptMapper;
import com.authine.cloudpivot.web.api.service.DeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Resource
    DeptMapper deptMapper;

    @Override
    public List<String> getUserBydeptId(String deptId) {
        return deptMapper.getUserBydeptId(deptId);
    }

    @Override
    public List<Unit> getUsersById(List<String> userIdList) {
        return deptMapper.getUsersById(userIdList);
    }
}
