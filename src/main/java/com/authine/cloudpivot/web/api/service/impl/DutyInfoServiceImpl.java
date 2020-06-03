package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.dto.BrigadeDutyInfoDto;
import com.authine.cloudpivot.web.api.dto.StationDutyInfoDto;
import com.authine.cloudpivot.web.api.entity.BrigadeHeadquarter;
import com.authine.cloudpivot.web.api.entity.CommandAssistant;
import com.authine.cloudpivot.web.api.entity.Commander;
import com.authine.cloudpivot.web.api.entity.StationDutyCadre;
import com.authine.cloudpivot.web.api.mapper.DutyInfoMapper;
import com.authine.cloudpivot.web.api.service.DutyInfoService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;

/**
 * 值班信息service
 *
 * @author wangyong
 * @time 2020/5/13 15:06
 */
@Service
public class DutyInfoServiceImpl implements DutyInfoService {

    @Resource
    DutyInfoMapper dutyInfoMapper;

    /**
     * 根据消防站id，日期获取消防站的值班信息
     *
     * @param stationId 消防站id
     * @param date      日期
     * @return 消防站值班信息
     * @author wangyong
     */
    @Override
    public StationDutyInfoDto getStationDutyInfoByStationId(String stationId, Date date) {
        StationDutyInfoDto stationDutyInfoDto = dutyInfoMapper.getStationDutyInfoByStationId(stationId, date);
        if (stationDutyInfoDto != null) {
            stationDutyInfoDto.setBrigadeHeadquarters(new ArrayList<>());
            stationDutyInfoDto.setStationDutyCadres(new ArrayList<>());
            if (!StringUtils.isEmpty(stationDutyInfoDto.getUserName1())) {
                BrigadeHeadquarter brigadeHeadquarter = new BrigadeHeadquarter();
                brigadeHeadquarter.setDutyName(stationDutyInfoDto.getUserName1());
                stationDutyInfoDto.getBrigadeHeadquarters().add(brigadeHeadquarter);
            }

            if (!StringUtils.isEmpty(stationDutyInfoDto.getUserName2())) {
                BrigadeHeadquarter brigadeHeadquarter = new BrigadeHeadquarter();
                brigadeHeadquarter.setDutyName(stationDutyInfoDto.getUserName2());
                stationDutyInfoDto.getBrigadeHeadquarters().add(brigadeHeadquarter);
            }

            if (!StringUtils.isEmpty(stationDutyInfoDto.getUserName3())) {
                BrigadeHeadquarter brigadeHeadquarter = new BrigadeHeadquarter();
                brigadeHeadquarter.setDutyName(stationDutyInfoDto.getUserName3());
                stationDutyInfoDto.getBrigadeHeadquarters().add(brigadeHeadquarter);
            }

            if (!StringUtils.isEmpty(stationDutyInfoDto.getUserName4())) {
                BrigadeHeadquarter brigadeHeadquarter = new BrigadeHeadquarter();
                brigadeHeadquarter.setDutyName(stationDutyInfoDto.getUserName4());
                stationDutyInfoDto.getBrigadeHeadquarters().add(brigadeHeadquarter);
            }
            if (!StringUtils.isEmpty(stationDutyInfoDto.getUserName5())) {
                BrigadeHeadquarter brigadeHeadquarter = new BrigadeHeadquarter();
                brigadeHeadquarter.setDutyName(stationDutyInfoDto.getUserName5());
                stationDutyInfoDto.getBrigadeHeadquarters().add(brigadeHeadquarter);
            }

            if (!StringUtils.isEmpty(stationDutyInfoDto.getUserName6())) {
                BrigadeHeadquarter brigadeHeadquarter = new BrigadeHeadquarter();
                brigadeHeadquarter.setDutyName(stationDutyInfoDto.getUserName6());
                stationDutyInfoDto.getBrigadeHeadquarters().add(brigadeHeadquarter);
            }

            if (!StringUtils.isEmpty(stationDutyInfoDto.getUserName7())) {
                StationDutyCadre stationDutyCadre = new StationDutyCadre();
                stationDutyCadre.setDutyName(stationDutyInfoDto.getUserName7());
                stationDutyInfoDto.getStationDutyCadres().add(stationDutyCadre);
            }

            if (!StringUtils.isEmpty(stationDutyInfoDto.getUserName8())) {
                StationDutyCadre stationDutyCadre = new StationDutyCadre();
                stationDutyCadre.setDutyName(stationDutyInfoDto.getUserName8());
                stationDutyInfoDto.getStationDutyCadres().add(stationDutyCadre);
            }

            if (!StringUtils.isEmpty(stationDutyInfoDto.getUserName9())) {
                StationDutyCadre stationDutyCadre = new StationDutyCadre();
                stationDutyCadre.setDutyName(stationDutyInfoDto.getUserName9());
                stationDutyInfoDto.getStationDutyCadres().add(stationDutyCadre);
            }

            if (!StringUtils.isEmpty(stationDutyInfoDto.getUserName10())) {
                StationDutyCadre stationDutyCadre = new StationDutyCadre();
                stationDutyCadre.setDutyName(stationDutyInfoDto.getUserName10());
                stationDutyInfoDto.getStationDutyCadres().add(stationDutyCadre);
            }

            if (!StringUtils.isEmpty(stationDutyInfoDto.getUserName11())) {
                StationDutyCadre stationDutyCadre = new StationDutyCadre();
                stationDutyCadre.setDutyName(stationDutyInfoDto.getUserName11());
                stationDutyInfoDto.getStationDutyCadres().add(stationDutyCadre);
            }

            if (!StringUtils.isEmpty(stationDutyInfoDto.getUserName12())) {
                StationDutyCadre stationDutyCadre = new StationDutyCadre();
                stationDutyCadre.setDutyName(stationDutyInfoDto.getUserName12());
                stationDutyInfoDto.getStationDutyCadres().add(stationDutyCadre);
            }

        }

        return stationDutyInfoDto;
    }


    /**
     * 根据大队id，日期获取大队的值班信息
     *
     * @param brigadeId 大队id
     * @param date      日期
     * @return 大队值班信息
     * @author wangyong
     */
    @Override
    public BrigadeDutyInfoDto getBrigadeDutyInfoByBrigadeId(String brigadeId, Date date) {
        BrigadeDutyInfoDto brigadeDutyInfoDto = dutyInfoMapper.getBrigadeDutyInfoByBrigadeId(brigadeId, date);
        if (brigadeDutyInfoDto != null) {

            brigadeDutyInfoDto.setCommandAssistants(new ArrayList<>());
            brigadeDutyInfoDto.setCommanders(new ArrayList<>());

            if (!StringUtils.isEmpty(brigadeDutyInfoDto.getUserName1())) {
                Commander commander = new Commander();
                commander.setDutyName(brigadeDutyInfoDto.getUserName1());
                brigadeDutyInfoDto.getCommanders().add(commander);
            }

            if (!StringUtils.isEmpty(brigadeDutyInfoDto.getUserName2())) {
                Commander commander = new Commander();
                commander.setDutyName(brigadeDutyInfoDto.getUserName2());
                brigadeDutyInfoDto.getCommanders().add(commander);
            }

            if (!StringUtils.isEmpty(brigadeDutyInfoDto.getUserName3())) {
                Commander commander = new Commander();
                commander.setDutyName(brigadeDutyInfoDto.getUserName3());
                brigadeDutyInfoDto.getCommanders().add(commander);
            }

            if (!StringUtils.isEmpty(brigadeDutyInfoDto.getUserName4())) {
                CommandAssistant commandAssistant = new CommandAssistant();
                commandAssistant.setDutyName(brigadeDutyInfoDto.getUserName4());
                brigadeDutyInfoDto.getCommandAssistants().add(commandAssistant);
            }

            if (!StringUtils.isEmpty(brigadeDutyInfoDto.getUserName5())) {
                CommandAssistant commandAssistant = new CommandAssistant();
                commandAssistant.setDutyName(brigadeDutyInfoDto.getUserName5());
                brigadeDutyInfoDto.getCommandAssistants().add(commandAssistant);
            }

            if (!StringUtils.isEmpty(brigadeDutyInfoDto.getUserName6())) {
                CommandAssistant commandAssistant = new CommandAssistant();
                commandAssistant.setDutyName(brigadeDutyInfoDto.getUserName6());
                brigadeDutyInfoDto.getCommandAssistants().add(commandAssistant);
            }
        }

        return brigadeDutyInfoDto;
    }
}
