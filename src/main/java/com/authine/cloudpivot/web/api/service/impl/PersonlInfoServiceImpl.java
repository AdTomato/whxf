package com.authine.cloudpivot.web.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.authine.cloudpivot.web.api.entity.BaseEntity;
import com.authine.cloudpivot.web.api.entity.Notice;
import com.authine.cloudpivot.web.api.entity.PersonlInfo;
import com.authine.cloudpivot.web.api.mapper.DeptMapper;
import com.authine.cloudpivot.web.api.service.PersonlInfoService;
import com.authine.cloudpivot.web.api.utils.DateUtil;
import com.authine.cloudpivot.web.api.utils.DingDingUtil;
import com.authine.cloudpivot.web.api.utils.RedisUtils;
import com.dingtalk.api.response.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author: weiyao
 * @time: 2020/5/11
 * @Description: 武汉消防 大屏功能 人员动态模块信息
 */
@Service
public class PersonlInfoServiceImpl implements PersonlInfoService {

    @Resource
    DeptMapper deptMapper;

    @Autowired
    RedisUtils redisUtils;


    @Override
    public PersonlInfo getPersonlInfo(String deptId) {
        PersonlInfo person = new PersonlInfo();
        List<String> names=new ArrayList<>();
//        String ddDeptId = deptMapper.getddDeptId(deptId);
        String ddDeptId = deptId;
        //获取token
        String token = DingDingUtil.getToken();
        //设置生日

        if (StringUtils.isNotBlank(ddDeptId)) {
            //获取部门用户详情
            OapiUserListbypageResponse deptUserList = DingDingUtil.getDeptUserByDeptId(ddDeptId, token);
            if (deptUserList.getUserlist() != null && deptUserList.getUserlist().size() > 0) {
                person.setNumtype1(deptUserList.getUserlist().size());//指挥员人数
                //    List<String> userNames1=new ArrayList<>();
                for (OapiUserListbypageResponse.Userlist user : deptUserList.getUserlist()) {
                    //    person.getUserNames1().add(user.getName());//添加指挥员人员姓名
                    //    userNames1.add(user.getName());//添加指挥员人员姓名
                    person = getPersonState(person, user.getUserid(), token,1,user.getName(),names);//设置在岗，出差，请假人数
                    person = getBirthdayPerson(person, user.getUserid(), user.getName(), token);//设置生日人员
                    //        person = setPersonNotice(person, user, token);//获取公告
                }
                //   person.setUserNames1(userNames1);
            }

            //      获取子部门
            OapiDepartmentListResponse depList = DingDingUtil.getDeptList(ddDeptId, token);
            if (depList.getDepartment() != null && depList.getDepartment().size() > 0) {
                for (OapiDepartmentListResponse.Department list : depList.getDepartment()) {
                    //获取部门用户详情
                    OapiUserListbypageResponse tq1 = DingDingUtil.getDeptUserByDeptId(list.getId() + "", token);
                    //   List<String> userNames1=new ArrayList<>();
                    if (list.getName().indexOf("特勤一班") != -1) {
                        if (tq1.getUserlist() != null && tq1.getUserlist().size() > 0) {
                            person.setNumtype2(tq1.getUserlist().size() + person.getNumtype2());//消防员人数
                            //    List<String> userNames1=new ArrayList<>();
                            for (OapiUserListbypageResponse.Userlist user : tq1.getUserlist()) {
                                //     person.getUserNames2().add(user.getName());//添加指挥员人员姓名
                                //     userNames1.add(user.getName());//添加指挥员人员姓名
                                person = getPersonState(person, user.getUserid(), token,2,user.getName(),names);//设置在岗，出差，请假人数
                                person = getBirthdayPerson(person, user.getUserid(), user.getName(), token);//设置生日人员
                                //                person = setPersonNotice(person, user, token);//获取公告
                            }
                            //    person.setUserNames2(userNames1);
                        }

                    } else if (list.getName().indexOf("特勤二班") != -1) {
                        if (tq1.getUserlist() != null && tq1.getUserlist().size() > 0) {
                            person.setNumtype2(tq1.getUserlist().size() + person.getNumtype2());//消防员人数
                            //   List<String> userNames1=new ArrayList<>();
                            for (OapiUserListbypageResponse.Userlist user : tq1.getUserlist()) {
                                //   person.getUserNames3().add(user.getName());//添加指挥员人员姓名
                                //   userNames1.add(user.getName());//添加指挥员人员姓名
                                person = getPersonState(person, user.getUserid(), token,3,user.getName(),names);//设置在岗，出差，请假人数
                                person = getBirthdayPerson(person, user.getUserid(), user.getName(), token);//设置生日人员
                                //              person = setPersonNotice(person, user, token);//获取公告
                            }
                            //     person.setUserNames3(userNames1);
                        }

                    } else if (list.getName().indexOf("灭火一班") != -1) {
                        if (tq1.getUserlist() != null && tq1.getUserlist().size() > 0) {
                            person.setNumtype2(tq1.getUserlist().size() + person.getNumtype2());//消防员人数
                            //   List<String> userNames1=new ArrayList<>();
                            for (OapiUserListbypageResponse.Userlist user : tq1.getUserlist()) {
                                //   person.getUserNames4().add(user.getName());//添加指挥员人员姓名
                                //      userNames1.add(user.getName());//添加指挥员人员姓名
                                person = getPersonState(person, user.getUserid(), token,4,user.getName(),names);//设置在岗，出差，请假人数
                                person = getBirthdayPerson(person, user.getUserid(), user.getName(), token);//设置生日人员
                                //             person = setPersonNotice(person, user, token);//获取公告
                            }
                            //       person.setUserNames4(userNames1);
                        }

                    } else if (list.getName().indexOf("灭火二班") != -1) {
                        if (tq1.getUserlist() != null && tq1.getUserlist().size() > 0) {
                            person.setNumtype2(tq1.getUserlist().size() + person.getNumtype2());//消防员人数
                            //   List<String> userNames1=new ArrayList<>();
                            for (OapiUserListbypageResponse.Userlist user : tq1.getUserlist()) {
                                //   person.getUserNames5().add(user.getName());//添加指挥员人员姓名
                                //       userNames1.add(user.getName());//添加指挥员人员姓名
                                person = getPersonState(person, user.getUserid(), token,5,user.getName(),names);//设置在岗，出差，请假人数
                                person = getBirthdayPerson(person, user.getUserid(), user.getName(), token);//设置生日人员
                                //         person = setPersonNotice(person, user, token);//获取公告
                            }
                            //    person.setUserNames5(userNames1);
                        }

                    } else if (list.getName().indexOf("通信保障班") != -1) {
                        if (tq1.getUserlist() != null && tq1.getUserlist().size() > 0) {
                            person.setNumtype2(tq1.getUserlist().size() + person.getNumtype2());//消防员人数
                            //   List<String> userNames1=new ArrayList<>();
                            for (OapiUserListbypageResponse.Userlist user : tq1.getUserlist()) {
                                //    person.getUserNames6().add(user.getName());//添加指挥员人员姓名
                                //     userNames1.add(user.getName());//添加指挥员人员姓名
                                person = getPersonState(person, user.getUserid(), token,6,user.getName(),names);//设置在岗，出差，请假人数
                                person = getBirthdayPerson(person, user.getUserid(), user.getName(), token);//设置生日人员
                                //     person = setPersonNotice(person, user, token);//获取公告
                            }
                            //      person.setUserNames6(userNames1);
                        }

                    }
                }

            }
            //设置总人数
            person.setNumAll(person.getNumtype1() + person.getNumtype2());
        }
        //去除一个人员在多个部门重复人员
        person= reusePerson(names,person);
        return person;
    }

    /*
    获取大队人员信息
     */
    @Override
    public PersonlInfo getTeamPersonlInfo(String sourceId) {
        List<String> names= new ArrayList();
        PersonlInfo person = new PersonlInfo();
        //获取token
        String token = DingDingUtil.getToken();
        if (StringUtils.isNotBlank(sourceId)) {
            //获取部门用户详情
            OapiUserListbypageResponse deptUserList = DingDingUtil.getDeptUserByDeptId(sourceId, token);
            if (deptUserList.getUserlist() != null && deptUserList.getUserlist().size() > 0) {
                person.setNumAll(deptUserList.getUserlist().size());//总人数
                for (OapiUserListbypageResponse.Userlist user : deptUserList.getUserlist()) {

                    if(StringUtils.isNotBlank(user.getPosition())){
                        if(user.getPosition().equals("大队长") ||user.getPosition().equals("政治教导员")){
                            //设置大队主官 人员
                            //  person.getUserNames1().add(user.getName());
                            person = getPersonState(person, user.getUserid(), token,1,user.getName(),names);//设置在岗，出差，请假人数
                            person.setNumtype1(person.getNumtype1()+1);//添加干部人数
                        }else if(user.getPosition().indexOf("干部")!= -1 ||user.getPosition().equals("副大队长")){
                            //设置大队干部 人员
                            //     person.getUserNames2().add(user.getName());
                            person = getPersonState(person, user.getUserid(), token,2,user.getName(),names);//设置在岗，出差，请假人数
                            person.setNumtype1(person.getNumtype1()+1);//添加干部人数
                        }else{
                            //设置大队文员 人员
                            //    person.getUserNames3().add(user.getName());
                            person = getPersonState(person, user.getUserid(), token,3,user.getName(),names);//设置在岗，出差，请假人数
                            person.setNumtype2(person.getNumtype2()+1);//添加文员人数
                        }
                    }else{
                        //没有职位也算文员
                        //   person.getUserNames3().add(user.getName());
                        person = getPersonState(person, user.getUserid(), token,3,user.getName(),names);//设置在岗，出差，请假人数
                        person.setNumtype2(person.getNumtype2()+1);//添加文员人数
                    }
                    //    userNames1.add(user.getName());//添加指挥员人员姓名
                    //     person = getPersonState(person, user.getUserid(), token);//设置在岗，出差，请假人数
                    person = getBirthdayPerson(person, user.getUserid(), user.getName(), token);//设置生日人员
                    //      person = setPersonNotice(person, user, token);//获取公告
                }
                //   person.setUserNames1(userNames1);
            }
        }
        //    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>names大小： "+names.size());
        return person;
    }


    /*
    weiyao
    根据id查询员工上班状态（休假，出差）
    types: //详细人员集合1-6
     */
    PersonlInfo getPersonState(PersonlInfo person, String personId, String token,Integer types,String name,List<String> names) {

        int color=1;
//        if("吕磊".equals(name)){
//            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>： ");
//        }
        //查询排版信息（不排班）
        //    OapiAttendanceScheduleListbydayResponse rsp = DingDingUtil.listbyday(personId, personId, new Date(), token);
        //查询请假信息
        OapiAttendanceGetleavestatusResponse qingjia=DingDingUtil.getleavestatus(personId,token);
        if(qingjia.getResult().getLeaveStatus()!=null && qingjia.getResult().getLeaveStatus().size()>0){
            //当天存在请假
            color=3;
        }else{
            //出差
            //获取商旅出差审批列表（之前七天发起的审批）==改为公差勤务
            OapiProcessinstanceListidsResponse  slChuChai= DingDingUtil.getApprovalIds(personId,token);
            if( slChuChai.getResult() !=null && slChuChai.getResult().getList().size()>0){
                List<String> ids=slChuChai.getResult().getList();
                for(String id:ids){
                    //查询商旅出差数据
                    OapiProcessinstanceGetResponse  detail= DingDingUtil.getApprovalDetail(id,token);
                    if(detail.getProcessInstance().getResult()!=null && "agree".equals(detail.getProcessInstance().getResult())){
                        List<OapiProcessinstanceGetResponse.FormComponentValueVo> list=detail.getProcessInstance().getFormComponentValues();
                        for(OapiProcessinstanceGetResponse.FormComponentValueVo vo :list){
//===================================2020-06-01(商旅出差修改为勤务出差)开始====================
                            if("DDDateRangeField".equals(vo.getComponentType())){
                                JSONArray json = (JSONArray)JSON.parse(vo.getValue());//开始时间，结束时间，总时长
                                if(json.size()>0){
                                    String strttime=(String)json.get(0);
                                    String endTime=(String)json.get(1);
                                    if(strttime.length()>1 && endTime.length()>1){
                                        if(isEffectiveDate(strttime,endTime)){
                                            //出差时间包含当天，状态是出差
                                            color=2;
                                        }
                                    }
                                }
                            }
//===================================2020-06-01(商旅出差修改为勤务出差)结束====================
/* =============2020-06-01原来的商旅出差代码注销=============
             JSONArray json = (JSONArray)JSON.parse(vo.getValue());
                            for (Object obj : json) {
                                JSONObject j = (JSONObject)obj;
                                if("TableField".equals(j.get("componentName"))){
                                    String val= (String)j.get("value");
                                    //     val=val.replaceAll("\", "");//去掉斜巷
                                    JSONArray json2 = (JSONArray)JSON.parse(val);
                                    JSONObject json2Data = (JSONObject) json2.get(0);
                                    JSONArray rowValues = (JSONArray) json2Data.get("rowValue");
                                    for(Object rowValue : rowValues){
                                        String strttime="";
                                        String endTime="";
                                        JSONObject j2 = (JSONObject)rowValue;
                                        if("startTime".equals(j2.get("bizAlias"))){
                                            strttime=(String)j2.get("value");
                                        }else if("endTime".equals(j2.get("bizAlias"))){
                                            endTime=(String)j2.get("value");
                                        }
                                        if(strttime.length()>1 && endTime.length()>1){
                                            if(isEffectiveDate(strttime,endTime)){
                                                //出差时间包含当天，状态是出差
                                                color=2;
                                            }
                                        }
                                    }
                                }

                            }*/
                        }

                    }
                }
            }
        }

        if(color==2){//公差
            person.setNumGongchai(person.getNumGongchai() + 1);
            //设置人员和颜色
            person=getNameAndColor(person,types,2,name);
        }else if(color==3){//请假
            person.setNumXiujia(person.getNumXiujia() + 1);
            //设置人员和颜色
            person=getNameAndColor(person,types,3,name);
        }else{//在岗
            person.setNumZaigang(person.getNumZaigang() + 1);
            //设置人员和颜色
            person=getNameAndColor(person,types,1,name);
        }
        /*
        if (rsp.getResult() != null && rsp.getResult().size() > 0) {
            //check_type  ==> OnDuty：上班  OffDuty：下班
            //     String check_type=rsp.getResult().get(0).getCheckType();
            //1：加班，2：出差，3：请假
            Long type = rsp.getResult().get(0).getApproveBizType();
            //    String check_type1=rsp.getResult().get(1).getCheckType();
            //1：加班，2：出差，3：请假
            //    Long type2=rsp.getResult().get(1).getApproveBizType();
            if (type != null && type == 2) {
                person.setNumGongchai(person.getNumGongchai() + 1);
                //设置人员和颜色
                person=getNameAndColor(person,types,2,name);
            } else if (type != null && type == 3) {
                person.setNumXiujia(person.getNumXiujia() + 1);
                //设置人员和颜色
                person=getNameAndColor(person,types,3,name);
            } else {
                person.setNumZaigang(person.getNumZaigang() + 1);
                //设置人员和颜色
                person=getNameAndColor(person,types,1,name);
            }
        } else {
            person.setNumZaigang(person.getNumZaigang() + 1);
            //设置人员和颜色
            person=getNameAndColor(person,types,1,name);
        }*/
        names.add(name+"-"+color);
        return person;
    }

    /*
    weiyao
    查询是否今天生日
      */
    PersonlInfo getBirthdayPerson(PersonlInfo person, String userid, String name, String token) {
        String birthday = "";
        String doday = DateUtil.getDate("MM-dd");
        if (redisUtils.hasKey(userid + "-birthday")) {
            birthday = redisUtils.get(userid + "-birthday") + "";
        } else {
            OapiSmartworkHrmEmployeeListResponse us = DingDingUtil.getBirthDayforEmployee(userid, token);
            List<OapiSmartworkHrmEmployeeListResponse.EmpFieldInfoVO> result = us.getResult();
            if (result.get(0).getFieldList().size() > 0) {
                birthday = result.get(0).getFieldList().get(0).getValue();
                redisUtils.set(userid  + "-birthday", birthday, 12 * 60 * 60);
            }
        }
        if (StringUtils.isNotBlank(birthday) && birthday.length() > 5) {
            //今天生日
            if (birthday.substring(5).equals(doday)) {
                person.getBirthdayNames().add(name);
            }
        }
        return person;
    }

    /*
   weiyao
   设置公告信息
    */
    PersonlInfo setPersonNotice2(PersonlInfo person, OapiUserListbypageResponse.Userlist user, String token) {
        if(!user.getIsAdmin()) {
            return person;
        }
        List<OapiBlackboardListtoptenResponse.OapiBlackboardVo> rsp = DingDingUtil.listtopten(user.getUserid(), token);
        if (CollectionUtils.isNotEmpty(rsp)) {
            //获取当前月时间范围
            Date[] currentmonth = DateUtil.getMonth();
//                long startadte=currentmonth[0].getTime();
//                long endadte=currentmonth[1].getTime();
//                long now=System.currentTimeMillis();
            rsp.forEach(x -> {
                if (currentmonth[0].getTime() < x.getGmtCreate().getTime()) {
                    Notice notice = new Notice();
                    notice.setId(user.getUserid());
                    notice.setTitle(x.getTitle());
                    notice.setGmt_create(x.getGmtCreate());
                    notice.setUrl(x.getUrl());
                    notice.setGtmName(user.getName());
                    person.getNotice().add(notice);
                    //      noticeMapper.insert(notice);
                }


            });
        }
        return person;
    }

    /*
  设置姓名和颜色
  color:颜色 1，白色，2出差，3，休假
     */
    PersonlInfo getNameAndColor(PersonlInfo person, int type, int color,String name) {
        BaseEntity map=new BaseEntity();
        if(color==2){
            map.setSequenceNo(name);
            map.setSequenceStatus("2");
            switch (type){
                case 1:
                    person.getUserNames1().add(map);
                    break;
                case 2:
                    person.getUserNames2().add(map);
                    break;
                case 3:
                    person.getUserNames3().add(map);
                    break;
                case 4:
                    person.getUserNames4().add(map);
                    break;
                case 5:
                    person.getUserNames5().add(map);
                    break;
                case 6:
                    person.getUserNames6().add(map);
                    break;
            }
        }else if(color==3){
            map.setSequenceNo(name);
            map.setSequenceStatus("3");
            switch (type){
                case 1:
                    person.getUserNames1().add(map);
                    break;
                case 2:
                    person.getUserNames2().add(map);
                    break;
                case 3:
                    person.getUserNames3().add(map);
                    break;
                case 4:
                    person.getUserNames4().add(map);
                    break;
                case 5:
                    person.getUserNames5().add(map);
                    break;
                case 6:
                    person.getUserNames6().add(map);
                    break;
            }
        }else{
            map.setSequenceNo(name);
            map.setSequenceStatus("1");
            switch (type){
                case 1:
                    person.getUserNames1().add(map);
                    break;
                case 2:
                    person.getUserNames2().add(map);
                    break;
                case 3:
                    person.getUserNames3().add(map);
                    break;
                case 4:
                    person.getUserNames4().add(map);
                    break;
                case 5:
                    person.getUserNames5().add(map);
                    break;
                case 6:
                    person.getUserNames6().add(map);
                    break;
            }
        }

        return person;
    }

    /*
    weiyao
    判断开始时间，结束时间是否包含今天"yyyy-MM-dd"格式
     */
    public boolean isEffectiveDate( String  startTime, String endTime)  {
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Long now = sdf2.parse(sdf2.format(new Date())).getTime();
            Long start = sdf2.parse(startTime).getTime();
            Long end = sdf2.parse(endTime).getTime();
            if (now >= start && now<=end){
                return true;
            }
        }catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    /*
    查询重复的人员数量
     */
    public int[] reusePersonNumer(List<String> names, List<BaseEntity> userNames){
        int re=0;
        int[] res=new int[3];
        res[0]=0;
        res[1]=0;
        res[2]=0;

        if(names.size()>0 && userNames.size()>0){
            for(int i=0;i<names.size();i++){
                for(int j=0;j<userNames.size();j++){
                    if(names.get(i).equals(userNames.get(j).getSequenceNo())){
                        //123==》在岗，公差，休假
                        if("2".equals(userNames.get(j).getSequenceStatus())){
                            //出差
                            res[1]=res[1]+1;
                        }else if("3".equals(userNames.get(j).getSequenceStatus())){
                            //请假
                            res[2]=res[2]+1;
                        }else{
                            //在岗
                            res[0]=res[0]+1;
                        }
                    }
                }
            }
        }

        return res;
    }

    /*
       去除重复人员
        */
    public PersonlInfo reusePerson(List<String> names, PersonlInfo personlInfo){
        Set<String> set = new HashSet<>(names);

        //获得list与set的差集
        Collection rs = CollectionUtils.disjunction(names,set);
        //将collection转换为list
        //重复人员的姓名集合
        List<String> rename = new ArrayList<>(rs);
        int zaig=0;
        int chuc=0;
        int qinjia=0;
        List<String> listname=new ArrayList();//去掉后缀颜色
        for(String st:rename){
            String[] ss=st.split("-");
            //颜色
            if(ss[1].equals("1")){
                zaig++;
            }else if(ss[1].equals("2")){
                chuc++;
            }else{
                qinjia++;
            }
            listname.add(ss[0]);
        }
        //   System.out.println(zaig+" ZCQ============"+chuc+" qingjia: "+qinjia);
        int[] re1=reusePersonNumer(listname,personlInfo.getUserNames1());
        int[] re2=reusePersonNumer(listname,personlInfo.getUserNames2());
        int[] re3=reusePersonNumer(listname,personlInfo.getUserNames3());
        int[] re4=reusePersonNumer(listname,personlInfo.getUserNames4());
        int[] re5=reusePersonNumer(listname,personlInfo.getUserNames5());
        int[] re6=reusePersonNumer(listname,personlInfo.getUserNames6());

        int numZaigang=re1[0]+re2[0]+re3[0]+re4[0]+re5[0]+re6[0];
        int numChuchai=re1[1]+re2[1]+re3[1]+re4[1]+re5[1]+re6[1];
        int numQingjia=re1[2]+re2[2]+re3[2]+re4[2]+re5[2]+re6[2];

        if(numZaigang>0){
            personlInfo.setNumZaigang(personlInfo.getNumZaigang()-numZaigang+zaig);
        }
        if(numChuchai>0){
            personlInfo.setNumGongchai(personlInfo.getNumGongchai()-numChuchai+chuc);
        }
        if(numQingjia>0){
            personlInfo.setNumXiujia(personlInfo.getNumXiujia()-numQingjia+qinjia);
        }

        //设置总人数
        personlInfo.setNumAll(personlInfo.getNumGongchai()+personlInfo.getNumXiujia()+personlInfo.getNumZaigang());
        personlInfo.setNumtype2(personlInfo.getNumAll()-personlInfo.getNumtype1());
        return personlInfo;
    }


}
