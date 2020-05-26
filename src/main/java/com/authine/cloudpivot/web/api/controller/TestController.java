package com.authine.cloudpivot.web.api.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.authine.cloudpivot.engine.enums.ErrCode;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.entity.Test;
import com.authine.cloudpivot.web.api.service.OrgService;
import com.authine.cloudpivot.web.api.service.TestService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping("/getTest")
    public Object getTestData(@RequestParam String token) {

        String url = "http://121.41.27.194/form/detail?sheetCode=ls_station_announcement&objectId=ebb3ceec50834ddf899aa07d1e643531&schemaCode=ls_station_announcement&return=/application/large_screen/application-list/ls_station_announcement?parentId=2c90a43e71afeaba0172119450a349ce&code=ls_station_announcement&openMode&pcUrl&queryCode=&iframeAction=detail";


        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiYXBpIl0sInVzZXJfaWQiOiIyYzkwYTQzZTZlZmU4YjA0MDE2ZWZmYjExOTI3MWM2ZiIsInVzZXJfbmFtZSI6IjI1MDc2NDAzNDE5MzgxNDAiLCJzY29wZSI6WyJyZWFkIl0sIm1vYmlsZSI6ZmFsc2UsImlzQWRtaW4iOnRydWUsImV4cCI6MTU5MDM2NDk3NCwiaXNBcHBBZG1pbiI6ZmFsc2UsImF1dGhvcml0aWVzIjpbIlVTRVIiLCJBVVRIX1NZU1RFTV9NQU5BR0UiXSwianRpIjoiZWY5OWNhNTEtMDNiYS00YzhhLWFmNjgtYzZkZTBhNWUzMGNmIiwiY2xpZW50X2lkIjoiYXBpIn0.sZfC5vYBXzKEufxKvr5lLQwAZt3UG_WcLpjlNRIafXQ4bUHqRrttzYRTXHSZtuC-x3jZkXoAeLFW3-ZiItPn7FicsGafcPuIo7g3EKEOHSjOhEKZZHBGMBLvxgCrl0G51CBSw_uEi_Aiv_CK_aFvJFXvxxn3rR8TN-4WzCto_oJiCgzJPN6n1FRa0SO0NS_SBiab7Pj4JIilF0-WZ-HbfNP3vvty6HaE2TTEfEr3GgbF97qyau2n6CNOeJSwZ_v8H_TMGSYjvXP9i4jhQnrAJ4lFN1f_Xs-j4fQbAMjzSLJ57tIAZzHf2tK0RD4H5l-UTLuRivnzJdIEmVjwjhNYnA");
        HttpEntity<String> request = new HttpEntity<>(headers);

//        restTemplate.getForEntity(url, request, String.class);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
        return this.getErrResponseResult(response, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
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
