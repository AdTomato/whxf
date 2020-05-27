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
     * 根据人员id获取支队id
     *
     * @param userId 人员id
     * @return 支队id
     * @author wnagyong
     */
    @Override
    public String getDetachmentIdByUserId(String userId) {
        return orgMapper.getDetachmentIdByUserId(userId);
    }

    /**
     * 根据人员id获取大队id
     *
     * @param userId 人员id
     * @return 大队id
     * @author wangyong
     */
    @Override
    public List<Map<String, String>> getBrigadeIdByUserId(String userId) {
        return orgMapper.getBrigadeIdByUserId(userId);
    }

    /**
     * 根据人员id获取消防站id
     *
     * @param userId 人员id
     * @return 消防站id
     * @author wangyong
     */
    @Override
    public List<Map<String, String>> getStationIdByUserId(String userId) {
        return orgMapper.getStationIdByUserId(userId);
    }

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

    /**
     * 获取消防站登录人密码
     *
     * @param stationId 消防站id
     * @param userId    登录人id
     * @return 登录人密码
     * @author wangyong
     */
    @Override
    public String getStationPassword(String stationId, String userId) {
        return orgMapper.getStationPassword(stationId, userId);
    }

    /**
     * 获取大队登录人密码
     *
     * @param brigadeId 大队id
     * @param userId    登录人id
     * @return 登录人密码
     * @author wangyong
     */
    @Override
    public String getBrigadePassword(String brigadeId, String userId) {
        return orgMapper.getBrigadePassword(brigadeId, userId);
    }

    /**
     * 获取支队登录人密码
     *
     * @param detachmentId 支队id
     * @param userId       登录人id
     * @return 登录人密码
     * @author wnagyong
     */
    @Override
    public String getDetachmentPassword(String detachmentId, String userId) {
        return orgMapper.getDetachmentPassword(detachmentId, userId);
    }

    /**
     * 获取大屏的标题
     *
     * @param id id值
     * @return 大屏标题
     * @author wangyong
     */
    @Override
    public String getTitle(String id) {
        return orgMapper.getTitle(id);
    }
}
