package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.mapper.OrgMapper;
import com.authine.cloudpivot.web.api.service.OrgService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 组织service
 *
 * @author wangyong
 * @time 2020/5/11 11:48
 */
@Service
public class OrgServiceImpl implements OrgService {

    @Resource
    OrgMapper orgMapper;

    /**
     * 获取所有的大队名称列表
     *
     * @return 大队名称，大队id
     * @author wangyong
     */
    @Override
    public List<Map<String, String>> getAllBrigadeNameList() {
        return orgMapper.getAllBrigadeNameList();
    }

    /**
     * 根据大队名称获取大队id
     *
     * @param brigadeName 大队名称
     * @return 大队id
     * @author wangyong
     */
    @Override
    public String getBrigadeIdByName(String brigadeName) {
        return orgMapper.getBrigadeIdByName(brigadeName);
    }

    /**
     * 获取所有的消防站名称列表
     *
     * @return 消防站名称，消防站id
     * @author wangyong
     */
    @Override
    public List<Map<String, String>> getAllStationList() {
        return orgMapper.getAllStationList();
    }

    /**
     * 根据消防站名称获取消防站id
     *
     * @param stationName 消防站名称
     * @return 消防站id
     * @author wangyong
     */
    @Override
    public String getStationIdByName(String stationName) {
        return orgMapper.getStationIdByName(stationName);
    }

    /**
     * 根据大队id获取大队下面的消防站名称列表
     *
     * @param brigadeId 大队id
     * @return 消防站名称列表
     * @author wangyong
     */
    @Override
    public List<Map<String, String>> getAllStationListByBrigadeId(String brigadeId) {
        return orgMapper.getAllStationListByBrigadeId(brigadeId);
    }
}
