package com.authine.cloudpivot.web.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 心理咨询分数和结果
 *
 * @author weiyao
 * @time 2020/8/4
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptionAndScore{

    /**
     * 大队名称
     */
    private String option;

    private Integer score;

}
