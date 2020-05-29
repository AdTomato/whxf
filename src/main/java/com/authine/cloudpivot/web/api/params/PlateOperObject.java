package com.authine.cloudpivot.web.api.params;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangyong
 * @time 2020/5/29 13:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlateOperObject {

    private String plate;//	String	是	车牌号
    private int oper;//		是	0：新增 1：修改 2：删除
    private String no;//		是	编号车主唯一编码
    private String Name;//		是	姓名
    private String address;//		否	住址或部门
    private String phone;//		否	联系电话
    private String valid_date;//		是	有效期格式：yyyy-MM-dd
    private int type;//		是	车牌类型0：月租车，1：固定车

}
