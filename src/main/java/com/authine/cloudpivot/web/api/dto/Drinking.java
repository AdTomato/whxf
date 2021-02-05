package com.authine.cloudpivot.web.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 饮酒报备
 *
 * @Author Ke LongHai
 * @Date 2021/2/5 11:55
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Drinking {

    //时间
    private String date;

    //地点
    private String site;

    //事由
    private String reason;
}
