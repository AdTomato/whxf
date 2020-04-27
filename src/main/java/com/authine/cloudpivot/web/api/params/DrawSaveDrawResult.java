package com.authine.cloudpivot.web.api.params;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @author: wangyong
 * @time: 2020/4/27 10:18
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrawSaveDrawResult {

    private String projectName;
    private String brigadeName;
    private Map<String, List<String>> drawResult;

}
