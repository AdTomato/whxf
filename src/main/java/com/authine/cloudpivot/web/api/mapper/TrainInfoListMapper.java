package com.authine.cloudpivot.web.api.mapper;

import com.authine.cloudpivot.web.api.dto.TrainInfoList;
import com.authine.cloudpivot.web.api.entity.CheckDetail;
import com.authine.cloudpivot.web.api.entity.TrainInfo;
import com.authine.cloudpivot.web.api.entity.UploadDanger;
import com.authine.cloudpivot.web.api.entity.WorkInfo;

import java.util.List;

/**
 * @Author Ke LongHai
 * @Date 2020/8/5 12:49
 * @Version 1.0
 */
public interface TrainInfoListMapper {


    /**
     * 查询训练情况
     * @param id
     * @return
     */
   List<TrainInfoList> getTrainInfoList(String id);

   List<CheckDetail> getCheckDetail(String id);

    /**
     * 查询工作情况
     * @param id
     * @return
     */
   List<WorkInfo> getWorkInfo(String id);


}
