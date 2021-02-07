package com.authine.cloudpivot.web.api.jiaqinxinxi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**干部休假审批
 * @Author Ke LongHai
 * @Date 2021/2/5 15:58
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveInformation {
    //请假人姓名
    private String name;
    //事由
    private String reason;

    //申请时间
    private Date createTime;
    //开始时间
    private String startTime;
    //结束时间
    private String endTime;
}
