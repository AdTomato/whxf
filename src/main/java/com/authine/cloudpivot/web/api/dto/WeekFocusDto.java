package com.authine.cloudpivot.web.api.dto;

import com.authine.cloudpivot.web.api.entity.WeekFocus;
import com.authine.cloudpivot.web.api.entity.WeekWork;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 本周重点工作信息 拓展
 * @Author Ke LongHai
 * @Date 2020/5/18 9:48
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeekFocusDto extends WeekWork {

    private List<WeekFocus> weekFocusList;

}
