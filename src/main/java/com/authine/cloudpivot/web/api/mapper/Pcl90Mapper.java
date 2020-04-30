package com.authine.cloudpivot.web.api.mapper;


import com.authine.cloudpivot.web.api.entity.Pcl90;
import com.authine.cloudpivot.web.api.entity.Pcl90Result;


/**
 * @Author: weiyao
 * @Date: 2020-04-29
 * @Description: 武汉PCL90心里测评
 */
public interface Pcl90Mapper {

    //插入评测结果
    public void insertPcl90Result(Pcl90Result pcl90Result);

    //查询测评结果
    Pcl90Result getResultByNameAndDate(String creater);

    //查询测评数据
    Pcl90 getPcl90ById(String id);

    //更新测评结果
    public void updatePcl90Result(Pcl90Result pcl90Result);
}
