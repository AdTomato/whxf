package com.authine.cloudpivot.web.api.mapper;

import com.authine.cloudpivot.web.api.entity.RoleVacationInfo;
import com.authine.cloudpivot.web.api.entity.Unit;

import java.util.List;

/**
 * weiyao
 * 2020-06-03
 * 角色请假人员详情
 */
public interface RoleVacationInfoMapper {

    //weiyao 获取钉钉角色Id
    String getddRoleId(String name);

    //批量插入请假人员信息
    Integer insertVacationDetailList(List<RoleVacationInfo> infolist);

    //插入请假信息主表
    Integer insertRoleVacation(RoleVacationInfo info);

    //查询是否有今天数据
    Integer getTodayCount();
}
