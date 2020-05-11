package com.authine.cloudpivot.web.api.dto;

import com.authine.cloudpivot.web.api.entity.CarsInfo;
import com.authine.cloudpivot.web.api.entity.VehicleInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 车辆信息扩展
 *
 * @author wangyong
 * @time 2020/5/11 9:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleInfoDto extends CarsInfo {

    private List<VehicleInfo> vehicleInfos;

}
