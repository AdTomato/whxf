package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.engine.api.facade.OrganizationFacade;
import com.authine.cloudpivot.engine.api.model.organization.DepartmentModel;
import com.authine.cloudpivot.engine.api.model.organization.UserModel;
import com.authine.cloudpivot.web.api.dubbo.DubboConfigService;
import com.authine.cloudpivot.web.api.entity.EduTrainPaln;
import com.authine.cloudpivot.web.api.entity.StationEduTrainPaln;
import com.authine.cloudpivot.web.api.mapper.EduTrainPalnMapper;
import com.authine.cloudpivot.web.api.mapper.OrgMapper;
import com.authine.cloudpivot.web.api.service.EduTrainPalnService;
import com.authine.cloudpivot.web.api.utils.Constant;
import com.authine.cloudpivot.web.api.utils.DataSetUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.List;


@Service
public class EduTrainPalnServiceImpl implements EduTrainPalnService {

    @Resource
    private EduTrainPalnMapper eduTrainPalnMapper;

    @Resource
    OrgMapper orgMapper;

    @Autowired
    private DubboConfigService dubboConfigService;

    /**
     * 插入消防站的今日教育训练计划
     *
     * @param eduTrainPaln  今日教育训练计划
     * @author Ke Longhai
     */

    @Override
    public void insertStationEduTrainPaln(StationEduTrainPaln eduTrainPaln) {
        eduTrainPalnMapper.insertStationEduTrainPaln(eduTrainPaln);
    }

    /**
     * 根据消防站id获取今日教育训练计划
     *
     * @param stationId 消防站id
     * @return 今日教育训练计划
     * @author Ke Longhai
     */
    @Override
    public StationEduTrainPaln getStationEduTrainPalnByStationId(String stationId, String userId) {
        StationEduTrainPaln eduTrainPaln = eduTrainPalnMapper.getStationEduTrainPalnByStationId(stationId);
        if (false) {
            // 今日数据不存在
            OrganizationFacade organizationFacade = dubboConfigService.getOrganizationFacade();
            UserModel user = organizationFacade.getUser(userId);
            DepartmentModel department = organizationFacade.getDepartment(user.getDepartmentId());
            eduTrainPaln = new StationEduTrainPaln();
            eduTrainPaln.setStationId(stationId);
            Calendar calendar = Calendar.getInstance();
//            eduTrainPaln.setDate(DateUtils.getYearMonthDateTime(date));
            eduTrainPaln.setMorningExercises("");
            eduTrainPaln.setMorning("");
            eduTrainPaln.setAfternoon("");
            eduTrainPaln.setNight("");
            DataSetUtils.setBaseData(eduTrainPaln, user, department, calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH) + "-" + calendar.get(Calendar.DATE), Constant.COMPLETED_STATUS);
            insertStationEduTrainPaln(eduTrainPaln);
        }
        return eduTrainPaln;
    }

    /**
     * 更新消防站的今日教育训练计划
     *
     * @param eduTrainPaln 今日教育训练计划
     * @author Ke Longhai
     */
    @Override
    public void updateStationEduTrainPalnByStationId(StationEduTrainPaln eduTrainPaln) {
        eduTrainPalnMapper.updateStationEduTrainPalnStationId(eduTrainPaln);

    }

    @Override
    public List<EduTrainPaln> getEduTrainPalnWeek(String stationId) {
        return eduTrainPalnMapper.getEduTrainPalnWeek(stationId);
    }

}
