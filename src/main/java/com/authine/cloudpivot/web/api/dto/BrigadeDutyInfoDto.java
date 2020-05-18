package com.authine.cloudpivot.web.api.dto;

import com.authine.cloudpivot.web.api.entity.BrigadeDutyInfo;
import com.authine.cloudpivot.web.api.entity.CommandAssistant;
import com.authine.cloudpivot.web.api.entity.Commander;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author wangyong
 * @time 2020/5/18 13:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrigadeDutyInfoDto extends BrigadeDutyInfo {

    private List<Commander> commanders;

    private List<CommandAssistant> commandAssistants;

}
