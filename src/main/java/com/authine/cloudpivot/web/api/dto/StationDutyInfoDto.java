package com.authine.cloudpivot.web.api.dto;

import com.authine.cloudpivot.web.api.entity.BrigadeHeadquarter;
import com.authine.cloudpivot.web.api.entity.StationDutyCadre;
import com.authine.cloudpivot.web.api.entity.StationDutyInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 消防站值班信息dto
 *
 * @author wangyong
 * @time 2020/5/13 14:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StationDutyInfoDto extends StationDutyInfo {

    /**
     * 大队全勤指挥部
     */
    private List<BrigadeHeadquarter> brigadeHeadquarters;

    /**
     * 队站值班干部
     */
    private List<StationDutyCadre> stationDutyCadres;

}
