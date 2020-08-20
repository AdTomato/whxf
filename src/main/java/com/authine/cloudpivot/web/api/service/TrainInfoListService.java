package com.authine.cloudpivot.web.api.service;

import com.authine.cloudpivot.web.api.dto.TrainInfoList;
import com.authine.cloudpivot.web.api.entity.TrainInfo;
import com.authine.cloudpivot.web.api.entity.UploadDanger;
import com.authine.cloudpivot.web.api.entity.WorkInfo;

import java.util.List;

/**
 * @Author Ke LongHai
 * @Date 2020/8/5 12:49
 * @Version 1.0
 */
public interface TrainInfoListService {


    /*
      查询工作情况
       */
    List<WorkInfo> getWorkInfo(String id);

    /**
     * 查询训练情况
     * @param id
     * @return
     */
    List<TrainInfoList> getTrainInfoList(String id);


}
