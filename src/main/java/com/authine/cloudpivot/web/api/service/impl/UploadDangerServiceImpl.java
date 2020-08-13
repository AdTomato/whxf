package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.entity.ServiceHotline;
import com.authine.cloudpivot.web.api.entity.UploadDanger;
import com.authine.cloudpivot.web.api.mapper.ServiceHotlineMapper;
import com.authine.cloudpivot.web.api.mapper.UploadDangerMapper;
import com.authine.cloudpivot.web.api.service.ServiceHotlineService;
import com.authine.cloudpivot.web.api.service.UploadDangerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Ke LongHai
 * @Date 2020/8/5 12:49
 * @Version 1.0
 */

@Service
public class UploadDangerServiceImpl implements UploadDangerService {

    @Resource
    UploadDangerMapper uploadDangerMapper;

    @Override
    public List<UploadDanger> getUploadDanger() {
        return uploadDangerMapper.getUploadDanger();
    }

    @Override
    public void insertUploadDanger(UploadDanger uploadDanger) {
       uploadDangerMapper.insertUploadDanger(uploadDanger);
    }
}
