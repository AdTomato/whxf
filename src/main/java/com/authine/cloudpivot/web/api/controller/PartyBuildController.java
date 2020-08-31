package com.authine.cloudpivot.web.api.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * 描述: 党建对接
 *
 * @author wangyong
 */
@RequestMapping("/controller/partyBuild")
@RestController
@Api(value = "党建数据对接", tags = "党建数据对接")
public class PartyBuildController extends BaseController {

    private static final String HUPING_URL = "http://119.whudml.com:8080/dingdingapi.asmx/GetHuping";
    private static final String ZIPING_URL = "http://119.whudml.com:8080/dingdingapi.asmx/GetZiping";
    private static final String STUDY_URL = "http://119.whudml.com:8080/dingdingapi.asmx/GetStudy";
    @Autowired
    RestTemplate restTemplate;

    @ApiOperation(value = "互评数据", notes = "互评数据", httpMethod = "GET")
    @GetMapping("/huping")
    public ResponseResult<Object> huping(
            @ApiParam(value = "钉钉用户id") @RequestParam(required = true) String userId) {
        if (StringUtils.isBlank(userId)) {
            return this.getErrResponseResult(null, 404L, "参数不能为空");
        }
        String url = HUPING_URL + "?userId=" + userId;
        String result = restTemplate.getForObject(url, String.class);
        if (StringUtils.isBlank(result)) {
            return this.getErrResponseResult(null, 405L, "没有获取数据");
        }
        JSONObject parse = (JSONObject) JSON.parse(result);
        Integer errcode = (Integer) parse.get("errcode");
        if (errcode == null || !errcode.equals(0)) {
            return this.getErrResponseResult(null, 405L, "没有获取数据");
        }
        JSONObject data = parse.getJSONObject("data");
        return this.getErrResponseResult(data, 200L, "获取成功");
    }

    @ApiOperation(value = "自评数据", notes = "自评数据", httpMethod = "GET")
    @GetMapping("ziping")
    public ResponseResult<Object> ziping(
            @ApiParam("钉钉用户id") @RequestParam(required = true) String userId) {
        if (StringUtils.isBlank(userId)) {
            return this.getErrResponseResult(null, 404L, "参数不能为空");
        }
        String url = ZIPING_URL + "?userId=" + userId;
        String result = restTemplate.getForObject(url, String.class);
        if (StringUtils.isBlank(result)) {
            return this.getErrResponseResult(null, 405L, "没有获取数据");
        }
        JSONObject parse = JSON.parseObject(result);
        Integer errCode = (Integer) parse.get("errcode");
        if (errCode == null || !errCode.equals(0)) {
            return this.getErrResponseResult(null, 405L, "没有获取数据");
        }
        JSONObject data = parse.getJSONObject("data");
        return this.getErrResponseResult(data, 200L, "获取成功");
    }

    @ApiOperation(value = "学习情况", notes = "学习情况", httpMethod = "GET")
    @GetMapping("/study")
    public ResponseResult<Object> study(
            @ApiParam("钉钉用户id") @RequestParam(required = true) String userId) {
        if (StringUtils.isBlank(userId)) {
            return this.getErrResponseResult(null, 404L, "参数不能为空");
        }
        String url = STUDY_URL + "?userId=" + userId;
        String result = restTemplate.getForObject(url, String.class);
        if (StringUtils.isBlank(result)) {
            return this.getErrResponseResult(null, 405L, "没有获取数据");
        }
        JSONObject parse = JSON.parseObject(result);
        Integer errCode = (Integer) parse.get("errcode");
        if (errCode == null || !errCode.equals(0)) {
            return this.getErrResponseResult(null, 405L, "没有获取数据");
        }
        JSONObject data = parse.getJSONObject("data");
        return this.getErrResponseResult(data, 200L, "获取成功");
    }

}
