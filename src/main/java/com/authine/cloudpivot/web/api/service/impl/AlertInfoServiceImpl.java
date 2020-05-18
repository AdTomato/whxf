package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.engine.api.facade.OrganizationFacade;
import com.authine.cloudpivot.engine.api.model.organization.DepartmentModel;
import com.authine.cloudpivot.engine.api.model.organization.UserModel;
import com.authine.cloudpivot.web.api.dubbo.DubboConfigService;
import com.authine.cloudpivot.web.api.entity.AlertInfo;
import com.authine.cloudpivot.web.api.entity.BrigadeAlertInfo;
import com.authine.cloudpivot.web.api.entity.StationAlertInfo;
import com.authine.cloudpivot.web.api.mapper.AlertInfoMapper;
import com.authine.cloudpivot.web.api.mapper.OrgMapper;
import com.authine.cloudpivot.web.api.service.AlertInfoService;
import com.authine.cloudpivot.web.api.utils.Constant;
import com.authine.cloudpivot.web.api.utils.DataSetUtils;
import com.authine.cloudpivot.web.api.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.awt.geom.AreaOp;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 接警信息service
 *
 * @author wangyong
 * @time 2020/5/11 14:45
 */
@Service
public class AlertInfoServiceImpl implements AlertInfoService {

    @Resource
    AlertInfoMapper alertInfoMapper;

    @Resource
    OrgMapper orgMapper;

    @Autowired
    private DubboConfigService dubboConfigService;

    /**
     * 插入消防站的今日警情信息
     *
     * @param alertInfo 今日警情信息
     * @author wangyong
     */
    @Override
    public void insertStationAlertInfo(StationAlertInfo alertInfo) {
        alertInfoMapper.insertStationAlertInfo(alertInfo);
    }

    /**
     * 根据消防站id获取今日警情信息
     *
     * @param stationId 消防站id
     * @param date      时间
     * @return 今日警情信息
     * @author wangyong
     */
    @Override
    public StationAlertInfo getStationAlertInfoByStationId(String stationId, Date date, String userId) {

        StationAlertInfo alertInfo = alertInfoMapper.getStationAlertInfoByStationId(stationId, date);
        if (alertInfo == null) {
            // 今日数据不存在
            OrganizationFacade organizationFacade = dubboConfigService.getOrganizationFacade();
            UserModel user = organizationFacade.getUser(userId);
            DepartmentModel department = organizationFacade.getDepartment(user.getDepartmentId());
            alertInfo = new StationAlertInfo();
            alertInfo.setStationId(stationId);
            Calendar calendar = Calendar.getInstance();
            alertInfo.setDate(DateUtils.getYearMonthDateTime(date));
            alertInfo.setCallPoliceTotal(0);
            alertInfo.setFireAlarmNum(0);
            alertInfo.setEmergencyRescueNum(0);
            alertInfo.setSocialAssistanceNum(0);
            alertInfo.setFalseAlarmNum(0);
            alertInfo.setOtherAlertNum(0);
            DataSetUtils.setBaseData(alertInfo, user, department, calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH) + "-" + calendar.get(Calendar.DATE), Constant.COMPLETED_STATUS);
            insertStationAlertInfo(alertInfo);
        }
        return alertInfo;
    }

    /**
     * 更新消防站的今日警情信息
     *
     * @param alertInfo 今日警情信息
     * @author wangyong
     */
    @Override
    public void updateStationAlertInfoByStationId(StationAlertInfo alertInfo) {
        alertInfoMapper.updateStationAlertInfoByStationId(alertInfo);
    }

    /**
     * 根据大队id获取某一日的大队警情信息
     *
     * @param brigadeId 大队id
     * @param date      时间
     * @return 大队某日警情信息
     * @author wangyong
     */
    @Override
    public List<BrigadeAlertInfo> getDateBrigadeAlertInfoByBrigadeId(String brigadeId, Date date) {
        return alertInfoMapper.getDateBrigadeAlertInfoByBrigadeId(brigadeId, date);
    }

    /**
     * 根据大队id获取某一月的大队警情信息
     *
     * @param brigadeId 大队id
     * @param date      时间
     * @return 大队某月警情信息
     * @author wangyong
     */
    @Override
    public List<BrigadeAlertInfo> getMonthBrigadeAlertInfoByBrigadeId(String brigadeId, Date date) {
        return alertInfoMapper.getMonthBrigadeAlertInfoByBrigadeId(brigadeId, date);
    }

}
