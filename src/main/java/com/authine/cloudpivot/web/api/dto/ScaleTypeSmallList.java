package com.authine.cloudpivot.web.api.dto;

import com.authine.cloudpivot.web.api.entity.ScaleTypeSmall;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *  分类详情集合
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScaleTypeSmallList extends ScaleTypeSmall{

    private List<ScaleTypeSmall> ScaleTypeSmallList;

}
