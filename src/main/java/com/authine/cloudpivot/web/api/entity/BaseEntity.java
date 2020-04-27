package com.authine.cloudpivot.web.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @author: wangyong
 * @time: 2020/4/26 14:26
 * @Description: 所有实体类基类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity {

    /**
     * id
     */
    public String id;

    /**
     * 数据标题
     */
    public String name;

    /**
     * 创建人
     */
    public String creater;

    /**
     * 创建人部门id
     */
    public String createdDeptId;

    /**
     * 拥有者
     */
    public String owner;

    /**
     * 拥有者部门id
     */
    public String ownerDeptId;

    /**
     * 创建时间
     */
    public Date createdTime;

    /**
     * 修改人
     */
    public String modifier;

    /**
     * 修改时间
     */
    public Date modifiedTime;

    /**
     * 流程实例id
     */
    public String workflowInstanceId;

    /**
     * 单据号
     */
    public String sequenceNo;

    /**
     * 单据状态
     */
    public String sequenceStatus;

    /**
     * 部门查询编码
     */
    public String ownerDeptQueryCode;

}
