package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.entity.QuantiAssessment;
import com.authine.cloudpivot.web.api.entity.QuantiAssessmentMonthInfo;
import com.authine.cloudpivot.web.api.entity.QuantiAssessmentMonthInfoList;
import com.authine.cloudpivot.web.api.mapper.QuantiAssessmentMapper;
import com.authine.cloudpivot.web.api.service.QuantiAssessmentService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/*
 @author:weiyao
 @time:2020-05-15
 @desc:量化考评周报
  */
@Service
public class QuantiAssessmentServiceImpl implements QuantiAssessmentService {

    @Resource
    QuantiAssessmentMapper quantiAssessmentMapper;

    @Override
    public List<QuantiAssessment> getAssess(String stationId) {
        return quantiAssessmentMapper.getAssess(stationId);
    }

    @Override
    public void updateQuantiAssessmentById(QuantiAssessment quantiAssessment) {
        quantiAssessmentMapper.updateQuantiAssessmentById(quantiAssessment);
    }

    @Override
    public List<QuantiAssessmentMonthInfoList> getAssessMonthDetail(String stationId){
        List<QuantiAssessmentMonthInfoList> assessMonthList=new ArrayList<>();
        //查询部门
        List<String> teams=  quantiAssessmentMapper.getTeamforMonth(stationId);
        if(!CollectionUtils.isEmpty(teams)){
            for(String team:teams){
                QuantiAssessmentMonthInfoList monthInfoList=new QuantiAssessmentMonthInfoList();
                monthInfoList.setTeam(team);

                //查询人员姓名
                List<String> names=quantiAssessmentMapper.getNameforTeam(stationId,team);
                if(!CollectionUtils.isEmpty(names)){
                    for(String name:names){
                        QuantiAssessmentMonthInfo monthInfo=new QuantiAssessmentMonthInfo();
                        monthInfo.setName(name);
                        Integer zzsx=quantiAssessmentMapper.getSumScoreforNameCurrentMonth(stationId,name,"政治思想");
                        if(zzsx!=null){
                            monthInfo.setZzsxScore(zzsx);
                        }
                        Integer fcml=quantiAssessmentMapper.getSumScoreforNameCurrentMonth(stationId,name,"服从命令");
                        if(fcml!=null){
                            monthInfo.setFcmlScore(fcml);
                        }
                        Integer lxzz=quantiAssessmentMapper.getSumScoreforNameCurrentMonth(stationId,name,"履行职责");
                        if(lxzz!=null){
                            monthInfo.setLxzzScore(lxzz);
                        }
                        Integer zzsj=quantiAssessmentMapper.getSumScoreforNameCurrentMonth(stationId,name,"遵章守纪");
                        if(zzsj!=null){
                            monthInfo.setZzsjScore(zzsj);
                        }
                        Integer zfyc=quantiAssessmentMapper.getSumScoreforNameCurrentMonth(stationId,name,"作风养成");
                        if(zfyc!=null){
                            monthInfo.setZfycScore(zfyc);
                        }
                        Integer shzd=quantiAssessmentMapper.getSumScoreforNameCurrentMonth(stationId,name,"一日生活制度");
                        if(shzd!=null){
                            monthInfo.setShzdScore(shzd);
                        }
                        Integer jsxl=quantiAssessmentMapper.getSumScoreforNameCurrentMonth(stationId,name,"军事训练");
                        if(jsxl!=null){
                            monthInfo.setJsxlScore(jsxl);
                        }
                        Integer mhjy=quantiAssessmentMapper.getSumScoreforNameCurrentMonth(stationId,name,"灭火救援");
                        if(mhjy!=null){
                            monthInfo.setMhjyScore(mhjy);
                        }
                        //上月总分
                        Integer lastMonthScre=quantiAssessmentMapper.getSumScoreforNameLastMonth(stationId,name);
                        monthInfo.setLastMonthScore(lastMonthScre);
                        //当月总分
                        Integer currentMonthScore=quantiAssessmentMapper.getSumScoreforNameCurrentMonth(stationId,name,null);
                        currentMonthScore=currentMonthScore==null?0:currentMonthScore;
                        monthInfo.setCurrentMonthScore(currentMonthScore+100);

                        monthInfoList.getQuantiAssessmentMonthInfoList().add(monthInfo);
                    }

                }
                assessMonthList.add(monthInfoList);
            }
        }

        return assessMonthList;
    }
}
