package com.authine.cloudpivot.web.api.service.impl;
import com.authine.cloudpivot.web.api.entity.UserInfoByCheck;
import com.authine.cloudpivot.web.api.mapper.AllCheckMapper;
import com.authine.cloudpivot.web.api.service.AllCheckService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author weiyao
 * @Date 2020/8/19
 * @Version 1.0
 */
@Service
public class AllCheckServiceImpl implements AllCheckService {

    @Resource
    AllCheckMapper allCheckMapper;

    @Override
    public List<Map<String, String>> getDeptListByName(String name) {
        if ("支队".equals(name)) {
            return allCheckMapper.getDeptListByZD();
        } else {
            return allCheckMapper.getDeptListByName(name);
        }

    }

    @Override
    public List<UserInfoByCheck> getUserListByDept(String deptId) {
        return allCheckMapper.getUserListByDeptId(deptId);
    }

}


