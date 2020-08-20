package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.dto.TrainInfoList;
import com.authine.cloudpivot.web.api.entity.TrainInfo;
import com.authine.cloudpivot.web.api.entity.UploadDanger;
import com.authine.cloudpivot.web.api.entity.WorkInfo;
import com.authine.cloudpivot.web.api.mapper.TrainInfoListMapper;
import com.authine.cloudpivot.web.api.mapper.UploadDangerMapper;
import com.authine.cloudpivot.web.api.service.TrainInfoListService;
import com.authine.cloudpivot.web.api.service.UploadDangerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Ke LongHai
 * @Date 2020/8/5 12:49
 * @Version 1.0
 */

@Service
public class TrainInfoListServiceImpl implements TrainInfoListService {

    @Resource
    TrainInfoListMapper trainInfoListMapper;

    @Override
    public List<WorkInfo> getWorkInfo(String id) {
        return trainInfoListMapper.getWorkInfo(id);
    }

    @Override
    public List<TrainInfoList> getTrainInfoList(String id) {
        return trainInfoListMapper.getTrainInfoList(id);
    }

}
