package com.authine.cloudpivot.web.api.mapper;

import com.authine.cloudpivot.web.api.entity.PersonalName;

import java.util.List;

/**
 * @author: wangyong
 * @time: 2020/4/26 14:31
 * @Description:
 */
public interface PersonalNameMapper {

    /**
     * 获取所有大队下面的参与抽签人的人员列表
     *
     * @param brigadeId 大队id
     * @return 大队人员列表
     * @author wangyong
     */
    List<PersonalName> getAllBrigadePersonal(String brigadeId);

}
