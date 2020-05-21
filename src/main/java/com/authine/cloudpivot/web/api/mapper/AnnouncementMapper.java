package com.authine.cloudpivot.web.api.mapper;

import com.authine.cloudpivot.web.api.entity.BrigadeAnnouncement;
import com.authine.cloudpivot.web.api.entity.StationAnnouncement;

import java.util.List;

/**
 * 公告mapper
 *
 * @author wangyong
 * @time 2020/5/21 16:11
 */
public interface AnnouncementMapper {

    /**
     * 根据消防站id获取消防站没有关闭的公告信息
     *
     * @param stationId 消防站id
     * @return 消防站没有关闭的公告
     * @author wangyong
     */
    List<StationAnnouncement> getIsNotCloseStationAnnouncementByStationId(String stationId);

    /**
     * 根据大队id获取大队没有关闭的公告信息
     *
     * @param brigadeId 大队id
     * @return 大队没有关闭的公告信息
     * @author wangyong
     */
    List<BrigadeAnnouncement> getIsNotCloseBrigadeAnnouncementByBrigadeId(String brigadeId);

}
