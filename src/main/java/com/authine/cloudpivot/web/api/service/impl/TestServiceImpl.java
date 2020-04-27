package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.entity.Test;
import com.authine.cloudpivot.web.api.mapper.TestMapper;
import com.authine.cloudpivot.web.api.service.TestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: wangyong
 * @time: 2020/4/24 13:02
 * @Description:
 */
@Service
public class TestServiceImpl implements TestService {

    @Resource
    TestMapper testMapper;

    @Override
    public List<Test> getAllTestData() {
        return testMapper.getAllTestData();
    }
}
