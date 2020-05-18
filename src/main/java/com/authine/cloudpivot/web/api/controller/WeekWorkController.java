package com.authine.cloudpivot.web.api.controller;


import com.authine.cloudpivot.engine.enums.ErrCode;
import com.authine.cloudpivot.web.api.dto.WeekFocusDto;
import com.authine.cloudpivot.web.api.service.WeekWorkService;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 本周重点工作控制层
 * @Author Ke LongHai
 * @Date 2020/5/18 9:56
 * @Version 1.0
 */

@Api(value = "工作重点信息接口", tags = "工作重点信息接口")
@RestController
@Slf4j
@RequestMapping("/controller/weekWork")
public class WeekWorkController extends BaseController {

    @Autowired
    WeekWorkService weekWorkService;

    /**
     *根据大队id获取该队的本周重点工作信息
     * @param brigadeId
     * @return  大队本周重点工作信息
     */
    @ApiOperation(value = "查询大队本周所有工作信息接口")
    @GetMapping("/getWeekWorks")
    public ResponseResult<Object> getWeekWorks(@RequestParam String brigadeId) {

        List<WeekFocusDto> weekWorks = weekWorkService.getWeekWorksByBrigadeId (brigadeId);

        return this.getErrResponseResult(weekWorks, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }

    /**
     *  根据大队id更新该队的本周重点工作信息的状态
     * @param id    工作重点id
     * @param status    工作完成进度的状态
     */
    @ApiOperation(value = "更新工作完成进度的状态")
    @PutMapping("/updateWorksStatus")
    public ResponseResult<Object> updateWorksStatus(@RequestParam String id, @RequestParam String status) {
        weekWorkService.updateWorksStatusById(id, status);
        return this.getErrResponseResult("更新成功", ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }

}
