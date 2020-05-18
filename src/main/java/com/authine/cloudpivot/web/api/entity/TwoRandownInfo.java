package com.authine.cloudpivot.web.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 量化考勤周报
 *
 * @author weiyao
 * @time 2020/5/14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TwoRandownInfo {


    //主键
    private String id;
    //时间
    private Date date;
    //本月应该检查
    private Integer shouldCheck;
    //本月未检查
    private Integer remainingCheck;
    //本月已经检查
    private Integer checked;



}
