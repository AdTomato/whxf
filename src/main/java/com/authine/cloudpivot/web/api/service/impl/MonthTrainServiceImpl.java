package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.entity.MonthTrain;
import com.authine.cloudpivot.web.api.mapper.MonthTrainMapper;
import com.authine.cloudpivot.web.api.service.MonthTrainService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

@Service
public class MonthTrainServiceImpl implements MonthTrainService {

    @Resource
    MonthTrainMapper monthTrainMapper;

    @Override
    public String upsetMonthTrainCen(String Id) {
        String resc="结果描述：";
        MonthTrain mt=monthTrainMapper.getMonthTrainCen(Id);
        if(mt !=null && mt.id !=null){
            //查询是否有中队统计，有更新，没有插入
            MonthTrain  mtcen=monthTrainMapper.getMonthTrainCenProNum(mt.getTrainDate(),mt.getDept());
            if(mtcen !=null && StringUtils.isNotBlank(mtcen.id)){
                //更新
                MonthTrain updateCen=mt;
                updateCen.setNumAll(mt.getNumAll()+mtcen.getNumAll());
                updateCen.setId(mtcen.getId());
                Integer rsc=monthTrainMapper.updateMonthTrainCenPro(updateCen);
                  resc=resc+"中队更新结果"+rsc +";";
            }else{
                //插入中队
                MonthTrain insetCen=mt;
                //修改id
                insetCen.setId(UUID.randomUUID().toString().replace("-", ""));
                Integer rsc=monthTrainMapper.insertMonthTrainCenPro(insetCen);
                resc=resc+"中队插入结果"+rsc+";";
            }

            //查询是否有大队统计，有更新，没有插入
            JSONArray json = (JSONArray)JSON.parse(mt.getDept());
            JSONObject deptJson = (JSONObject)json.get(0);
            String deptId=deptJson.getString("id");
            String parentDeptId=monthTrainMapper.getParentDeptId(deptId);//父部门（大队部门Id）
            if(StringUtils.isNotBlank(parentDeptId)){
                parentDeptId= "[{\"id\":\""+ parentDeptId  +"\",\"type\":1}]";
                MonthTrain  mtbig=monthTrainMapper.getMonthTrainBigProNum(mt.getTrainDate(),parentDeptId);
                if(mtbig!=null){
                    //更新大队统计
                    MonthTrain updatebig=mt;
                    updatebig.setId(mtbig.getId());
               //     updatebig.setNumAll(mtbig.getNumAll()+mt.getNumAll());//总人数
             //       mtbig.setNumCenPerson(mtbig.getNumCenPerson()==null?0:mtbig.getNumCenPerson());//去空
                //    updatebig.setNumCenPerson(mtbig.getNumCenPerson()+mt.getNumAll());//中队人数
                    Integer rsc=monthTrainMapper.updateMonthTrainBigPro(updatebig);
                    resc=resc+"大队更新结果"+rsc+";";
                }else{
                    //插入大队
                    MonthTrain insetbig=mt;
                    insetbig.setId(UUID.randomUUID().toString().replace("-", ""));
                    insetbig.setDept(parentDeptId);
               //     insetbig.setNumCenPerson(mt.getNumAll());
                    Integer rsc=monthTrainMapper.insertMonthTrainBigPro(insetbig);
                    resc=resc+"大队插入结果"+rsc+";";
                }
            }else{
                resc=resc+"中队里不存在上级大队;";
            }

        }
        return resc;
    }

    @Override
    public String upsetMonthTrainBig(String Id) {
        String resc="结果描述：";
        MonthTrain mt=monthTrainMapper.getMonthTrainBig(Id);
        if(mt !=null){
            //查询是否有大队统计，有更新，没有插入
            MonthTrain  mtbig=monthTrainMapper.getMonthTrainBigProNum(mt.getTrainDate(),mt.getDept());
            if(mtbig!=null){
                //更新大队统计
                MonthTrain upmt=new MonthTrain(mt);
                upmt.setId(mtbig.getId());
             //   upmt.setNumAll(mt.getNumAll()+mtbig.getNumAll());//总人数
             //   upmt.setNumBigPerson(mt.getNumAll()+mtbig.getNumBigPerson());//大队人数
                Integer rsc=monthTrainMapper.updateMonthTrainBigPro(upmt);
                resc=resc+"大队更新结果"+rsc+";";
            }else{
                //插入大队统计
                MonthTrain insertmt=new MonthTrain(mt);
                insertmt.setId(UUID.randomUUID().toString().replace("-", ""));
                insertmt.setName(mt.getName());
                insertmt.setSequenceStatus(mt.getSequenceStatus());
                insertmt.setCreater(mt.getCreater());
                //insertmt.setCreatedTime(nowDate);
                insertmt.setCreatedDeptId(mt.getCreatedDeptId());
                insertmt.setOwner(mt.getOwner());
                insertmt.setOwnerDeptId(mt.getOwnerDeptId());
                insertmt.setOwnerDeptQueryCode(mt.getOwnerDeptQueryCode());
                insertmt.setModifier(mt.getModifier());
           //     insertmt.setNumAll(mt.getNumAll());
           //     insertmt.setNumBigPerson(mt.getNumAll());
                insertmt.setDept(mt.getDept());
                insertmt.setTrainDate(mt.getTrainDate());
                Integer rsc=monthTrainMapper.insertMonthTrainBigPro(insertmt);
                resc=resc+"大队插入结果"+rsc+";";
            }
        }
        return resc;
    }
}
