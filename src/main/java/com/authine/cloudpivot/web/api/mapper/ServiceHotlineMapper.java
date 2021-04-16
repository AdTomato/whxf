package com.authine.cloudpivot.web.api.mapper;

import com.authine.cloudpivot.web.api.entity.PsychologyManData;
import com.authine.cloudpivot.web.api.entity.ServiceHotline;

import java.util.List;

/**服务热线mapper
 * @Author Ke LongHai
 * @Date 2020/8/5 12:49
 * @Version 1.0
 */
public interface ServiceHotlineMapper {

    /**
     * 得到服务热线
     *
     * @return {@link List<ServiceHotline>}
     */
    List<ServiceHotline> getServiceHotline();

}
