package com.authine.cloudpivot.web.api.utils;

import com.authine.cloudpivot.engine.api.model.organization.DepartmentModel;
import com.authine.cloudpivot.engine.api.model.organization.UserModel;
import com.authine.cloudpivot.web.api.entity.BaseEntity;

import java.util.Date;
import java.util.UUID;

/**
 * @Author:wangyong
 * @Date:2020/3/27 0:25
 * @Description: 设置数据工具类
 */
public class DataSetUtils {

    public static String setBaseData(BaseEntity baseBean, UserModel userModel, DepartmentModel departmentModel, String name, String sequenceStatus) {
        Date nowDate = new Date();
        baseBean.setId(UUID.randomUUID().toString().replace("-", ""));
        baseBean.setName(name);
        baseBean.setSequenceStatus(sequenceStatus);
        baseBean.setWorkflowInstanceId(null);
        baseBean.setCreater(userModel.getId());
        baseBean.setCreatedTime(nowDate);
        baseBean.setCreatedDeptId(departmentModel.getId());
        baseBean.setOwner(userModel.getId());
        baseBean.setOwnerDeptId(departmentModel.getId());
        baseBean.setOwnerDeptQueryCode(departmentModel.getQueryCode());
        baseBean.setModifier(userModel.getId());
        baseBean.setModifiedTime(nowDate);
        return baseBean.getId();
    }

}
