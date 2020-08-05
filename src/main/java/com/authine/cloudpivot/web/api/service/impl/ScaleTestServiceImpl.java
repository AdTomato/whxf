package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.entity.ScaleTest;
import com.authine.cloudpivot.web.api.entity.ScaleTypeSmall;
import com.authine.cloudpivot.web.api.mapper.ScaleTestMapper;
import com.authine.cloudpivot.web.api.mapper.ScaleTypeSmallMapper;
import com.authine.cloudpivot.web.api.service.ScaleTestService;
import com.authine.cloudpivot.web.api.service.ScaleTypeSmallService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Ke LongHai
 * @Date 2020/7/31 15:57
 * @Version 1.0
 */
@Service
public class ScaleTestServiceImpl implements ScaleTestService {

    @Resource
    ScaleTestMapper scaleTestMapper;
    @Override
    public List<ScaleTest> getScaleTestOne(String id) {

        return scaleTestMapper.getScaleTestOne(id);
    }

        public List<ScaleTest> getScaleTestId(String id) {

            List<ScaleTest> stationStarMonths = scaleTestMapper.getScaleTestId(id);
            if (stationStarMonths != null && stationStarMonths.size()>0) {
                for (ScaleTest st : stationStarMonths) {
                    if (!StringUtils.isEmpty(st.getImgUrl())) {
                        st.setImgUrl("http://121.41.27.194/api/api/aliyun/download?refId=" + st.getImgUrl());

                    }
                }
            }
                return stationStarMonths;

            }

   /* @Override
    public List<ScaleTest> getScaleTestAll(String id) {
        return scaleTestMapper.getScaleTestAll(id);
    }*/


}
