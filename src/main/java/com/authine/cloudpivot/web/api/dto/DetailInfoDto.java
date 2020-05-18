package com.authine.cloudpivot.web.api.dto;

import com.authine.cloudpivot.web.api.entity.DetailInfo;
import com.authine.cloudpivot.web.api.entity.TrainResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 成绩信息扩展
 *
 * @author Ke Longhai
 * @time 2020/5/14 9:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailInfoDto extends TrainResult {

    private List<DetailInfo> DetailInfos;

}
