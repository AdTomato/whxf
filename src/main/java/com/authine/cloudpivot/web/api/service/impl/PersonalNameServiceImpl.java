package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.entity.PersonalName;
import com.authine.cloudpivot.web.api.mapper.PersonalNameMapper;
import com.authine.cloudpivot.web.api.service.PersonalNameService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: wangyong
 * @time: 2020/4/26 14:30
 * @Description:
 */
@Service
public class PersonalNameServiceImpl implements PersonalNameService {

    @Resource
    PersonalNameMapper personalNameMapper;

    /**
     * 获取所有大队下面的参与抽签人的人员列表
     *
     * @param brigadeId 大队id
     * @return 大队人员列表
     * @author wangyong
     */
    @Override
    public List<PersonalName> getAllBrigadePersonal(String brigadeId) {
        return personalNameMapper.getAllBrigadePersonal(brigadeId);
    }
}
