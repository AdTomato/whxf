package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.entity.BrigadeAnnouncement;
import com.authine.cloudpivot.web.api.entity.StationAnnouncement;
import com.authine.cloudpivot.web.api.mapper.AnnouncementMapper;
import com.authine.cloudpivot.web.api.service.AnnouncementService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 公告service接口
 *
 * @author wangyong
 * @time 2020/5/21 16:32
 */
@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    @Resource
    AnnouncementMapper announcementMapper;

    /**
     * 根据消防站id获取消防站没有关闭的公告
     *
     * @param stationId 消防站id
     * @return 没有关闭的公告
     * @author wangyong
     */
    @Override
    public List<StationAnnouncement> getIsNotCloseStationAnnouncementByStationId(String stationId) {
        return announcementMapper.getIsNotCloseStationAnnouncementByStationId(stationId);
    }

    /**
     * 根据大队id获取大队没有关闭的公告
     *
     * @param brigadeId 大队id
     * @return 没有关闭的公告
     * @author wangyong
     */
    @Override
    public List<BrigadeAnnouncement> getIsNotCloseBrigadeAnnouncementByBrigadeId(String brigadeId) {
        return announcementMapper.getIsNotCloseBrigadeAnnouncementByBrigadeId(brigadeId);
    }
}
