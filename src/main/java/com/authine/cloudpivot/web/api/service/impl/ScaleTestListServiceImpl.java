package com.authine.cloudpivot.web.api.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.authine.cloudpivot.web.api.dto.ScaleTestList;
import com.authine.cloudpivot.web.api.entity.*;
import com.authine.cloudpivot.web.api.mapper.ScaleTestListMapper;
import com.authine.cloudpivot.web.api.mapper.ScaleTestMapper;
import com.authine.cloudpivot.web.api.service.ScaleTestListService;
import com.authine.cloudpivot.web.api.service.ScaleTestService;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.alibaba.fastjson.JSON;
/**
 * @Author Ke LongHai
 * @Date 2020/7/31 15:57
 * @Version 1.0
 */
@Service
public class ScaleTestListServiceImpl implements ScaleTestListService {

    @Resource
    ScaleTestListMapper scaleTestListMapper;
    @Resource
    ScaleTestMapper scaleTestMapper;

    @Override
    public List<ScaleTestList> getScaleTestList(String id) {
        List<ScaleTestList> scaleTestList = scaleTestListMapper.getScaleTestList(id);
        if (scaleTestList != null){
            for (ScaleTestList testList : scaleTestList) {
                String img=scaleTestMapper.getImgUrl(id);
                testList.setImgUrl("http://121.41.27.194/api/api/aliyun/download?refId=" + img);
                List<ScaleTestDetail> scaleTestDetails = testList.getScaleTestDetails();
                for (ScaleTestDetail scaleTestDetail : scaleTestDetails) {
                    System.out.println("scaleTestDetail = " + scaleTestDetail);
                    String optionResult = scaleTestDetail.getOptionResult();
                    System.out.println("optionResult = " + optionResult);
                    JSONArray json = (JSONArray)JSON.parse(optionResult);
                    List<OptionAndScore> os = JSON.parseArray(optionResult, OptionAndScore.class);
                    scaleTestDetail.setOptionAndScoreList(os);



                }
            }
        }
        return scaleTestList;
//        return scaleTestListMapper.getScaleTestList(id);
    }



}
