package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.entity.Pcl90;
import com.authine.cloudpivot.web.api.entity.Pcl90Result;
import com.authine.cloudpivot.web.api.entity.PingceResult;
import com.authine.cloudpivot.web.api.entity.XinliSas;
import com.authine.cloudpivot.web.api.mapper.Pcl90Mapper;
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

    @Resource
    private Pcl90Mapper pcl90Mapper;

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

    //修改pcl90测评数据
    @Override
    public String upsetScl90Result(String id) {
        Pcl90 pcl90= pcl90Mapper.getPcl90ById(id);
        if(pcl90 !=null && pcl90.getCreater() !=null){
            Pcl90Result pingceResult = pcl90Mapper.getResultByNameAndDate(pcl90.getCreater());
            if(pingceResult !=null && pingceResult.getId() !=null){
                pingceResult= setPcl90Result(pingceResult,pcl90);

                pcl90Mapper.updatePcl90Result(pingceResult);
            }else{
                //插入测评结果
                Pcl90Result pingceResults=new Pcl90Result(pcl90);
                pingceResults=setPcl90Result(pingceResults,pcl90);

                pcl90Mapper.insertPcl90Result(pingceResults);
            }

        }else{
            return "Scl90的Id错误";
        }

        return "更新结果成功";
    }

    private String getRemark(Float f){
        String remark="1.正常";
        if(f>4){
            remark="4.整体症状强烈";
        }else if(f>3){
            remark="3.整体症状明显";
        }else if(f>2){
            remark="2.整体症状轻微";
        }else {
            return remark;
        }
        return remark;
    }

    private Pcl90Result setPcl90Result(Pcl90Result pingceResult,Pcl90 pcl90){
        //总分
        pingceResult.setScoreResult(pcl90.getCount());
        //评测结果
        if(pcl90.getCount()>360){
            pingceResult.setTestResult("4.整体症状强烈");
        }else if(pcl90.getCount()>270){
            pingceResult.setTestResult("3.整体症状明显");
        }else if(pcl90.getCount()>180){
            pingceResult.setTestResult("2.整体症状轻微");
        }else {
            pingceResult.setTestResult("1.整体正常");
        }
        pingceResult.setF1(pcl90.getF1());
        pingceResult.setF2(pcl90.getF2());
        pingceResult.setF3(pcl90.getF3());
        pingceResult.setF4(pcl90.getF4());
        pingceResult.setF5(pcl90.getF5());
        pingceResult.setF6(pcl90.getF6());
        pingceResult.setF7(pcl90.getF7());
        pingceResult.setF8(pcl90.getF8());
        pingceResult.setF9(pcl90.getF9());
        pingceResult.setF10(pcl90.getF10());

        pingceResult.setF1Remark(getRemark(pcl90.getF1()));
        pingceResult.setF2Remark(getRemark(pcl90.getF2()));
        pingceResult.setF3Remark(getRemark(pcl90.getF3()));
        pingceResult.setF4Remark(getRemark(pcl90.getF4()));
        pingceResult.setF5Remark(getRemark(pcl90.getF5()));
        pingceResult.setF6Remark(getRemark(pcl90.getF6()));
        pingceResult.setF7Remark(getRemark(pcl90.getF7()));
        pingceResult.setF8Remark(getRemark(pcl90.getF8()));
        pingceResult.setF9Remark(getRemark(pcl90.getF9()));
        pingceResult.setF10Remark(getRemark(pcl90.getF10()));
        return pingceResult;
    }
}
