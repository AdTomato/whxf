package com.authine.cloudpivot.web.api.service;

import java.util.List;
import java.util.Map;

/**
 * 组织service接口
 *
 * @author wangyong
 * @time 2020/5/11 11:43
 */
public interface OrgService {

    /**
     * 根据人员id获取支队id
     *
     * @param userId 人员id
     * @return 支队id
     * @author wnagyong
     */
    String getDetachmentIdByUserId(String userId);

    /**
     * 根据人员id获取大队id
     *
     * @param userId 人员id
     * @return 大队id
     * @author wangyong
     */
    List<Map<String, String>> getBrigadeIdByUserId(String userId);

    /**
     * 根据人员id获取消防站id
     *
     * @param userId 人员id
     * @return 消防站id
     * @author wangyong
     */
    List<Map<String, String>> getStationIdByUserId(String userId);

    /**
     * 获取所有的大队名称列表
     *
     * @return 大队名称，大队id
     * @author wangyong
     */
    List<Map<String, String>> getAllBrigadeNameList();

    /**
     * 根据大队名称获取大队id
     *
     * @param brigadeName 大队名称
     * @return 大队id
     * @author wangyong
     */
    String getBrigadeIdByName(String brigadeName);

    /**
     * 获取所有的消防站名称列表
     *
     * @return 消防站名称，消防站id
     * @author wangyong
     */
    List<Map<String, String>> getAllStationList();

    /**
     * 根据消防站名称获取消防站id
     *
     * @param stationName 消防站名称
     * @return 消防站id
     * @author wangyong
     */
    String getStationIdByName(String stationName);

    /**
     * 根据大队id获取大队下面的消防站名称列表
     *
     * @param brigadeId 大队id
     * @return 消防站名称列表
     * @author wangyong
     */
    List<Map<String, String>> getAllStationListByBrigadeId(String brigadeId);

    /**
     * 获取消防站登录人密码
     *
     * @param stationId 消防站id
     * @param userId    登录人id
     * @return 登录人密码
     * @author wangyong
     */
    String getStationPassword(String stationId, String userId);

    /**
     * 获取大队登录人密码
     *
     * @param brigadeId 大队id
     * @param userId    登录人id
     * @return 登录人密码
     * @author wangyong
     */
    String getBrigadePassword(String brigadeId, String userId);

    /**
     * 获取支队登录人的密码
     *
     * @param detachmentId 支队id
     * @param userId       登录人id
     * @return 登录人密码
     * @author wangyong
     */
    String getDetachmentPassword(String detachmentId, String userId);

    /**
     * 根据id获取大屏标题
     *
     * @param id id值
     * @return 大屏标题
     * @author wangyong
     */
    String getTitle(String id);

}
