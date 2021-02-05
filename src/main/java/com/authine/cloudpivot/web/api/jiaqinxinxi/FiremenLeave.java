package com.authine.cloudpivot.web.api.jiaqinxinxi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**消防员
 * @Author Ke LongHai
 * @Date 2021/2/5 14:02
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FiremenLeave {

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
