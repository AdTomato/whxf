package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.entity.PingceResult;
import com.authine.cloudpivot.web.api.entity.XinliSas;
import com.authine.cloudpivot.web.api.mapper.XinliCePingMapper;
import com.authine.cloudpivot.web.api.service.UpsetPingCeResultService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @Author: weiyao
 * @Date: 2020-04-226
 * @Description: 武汉心里测评
 */
@Service
public class UpsetPingCeResultServiceImpl implements UpsetPingCeResultService {
    @Resource
    private XinliCePingMapper xinliCePingMapper;

    //焦虑
    @Override
    public String upsetResultBySas(String id) {
        XinliSas xinliSas= xinliCePingMapper.getXinliSasById(id);
        if(xinliSas !=null && xinliSas.getCreater() !=null){
            PingceResult pingceResult = xinliCePingMapper.getCepingResultByName(xinliSas.getCreater());
            if(pingceResult !=null && pingceResult.getId() !=null){
                pingceResult.setJiaolvScore(xinliSas.getCount());
                pingceResult.setJiaolvTestDate(xinliSas.getCreatedTime());

                if(pingceResult.getYiyuScore() !=null){
                    Float reuF=xinliSas.getCount()+pingceResult.getYiyuScore();
                    //总分
                    pingceResult.setScoreResult(reuF);
                    //评测结果
                    if(reuF>70){
                        pingceResult.setTestResult("4.极大可能为抑郁症（焦虑症），需进行进一步诊断和专业医学治疗");
                    }else if(reuF>60){
                        pingceResult.setTestResult("3.中度抑郁（焦虑），存在抑郁症（焦虑症）的可能性较大，需持续关注");
                    }else if(reuF>50){
                        pingceResult.setTestResult("2.轻度抑郁（焦虑），出现抑郁（焦虑）情绪的可能性较大，需予以心理辅导和后续测量");
                    }else {
                        pingceResult.setTestResult("1.测量人无抑郁（焦虑）情绪");
                    }
                }
                xinliCePingMapper.updateCepingResult(pingceResult);
            }else{
                //插入测评结果
                PingceResult pingceResults=new PingceResult(xinliSas);
                pingceResults.setJiaolvScore(xinliSas.getCount());
                pingceResults.setJiaolvTestDate(xinliSas.getCreatedTime());
                xinliCePingMapper.insertCepingResult(pingceResults);
            }

        }else{
            return "焦虑Id错误";
        }

        return "更新结果成功";
    }

    //抑郁
    @Override
    public String upsetResultBySds(String id) {
        XinliSas xinliSas= xinliCePingMapper.getXinliSdsById(id);
        if(xinliSas !=null && xinliSas.getCreater() !=null){
            PingceResult pingceResult = xinliCePingMapper.getCepingResultByName(xinliSas.getCreater());
            if(pingceResult !=null && pingceResult.getId() !=null){
                pingceResult.setYiyuScore(xinliSas.getCount());
                pingceResult.setYiyutestDate(xinliSas.getCreatedTime());

                if(pingceResult.getJiaolvScore() !=null){
                    Float reuF=xinliSas.getCount()+pingceResult.getJiaolvScore();
                    //总分
                    pingceResult.setScoreResult(reuF);
                    //评测结果
                    if(reuF>70){
                        pingceResult.setTestResult("4.极大可能为抑郁症（焦虑症），需进行进一步诊断和专业医学治疗");
                    }else if(reuF>60){
                        pingceResult.setTestResult("3.中度抑郁（焦虑），存在抑郁症（焦虑症）的可能性较大，需持续关注");
                    }else if(reuF>50){
                        pingceResult.setTestResult("2.轻度抑郁（焦虑），出现抑郁（焦虑）情绪的可能性较大，需予以心理辅导和后续测量");
                    }else {
                        pingceResult.setTestResult("1.测量人无抑郁（焦虑）情绪");
                    }
                }
                xinliCePingMapper.updateCepingResult(pingceResult);
            }else{
                //插入测评结果
                PingceResult pingceResults=new PingceResult(xinliSas);
                pingceResults.setYiyuScore(xinliSas.getCount());
                pingceResults.setYiyutestDate(xinliSas.getCreatedTime());
                xinliCePingMapper.insertCepingResult(pingceResults);
            }

        }else{
            return "抑郁Id错误";
        }

        return "更新结果成功";
    }
}
