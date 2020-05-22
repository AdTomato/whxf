package com.authine.cloudpivot.web.api.utils;

import com.authine.cloudpivot.web.api.service.OrgService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import sun.security.acl.WorldGroupImpl;

/**
 * 用户工具类
 *
 * @author wangyong
 * @date: 2020-01-12 10:42
 */
public class UserUtils {

    @Autowired
    static OrgService orgService;

    public static String getUserId(String userId) {
        if (StringUtils.isEmpty(userId)) {
            return Constant.ADMIN_ID;
        }
        return userId;
    }

    public static String getConsumerPassword(String typeId, String userId, String consumerType) {
        String result = "";
        switch (consumerType) {
            case "支队":
                result = orgService.getDetachmentPassword(typeId, userId);
                break;
            case "大队":
                result = orgService.getBrigadePassword(typeId, userId);
                break;
            case "消防站":
                result = orgService.getStationPassword(typeId, userId);
                break;
        }
        return result;
    }

}
