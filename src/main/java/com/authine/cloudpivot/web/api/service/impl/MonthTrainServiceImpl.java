package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.entity.MonthTrain;
import com.authine.cloudpivot.web.api.entity.MonthTrainPerson;
import com.authine.cloudpivot.web.api.mapper.MonthTrainMapper;
import com.authine.cloudpivot.web.api.service.MonthTrainService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

@Service
public class MonthTrainServiceImpl implements MonthTrainService {

    @Resource
    MonthTrainMapper monthTrainMapper;

    @Override
    public String upsetMonthTrainCen(String Id) {
        String resc="结果描述：";
        MonthTrain mt=monthTrainMapper.getMonthTrainCen(Id);
        String cenid="";
        String bigid="";
        if(mt !=null && mt.id !=null){
            //查询是否有中队统计，有更新，没有插入
            MonthTrain  mtcen=monthTrainMapper.getMonthTrainCenProNum(mt.getTrainDate(),mt.getDept());
            if(mtcen !=null && StringUtils.isNotBlank(mtcen.id)){
                //更新
                MonthTrain updateCen=new MonthTrain();
                //类复制
                BeanUtils.copyProperties(mt,updateCen);
                updateCen.setNumAll(mt.getNumAll()+mtcen.getNumAll());
                updateCen.setId(mtcen.getId());
                Integer rsc=monthTrainMapper.updateMonthTrainCenPro(updateCen);
                  resc=resc+"中队更新结果"+rsc +";";
                String up=upAvgByCen(updateCen.getId(),mt.getDept(),mt.getTrainDate());
                resc=resc+up ;
            }else{
                //插入中队
                MonthTrain insetCen=new MonthTrain();
                //类复制
                BeanUtils.copyProperties(mt,insetCen);
                //修改id
                insetCen.setId(UUID.randomUUID().toString().replace("-", ""));
                Integer rsc=monthTrainMapper.insertMonthTrainCenPro(insetCen);
                resc=resc+"中队插入结果"+rsc+";";
                String up=upAvgByCen(insetCen.getId(),mt.getDept(),mt.getTrainDate());
                resc=resc+up ;
            }

            //查询是否有大队统计，有更新，没有插入
//            JSONArray json = (JSONArray)JSON.parse(mt.getDept());
//            JSONObject deptJson = (JSONObject)json.get(0);
//            String deptId=deptJson.getString("id");
//            String parentDeptId=monthTrainMapper.getParentDeptId(deptId);//父部门（大队部门Id）
            String parentDeptId=mt.getBigDept();
            if(StringUtils.isNotBlank(parentDeptId)){
//                parentDeptId= "[{\"id\":\""+ parentDeptId  +"\",\"type\":1}]";

                MonthTrain  mtbig=monthTrainMapper.getMonthTrainBigProNum(mt.getTrainDate(),parentDeptId);
                if(mtbig!=null){

                    //更新大队统计
                    MonthTrain updatebig=new MonthTrain();
                    //类复制
                    BeanUtils.copyProperties(mt,updatebig);
                    updatebig.setId(mtbig.getId());
               //     updatebig.setNumAll(mtbig.getNumAll()+mt.getNumAll());//总人数
             //       mtbig.setNumCenPerson(mtbig.getNumCenPerson()==null?0:mtbig.getNumCenPerson());//去空
                //    updatebig.setNumCenPerson(mtbig.getNumCenPerson()+mt.getNumAll());//中队人数
                    Integer rsc=monthTrainMapper.updateMonthTrainBigPro(updatebig);
                    resc=resc+"大队更新结果"+rsc+";";
                    String up=upAvgByBig(updatebig.getId(),parentDeptId,mt.getTrainDate());
                    resc=resc+up ;
                }else{
                    //插入大队
                    MonthTrain insetbig=new MonthTrain();
                    //类复制
                    BeanUtils.copyProperties(mt,insetbig);
                    insetbig.setId(UUID.randomUUID().toString().replace("-", ""));
                    insetbig.setDept(parentDeptId);
                    Integer rsc=monthTrainMapper.insertMonthTrainBigPro(insetbig);
                    resc=resc+"大队插入结果"+rsc+";";
                    String up=upAvgByBig(insetbig.getId(),parentDeptId,mt.getTrainDate());
                    resc=resc+up ;
                }
            }else{
                resc=resc+"中队里不存在上级大队;";
            }

            //个人统计操作
            List<MonthTrainPerson> mtpb=monthTrainMapper.getMonthTrainPersonInfoByCen(mt.getId());
            if(mtpb!=null && mtpb.size()>0){
                for(MonthTrainPerson mp :mtpb){
                    MonthTrainPerson isp=monthTrainMapper.getMonthTrainPerson(mt.getTrainDate(),mp.getTrainNames());
                    if(isp ==null){
                        MonthTrainPerson insPerson=new MonthTrainPerson();
                        //类复制
                        BeanUtils.copyProperties(mp,insPerson);
                        insPerson.setId(UUID.randomUUID().toString().replace("-", ""));
                        insPerson.setName(mt.getName());
                        insPerson.setSequenceStatus(mt.getSequenceStatus());
                        insPerson.setCreater(mt.getCreater());
                        insPerson.setCreatedDeptId(mt.getCreatedDeptId());
                        insPerson.setOwner(mt.getOwner());
                        insPerson.setOwnerDeptId(mt.getOwnerDeptId());
                        insPerson.setOwnerDeptQueryCode(mt.getOwnerDeptQueryCode());
                        insPerson.setModifier(mt.getModifier());

                        insPerson.setDept(mt.getDept());
                        insPerson.setTrainDate(mt.getTrainDate());
                        //插入
                        Integer rsc=monthTrainMapper.insertMonthTrainPerson(insPerson);
                        resc=resc+"个人详情中队插入结果"+rsc+";";
                    }else{
                        MonthTrainPerson upPerson=new MonthTrainPerson();
                        //类复制
                        BeanUtils.copyProperties(mp,upPerson);
                        upPerson.setId(isp.getId());
                        //更新个人
                        Integer rsc=monthTrainMapper.updateMonthTrainPerson(upPerson);
                        resc=resc+"个人详情中队更新结果"+rsc+";";
                    }
                }
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

            //个人统计操作
            List<MonthTrainPerson> mtpb=monthTrainMapper.getMonthTrainPersonInfoByBig(mt.getId());
            if(mtpb!=null && mtpb.size()>0){
                for(MonthTrainPerson mp :mtpb){
                    MonthTrainPerson isp=monthTrainMapper.getMonthTrainPerson(mt.getTrainDate(),mp.getTrainNames());
                    if(isp ==null){
                        MonthTrainPerson insPerson=new MonthTrainPerson(mp);
                        insPerson.setId(UUID.randomUUID().toString().replace("-", ""));
                        insPerson.setName(mt.getName());
                        insPerson.setSequenceStatus(mt.getSequenceStatus());
                        insPerson.setCreater(mt.getCreater());
                        insPerson.setCreatedDeptId(mt.getCreatedDeptId());
                        insPerson.setOwner(mt.getOwner());
                        insPerson.setOwnerDeptId(mt.getOwnerDeptId());
                        insPerson.setOwnerDeptQueryCode(mt.getOwnerDeptQueryCode());
                        insPerson.setModifier(mt.getModifier());

                        insPerson.setDept(mt.getDept());
                        insPerson.setTrainDate(mt.getTrainDate());
                        //插入
                        Integer rsc=monthTrainMapper.insertMonthTrainPerson(insPerson);
                        resc=resc+"个人详情大队插入结果"+rsc+";";
                    }else{
                        MonthTrainPerson insPerson=new MonthTrainPerson(mp);
                        insPerson.setId(isp.getId());
                        //更新个人
                        Integer rsc=monthTrainMapper.updateMonthTrainPerson(insPerson);
                        resc=resc+"个人详情大队更新结果"+rsc+";";
                    }
                }
            }
        }
        return resc;
    }

    //更新中队平均分
    String upAvgByCen(String id, String dept, Date trainDate){
      List<MonthTrain> mtlist=monthTrainMapper.getMonthTrainCenListDT(trainDate,dept);
      String resc="";
      if(!CollectionUtils.isEmpty(mtlist)){
          int count=0;//总人数
          Float score=0F;//总分数
          for(MonthTrain idList :mtlist){
              //查询子表详细成绩
              List<MonthTrainPerson> mtpb=monthTrainMapper.getMonthTrainPersonInfoByCen(idList.getId());
              if(mtpb!=null && mtpb.size()>0){
                  for(MonthTrainPerson list :mtpb){
                      if(list.getScore1() !=null ){
                          count++;
                          score=score+list.getScore1();
                      }
                      if(list.getScore2() !=null ){
                          count++;
                          score=score+list.getScore2();
                      }
                      if(list.getScore3() !=null ){
                          count++;
                          score=score+list.getScore3();
                      }
                      if(list.getScore4() !=null ){
                          count++;
                          score=score+list.getScore4();
                      }
                      if(list.getScore5() !=null ){
                          count++;
                          score=score+list.getScore5();
                      }
                      if(list.getScore6() !=null ){
                          count++;
                          score=score+list.getScore6();
                      }
                      if(list.getScore7() !=null ){
                          count++;
                          score=score+list.getScore7();
                      }
                      if(list.getScore8() !=null ){
                          count++;
                          score=score+list.getScore8();
                      }
                      if(list.getScore9() !=null ){
                          count++;
                          score=score+list.getScore9();
                      }

                  }
              }
          }
          Float deptAvg=score/count;
          Map<String,Object> map=new HashMap<>();
          map.put("id",id);
          map.put("deptAvg",deptAvg);
          Integer rsc=monthTrainMapper.updateAvgByCen(map);
          resc=resc+"中队平均分统计更新结果"+rsc+";";
      }


        return resc;
    }

    //更新大队平均分
    String upAvgByBig(String id, String bigDept, Date trainDate){
        List<MonthTrain> mtlist=monthTrainMapper.getMonthTrainBigListByDT(trainDate,bigDept);
        String resc="";
        if(!CollectionUtils.isEmpty(mtlist)){
            int count=0;//总人数
            Float score=0F;//总分数
            for(MonthTrain idList :mtlist){
                //查询子表详细成绩
                List<MonthTrainPerson> mtpb=monthTrainMapper.getMonthTrainPersonInfoByCen(idList.getId());
                if(mtpb!=null && mtpb.size()>0){
                    for(MonthTrainPerson list :mtpb){
                        if(list.getScore1() !=null ){
                            count++;
                            score=score+list.getScore1();
                        }
                        if(list.getScore2() !=null ){
                            count++;
                            score=score+list.getScore2();
                        }
                        if(list.getScore3() !=null ){
                            count++;
                            score=score+list.getScore3();
                        }
                        if(list.getScore4() !=null ){
                            count++;
                            score=score+list.getScore4();
                        }
                        if(list.getScore5() !=null ){
                            count++;
                            score=score+list.getScore5();
                        }
                        if(list.getScore6() !=null ){
                            count++;
                            score=score+list.getScore6();
                        }
                        if(list.getScore7() !=null ){
                            count++;
                            score=score+list.getScore7();
                        }
                        if(list.getScore8() !=null ){
                            count++;
                            score=score+list.getScore8();
                        }
                        if(list.getScore9() !=null ){
                            count++;
                            score=score+list.getScore9();
                        }

                    }
                }
            }
            Float deptAvg=score/count;
            Map<String,Object> map=new HashMap<>();
            map.put("id",id);
            map.put("deptAvg",deptAvg);
            Integer rsc=monthTrainMapper.updateAvgByBig(map);
            resc=resc+"大队平均分统计更新结果"+rsc+";";
        }


        return resc;
    }
}
