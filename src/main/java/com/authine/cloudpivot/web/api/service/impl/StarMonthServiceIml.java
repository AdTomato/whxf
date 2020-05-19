package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.entity.StationStarMonth;
import com.authine.cloudpivot.web.api.mapper.StarMonthMapper;
import com.authine.cloudpivot.web.api.service.StarMonthService;
import com.authine.cloudpivot.web.api.utils.DingDingUtil;
import com.dingtalk.api.response.OapiUserGetResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 每月之星service
 *
 * @author wangyong
 * @time 2020/5/13 17:30
 */
@Service
public class StarMonthServiceIml implements StarMonthService {

    @Resource
    StarMonthMapper starMonthMapper;


    /**
     * 根据消防站id、日期获取消防站每月之星
     *
     * @param stationId 消防站id
     * @param date      日期
     * @return 每月之星
     * @author wangyong
     */
    @Override
    public StationStarMonth getStationStarMonthByStationId(String stationId, Date date) {

        StationStarMonth stationStarMonths = starMonthMapper.getStationStarMonthByStationId(stationId, date);

        OapiUserGetResponse trainStar = DingDingUtil.getUserDetail(stationStarMonths.getTrainStar().getUserId());
        if (trainStar != null) {
            stationStarMonths.getTrainStar().setImgUrl(trainStar.getAvatar());
            stationStarMonths.getTrainStar().setName(trainStar.getName());
        }
        OapiUserGetResponse disciplineStar = DingDingUtil.getUserDetail(stationStarMonths.getDisciplineStar().getUserId());
        if (disciplineStar != null) {
            stationStarMonths.getDisciplineStar().setImgUrl(disciplineStar.getAvatar());
            stationStarMonths.getDisciplineStar().setName(disciplineStar.getName());
        }
        OapiUserGetResponse houseStar = DingDingUtil.getUserDetail(stationStarMonths.getHouseStar().getUserId());
        if (houseStar != null) {
            stationStarMonths.getHouseStar().setImgUrl(houseStar.getAvatar());
            stationStarMonths.getHouseStar().setName(houseStar.getName());
        }
        OapiUserGetResponse learningStar = DingDingUtil.getUserDetail(stationStarMonths.getLearningStar().getUserId());
        if (learningStar != null) {
            stationStarMonths.getLearningStar().setImgUrl(learningStar.getAvatar());
            stationStarMonths.getLearningStar().setName(learningStar.getName());
        }

        return stationStarMonths;
    }
}
