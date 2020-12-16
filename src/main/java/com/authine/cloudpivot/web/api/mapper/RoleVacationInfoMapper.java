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

    //批量插入请假人员信息-干部
    Integer insertVacationDetailList(List<RoleVacationInfo> infolist);
    //批量插入请假人员信息-消防员
    Integer insertXfyVacationDetailList(List<RoleVacationInfo> infolist);
    //批量插入请假人员信息-专职消防员
    Integer insertZzxfyVacationDetailList(List<RoleVacationInfo> infolist);

    //插入请假信息主表-干部
    Integer insertRoleVacation(RoleVacationInfo info);
    //插入请假信息主表-消防员
    Integer insertXfyRoleVacation(RoleVacationInfo info);
    //插入请假信息主表-专职消防员
    Integer insertZzxfyRoleVacation(RoleVacationInfo info);

    //查询是否有今天数据
    Integer getTodayCount();
    //查询是否有今天数据-消防员
    Integer getXfyTodayCount();
    //查询是否有今天数据-专职消防员
    Integer getZzxfyTodayCount();

    //根据钉钉部门id获取部门名称
    String getDeptNameByDDdeptId(String sourceId);

    //weiyao-2020-1216 查询增加的干部请假推送消息
    String getPushInfo();
}
