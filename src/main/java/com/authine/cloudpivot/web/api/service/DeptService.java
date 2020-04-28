package com.authine.cloudpivot.web.api.service;
import com.authine.cloudpivot.web.api.entity.Unit;

import java.util.List;

/**
 * @author: weiyao
 * @time: 2020/4/27
 * @Description:
 */
public interface DeptService {

    List<String> getUserBydeptId(String deptId);

    List<Unit> getUsersById(List<String> userIdList);
}
