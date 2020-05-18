package com.authine.cloudpivot.web.api.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.authine.cloudpivot.engine.enums.ErrCode;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * 天气
 *
 * @author wangyong
 * @time 2020/5/18 15:57
 */
@RestController
@RequestMapping("/controller/weather")
@Api(value = "天气", tags = "二次开发：天气")
@Slf4j
public class WeatherController extends BaseController {

    @Autowired
    RestTemplate restTemplate;

    @ApiOperation(value = "根据城市获取城市相关天气")
    @GetMapping("getWeatherByCityCode")
    public ResponseResult<Object> getWeatherByCityCode(@ApiParam("城市编码") String cityCode) {
        Map<String, Object> result = new HashMap<>();
        getWeather(result, "wuhan");
        getAirQuality(result, "wuhan");
        getHighAndLowTemperature(result, "wuhan");
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
