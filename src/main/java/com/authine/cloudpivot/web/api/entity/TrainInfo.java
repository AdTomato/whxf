package com.authine.cloudpivot.web.api.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author Ke LongHai
 * @Date 2020/8/18 17:31
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TrainInfo extends BaseEntity  implements Serializable{

    //日期
    private Date checkDate;

    //训练详情
    private String checkDetail;

    private List<CheckDetail> checkDetails;


}
