package com.authine.cloudpivot.web.api.controller;

import com.authine.cloudpivot.engine.enums.ErrCode;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.entity.PersonalName;
import com.authine.cloudpivot.web.api.service.BrigadeService;
import com.authine.cloudpivot.web.api.service.LotteryService;
import com.authine.cloudpivot.web.api.service.PersonalNameService;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: wangyong
 * @time: 2020/4/26 14:08
 * @Description: 抽签模块
 */
@RestController
@Slf4j
@RequestMapping("/controller/draw")
public class DrawController extends BaseController {

    @Autowired
    LotteryService lotteryService;

    @Autowired
    BrigadeService brigadeService;

    @Autowired
    PersonalNameService personalNameService;

    /**
     * 获取抽签项目id以及项目名称
     *
     * @return 项目id以及项目名称
     * @author wangyong
     */
    @GetMapping("/getLotteryNameAndId")
    public ResponseResult<Object> getLotteryNameAndId() {
        List<Map<String, String>>  lotteryNameAndId = lotteryService.getLotteryNameAndId();
        return this.getErrResponseResult(lotteryNameAndId, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }

    /**
     * 根据抽签项目id 获取大队名称以及大队id
     *
     * @param lotteryId 抽签项目id
     * @return 大队名称，大队id
     */
    @GetMapping("/getBrigadeNameAndId")
    public ResponseResult<Object> getBrigadeNameAndId(@RequestParam String lotteryId) {
        List<Map<String, String>>  brigadeNameAndId = brigadeService.getBrigadeNameAndId(lotteryId);
        return this.getErrResponseResult(brigadeNameAndId, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }

    /**
     * 获取所有大队下面的参与抽签人的人员列表
     *
     * @param brigadeId 大队id
     * @return 大队人员列表
     * @author wangyong
     */
    @GetMapping("/getAllBrigadePersonal")
    public ResponseResult<Object> getAllBrigadePersonal(@RequestParam String brigadeId) {
        List<PersonalName> allBrigadePersonal = personalNameService.getAllBrigadePersonal(brigadeId);
        Map<String, List<String>> result = new HashMap<>();
        for (PersonalName personalName : allBrigadePersonal) {
            if (!result.containsKey(personalName.getJobTitle())) {
                List<String> personal = new ArrayList<>();
                personal.add(personalName.getPersonalName());
                result.put(personalName.getJobTitle(), personal);
            } else {
                result.get(personalName.getJobTitle()).add(personalName.getPersonalName());
            }
        }
        return this.getErrResponseResult(result, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }

}
