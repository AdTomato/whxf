package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.entity.PsychologyManData;
import com.authine.cloudpivot.web.api.mapper.PsychologyManDataMapper;
import com.authine.cloudpivot.web.api.service.PsychologyManDataService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Ke LongHai
 * @Date 2020/8/5 12:49
 * @Version 1.0
 */

@Service
public class PsychologyManDataServiceImpl implements PsychologyManDataService {

    @Resource
    PsychologyManDataMapper psychologyManDataMapper;

    @Override
    public PsychologyManData getPsychologyManDataId(String id) {
        PsychologyManData  stationStarMonths = psychologyManDataMapper.getPsychologyManDataId(id);
        if ( stationStarMonths != null) {
                if (!StringUtils.isEmpty(stationStarMonths.getPhoto())) {
                    stationStarMonths.setPhoto("http://121.41.27.194/api/api/aliyun/download?refId=" + stationStarMonths.getPhoto());
                }
            }

        return stationStarMonths;
    }

    @Override
    public List<PsychologyManData> getPsychologyManData(String id) {
        List<PsychologyManData> stationStarMonths = psychologyManDataMapper.getPsychologyManData(id);
        if (stationStarMonths != null) {
            for (PsychologyManData stationStarMonth : stationStarMonths) {
                if (!StringUtils.isEmpty(stationStarMonth.getPhoto())) {
                    stationStarMonth.setPhoto("http://121.41.27.194/api/api/aliyun/download?refId=" + stationStarMonth.getPhoto());

                }

            }
        }
        return stationStarMonths;
    }

    public List<PsychologyManData> getPsychologyManDataList(PsychologyManData  info){
        List<PsychologyManData> stationStarMonths = psychologyManDataMapper.getPsychologyManDataList(info);
        if (stationStarMonths != null) {
            for (PsychologyManData stationStarMonth : stationStarMonths) {
                if (!StringUtils.isEmpty(stationStarMonth.getPhoto())) {
                    stationStarMonth.setPhoto("http://121.41.27.194/api/api/aliyun/download?refId=" + stationStarMonth.getPhoto());

                }

            }
        }
        return stationStarMonths;
    };
}
