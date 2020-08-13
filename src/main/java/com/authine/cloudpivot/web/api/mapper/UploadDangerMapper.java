package com.authine.cloudpivot.web.api.mapper;

import com.authine.cloudpivot.web.api.entity.ServiceHotline;
import com.authine.cloudpivot.web.api.entity.UploadDanger;

import java.util.List;

/**
 * @Author Ke LongHai
 * @Date 2020/8/5 12:49
 * @Version 1.0
 */
public interface UploadDangerMapper {

    List<UploadDanger> getUploadDanger();


    void insertUploadDanger(UploadDanger uploadDanger);

}
