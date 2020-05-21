package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.entity.StationStarMonth;
import com.authine.cloudpivot.web.api.mapper.StarMonthMapper;
import com.authine.cloudpivot.web.api.service.StarMonthService;
import com.authine.cloudpivot.web.api.utils.DingDingUtil;
import com.dingtalk.api.response.OapiUserGetResponse;
import org.apache.commons.lang3.StringUtils;
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
        if (stationStarMonths != null) {
            if (!StringUtils.isEmpty(stationStarMonths.getLearningStarImg())) {
                stationStarMonths.setLearningStarImg("http://121.41.27.194/api/api/aliyun/download?refId=" + stationStarMonths.getLearningStarImg());
                stationStarMonths.getLearningStar().setImgUrl(stationStarMonths.getLearningStarImg());
            }
            if (!StringUtils.isEmpty(stationStarMonths.getDisciplineStarImg())) {
                stationStarMonths.setDisciplineStarImg("http://121.41.27.194/api/api/aliyun/download?refId=" + stationStarMonths.getDisciplineStarImg());
                stationStarMonths.getDisciplineStar().setImgUrl(stationStarMonths.getDisciplineStarImg());
            }
            if (!StringUtils.isEmpty(stationStarMonths.getTrainStarImg())) {
                stationStarMonths.setTrainStarImg("http://121.41.27.194/api/api/aliyun/download?refId=" + stationStarMonths.getTrainStarImg());
                stationStarMonths.getTrainStar().setImgUrl(stationStarMonths.getTrainStarImg());
            }
            if (!StringUtils.isEmpty(stationStarMonths.getHouseStarImg())) {
                stationStarMonths.setHouseStarImg("http://121.41.27.194/api/api/aliyun/download?refId=" + stationStarMonths.getHouseStarImg());
                stationStarMonths.getHouseStar().setImgUrl(stationStarMonths.getHouseStarImg());
            }
        }

        return stationStarMonths;
    }
}
