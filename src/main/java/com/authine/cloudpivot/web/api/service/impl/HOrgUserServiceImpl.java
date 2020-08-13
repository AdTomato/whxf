package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.entity.HOrgUser;
import com.authine.cloudpivot.web.api.entity.UploadDanger;
import com.authine.cloudpivot.web.api.mapper.HOrgUserMapper;
import com.authine.cloudpivot.web.api.mapper.UploadDangerMapper;
import com.authine.cloudpivot.web.api.service.HOrgUserService;
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
public class HOrgUserServiceImpl implements HOrgUserService {

    @Resource
    HOrgUserMapper hOrgUserMapper;

    @Override
    public HOrgUser getHOrgUser(String userId) {
        return hOrgUserMapper.getHOrgUser(userId);
    }
}
