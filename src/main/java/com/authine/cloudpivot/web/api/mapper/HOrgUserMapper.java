package com.authine.cloudpivot.web.api.mapper;

import com.authine.cloudpivot.web.api.entity.HOrgUser;
import com.authine.cloudpivot.web.api.entity.UploadDanger;

import java.util.List;

/**查询云枢人员信息
 * @Author Ke LongHai
 * @Date 2020/8/5 12:49
 * @Version 1.0
 */
public interface HOrgUserMapper {

    HOrgUser getHOrgUser(String userId);


}
