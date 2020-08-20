package com.authine.cloudpivot.web.api.dto;

import com.authine.cloudpivot.web.api.entity.BaseEntity;
import com.authine.cloudpivot.web.api.entity.CheckDetail;
import com.authine.cloudpivot.web.api.entity.TrainInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @Author Ke LongHai
 * @Date 2020/8/18 17:31
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainInfoList extends TrainInfo {


    private List<CheckDetail> checkDetails;


}
