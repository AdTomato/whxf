package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.entity.PsychologyManData;
import com.authine.cloudpivot.web.api.entity.ServiceHotline;
import com.authine.cloudpivot.web.api.mapper.ServiceHotlineMapper;
import com.authine.cloudpivot.web.api.service.ServiceHotlineService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Ke LongHai
 * @Date 2020/8/5 12:49
 * @Version 1.0
 */

@Service
public class ServiceHotlineServiceImpl implements ServiceHotlineService {

    @Resource
    ServiceHotlineMapper serviceHotlineMapper;

    @Override
    public List<ServiceHotline> getServiceHotline() {
        return serviceHotlineMapper.getServiceHotline();
    }
}
