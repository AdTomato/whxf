package com.authine.cloudpivot.web.api.mapper;

import com.authine.cloudpivot.web.api.entity.CePingUserInfo;
import com.authine.cloudpivot.web.api.entity.Test;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author: weiyao
 * @time: 2020/9/8
 * @Description:  测评结果统计
 */
public interface CePingResultMapper {

    //查询所有部门测评数据
    List<CePingUserInfo> getInfo();

    //查询大队，站，班数据
    List<CePingUserInfo> getDeptInfo(@Param("name") String name);

    //查询班数据
    List<CePingUserInfo> getDeptBanInfo(@Param("name") String name);

    //查询父部门
    CePingUserInfo getParentDept(@Param("parentId") String parentId);

    //查询总数
    Integer getNum();

    //查询总人次
    Integer getNumAll();

    //查询部门总人数
    Integer getDeptUserCountByDeptid(@Param("deptid") String deptid);
    //查询下级部门总人数
    Integer getDeptUserCountByParentId(@Param("parentid") String parentid);

}
