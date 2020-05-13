package com.authine.cloudpivot.web.api.service;

import com.authine.cloudpivot.web.api.entity.PersonlInfo;

/**
 * @author: weiyao
 * @time: 2020/5/11
 * @Description: 武汉消防 大屏功能 人员动态模块信息
 */

public interface PersonlInfoService {

    public PersonlInfo getPersonlInfo(String deptId);

}
