package com.authine.cloudpivot.web.api.jiaqinxinxi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 干部统计信息
 * @Author Ke LongHai
 * @Date 2021/2/5 14:01
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CadreLeave {

    //角色姓名
    private String roleName;
    //统计日期
    private Date vacationDate;
    //总人数
    private Integer allroleNum;
    //请假人数
    private Integer vacationNum;
    //在岗人数
    private Integer inworkNum;
}
