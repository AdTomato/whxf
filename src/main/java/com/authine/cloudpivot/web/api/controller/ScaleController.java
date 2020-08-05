package com.authine.cloudpivot.web.api.controller;

import com.authine.cloudpivot.engine.enums.ErrCode;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.dto.ScaleTestList;
import com.authine.cloudpivot.web.api.entity.ScaleTest;
import com.authine.cloudpivot.web.api.entity.ScaleTypeBig;
import com.authine.cloudpivot.web.api.service.ScaleTestListService;
import com.authine.cloudpivot.web.api.service.ScaleTestService;
import com.authine.cloudpivot.web.api.service.ScaleTypeSmallService;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author Ke LongHai
 * @Date 2020/8/4 14:43
 * @Version 1.0
 */
@Api(value = "量表信息", tags = "量表信息")
@RestController
@Slf4j
@RequestMapping("/controller/ScaleTest")
public class ScaleController extends BaseController {


    @Autowired
    private ScaleTypeSmallService scaleTypeSmallService;

    @Autowired
    private ScaleTestService scaleTestService;

    @Autowired
    private ScaleTestListService scaleTestListService;

    @ApiOperation("获取一二级分类信息")
    @GetMapping("/getScaleTypeSmallAll")
    public ResponseResult<Object> getScaleTypeSmallAll() {

        List<ScaleTypeBig> scaleType = scaleTypeSmallService.getScaleTypeAll();
        return this.getErrResponseResult(scaleType, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());

    }

    @ApiOperation("获取单个小标题下的量表信息包含分类标题信息含图片地址")
    @GetMapping("/getScaleTestId")
    public ResponseResult<Object> getScaleTestId(String id) {
        if(StringUtils.isNotEmpty(id)){
            List<ScaleTest> scaleTestId = scaleTestService.getScaleTestId(id);
            return this.getErrResponseResult(scaleTestId, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
        }else{
            return this.getErrResponseResult(null, 404L, "没有小标题Id");
        }

    }


    @ApiOperation("获取单个量表结果信息")
    @GetMapping("/getScaleTestList")
    public ResponseResult<Object> getScaleTestList(String id) {
        if(StringUtils.isNotEmpty(id)){
            List<ScaleTestList> scaleTypeList= scaleTestListService.getScaleTestList(id);
            return this.getErrResponseResult(scaleTypeList, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
        }else{
            return this.getErrResponseResult(null, 404L, "没有小标题Id");
        }
    }
}
