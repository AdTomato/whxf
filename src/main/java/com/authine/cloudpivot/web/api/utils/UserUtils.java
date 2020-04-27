package com.authine.cloudpivot.web.api.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @Author: wangyong
 * @Date: 2020-01-12 10:42
 * @Description: 用户工具类
 */
public class UserUtils {

    public static String getUserId(String userId) {
        if (StringUtils.isEmpty(userId)) {
            return Constant.ADMIN_ID;
        }
        return userId;
    }

}
