package com.authine.cloudpivot.web.api.mapper;

import java.util.List;
import java.util.Map;

/**
 * 组织mapper
 *
 * @author wangyong
 * @time 2020/5/11 11:35
 */
public interface OrgMapper {

    /**
     * 根据人员id获取支队id，名称列表
     *
     * @param userId 人员id
     * @return 支队id, 名称列表
     * @author wnagyong
     */
    String getDetachmentIdByUserId(String userId);

    /**
     * 根据人员id获取大队id，名称列表
     *
     * @param userId 人员id
     * @return 大队id，名称列表
     * @author wangyong
     */
    List<Map<String, String>> getBrigadeIdByUserId(String userId);

    /**
     * 根据人员id获取消防站id，名称列表
     *
     * @param userId 人员id
     * @return 消防站id，名称列表
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
     * 根据消防站id获取该消防站所属大队的id
     *
     * @param stationId 消防站id
     * @return 大队id
     * @author wangyong
     */
    String getBrigadeIdByStationId(String stationId);

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

}
