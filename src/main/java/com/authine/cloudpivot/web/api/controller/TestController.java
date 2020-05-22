package com.authine.cloudpivot.web.api.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.authine.cloudpivot.engine.enums.ErrCode;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.entity.Test;
import com.authine.cloudpivot.web.api.service.OrgService;
import com.authine.cloudpivot.web.api.service.TestService;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import io.swagger.util.Json;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: wangyong
 * @time: 2020/4/24 12:58
 * @Description:
 */
@RestController
@RequestMapping("/controller/testController")
public class TestController extends BaseController {

    @Autowired
    TestService testService;

    @Autowired
    OrgService orgService;

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/getAllTestData")
    public Object getAllTestData() {
        Object result = null;
        result = orgService.getStationPassword("af127c960a8b490683a1ff9c57b83163", "2c90a43e6efe8b04016effb119271c6f");
        result = orgService.getBrigadePassword("586d63454d6841dfa667405212572ca7", "2c90a43e6eda16b5016ef3d7088225fd");
        result = orgService.getDetachmentPassword("f05e6ce21b4942ccb3bd2e90b7a936dc", "2c90a43e6eb51314016eb667507239e9");
        return this.getErrResponseResult(result, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }

    /**
     * 获取天气实况
     *
     * @param result 结果
     * @param city   城市
     * @author wangyong
     */
    private void getWeather(Map<String, Object> result, String city) {
        String forObject = restTemplate.getForObject("https://api.seniverse.com/v3/weather/now.json?key=S2FU4pzhy2uHwdUpv&location=" + city + "&language=zh-Hans&unit=c", String.class);
        if (!StringUtils.isEmpty(forObject)) {
            JSONObject data = (JSONObject) JSONObject.parse(forObject);
            if (data != null) {
                JSONArray results = data.getJSONArray("results");
                if (results != null) {
                    JSONObject jsonObject = results.getJSONObject(0);
                    if (jsonObject != null) {
                        JSONObject now = jsonObject.getJSONObject("now");
                        if (now != null) {
                            result.put("code", now.get("code"));
                            result.put("temperature", now.get("temperature"));
                            result.put("humidity", now.get("humidity"));
                            result.put("visibility", now.get("visibility"));
                            result.put("wind_direction", now.get("wind_direction"));
                            result.put("wind_scale", now.get("wind_scale"));
                        }
                    }
                }
            }
        }





    }

    /**
     * 获取空气质量类别
     *
     * @param result 结果
     * @param city   城市
     * @author wangyong
     */
    private void getAirQuality(Map<String, Object> result, String city) {
        String forObject = restTemplate.getForObject("https://api.seniverse.com/v3/air/now.json?key=S2FU4pzhy2uHwdUpv&location=" + city + "&language=zh-Hans&scope=city", String.class);
        if (!StringUtils.isEmpty(forObject)) {
            JSONObject data = (JSONObject) JSONObject.parse(forObject);
            if (data != null) {
                JSONArray results = data.getJSONArray("results");
                if (results != null) {
                    JSONObject jsonObject = results.getJSONObject(0);
                    if (jsonObject != null) {
                        JSONObject air = jsonObject.getJSONObject("air");
                        if (air != null) {
                            JSONObject city1 = air.getJSONObject("city");
                            if (city1 != null) {
                                result.put("quality", city1.get("quality"));
                            }
                        }
                    }
                }
            }


        }
    }

    /**
     * 获取最高最低温度
     *
     * @param result 结果
     * @param city   城市
     * @author wangyong
     */
    private void getHighAndLowTemperature(Map<String, Object> result, String city) {
        String forObject = restTemplate.getForObject("https://api.seniverse.com/v3/weather/daily.json?key=S2FU4pzhy2uHwdUpv&location=" + city + "&language=zh-Hans&unit=c&start=0&days=1", String.class);
        if (!StringUtils.isEmpty(forObject)) {
            JSONObject data = (JSONObject) JSONObject.parse(forObject);
            if (data != null) {
                JSONArray results = data.getJSONArray("results");
                if (results != null) {
                    JSONObject jsonObject = results.getJSONObject(0);
                    if (jsonObject != null) {
                        JSONArray daily = jsonObject.getJSONArray("daily");
                        if (daily != null) {
                            JSONObject jsonObject1 = daily.getJSONObject(0);
                            if (jsonObject1 != null) {
                                result.put("high", jsonObject1.get("high"));
                                result.put("low", jsonObject1.get("low"));
                            }
                        }
                    }
                }
            }
        }
    }

}
