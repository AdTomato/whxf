package com.authine.cloudpivot.web.api.mapper;


import com.authine.cloudpivot.web.api.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: weiyao
 * @Date: 2020-04-26
 * @Description: 武汉心里测评
 */
public interface XinliCePingMapper {

    //插入评测结果
    public void insertCepingResult(PingceResult pingceResult);

    //查询测评结果
    PingceResult getCepingResultByName(String creater);

    //查询测评数据(抑郁测评)（焦虑测评）
    XinliSas getXinliSdsById(String id);
    //查询测评数据(抑郁测评)（焦虑测评）
    XinliSas getXinliSasById(String id);

    //更新测评结果
    public void updateCepingResult(PingceResult pingceResult);
}
