package com.authine.cloudpivot.web.api.service;

import com.authine.cloudpivot.web.api.entity.ServiceHotline;
import com.authine.cloudpivot.web.api.entity.UploadDanger;

import java.util.List;

/**
 * @Author Ke LongHai
 * @Date 2020/8/5 12:49
 * @Version 1.0
 */
public interface UploadDangerService {

    List<UploadDanger> getUploadDanger();


    void insertUploadDanger(UploadDanger uploadDanger);



}
