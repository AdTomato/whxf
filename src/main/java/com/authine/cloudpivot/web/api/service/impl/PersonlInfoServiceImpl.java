package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.entity.Notice;
import com.authine.cloudpivot.web.api.entity.PersonlInfo;
import com.authine.cloudpivot.web.api.mapper.DeptMapper;
import com.authine.cloudpivot.web.api.mapper.TwoRandownMapper;
import com.authine.cloudpivot.web.api.service.PersonlInfoService;
import com.authine.cloudpivot.web.api.utils.DateUtil;
import com.authine.cloudpivot.web.api.utils.DingDingUtil;
import com.authine.cloudpivot.web.api.utils.RedisUtils;
import com.dingtalk.api.response.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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
                    person.getUserNames1().add(user.getName());//添加指挥员人员姓名
                    //    userNames1.add(user.getName());//添加指挥员人员姓名
                    person = getPersonState(person, user.getUserid(), token);//设置在岗，出差，请假人数
                    person = getBirthdayPerson(person, user.getUserid(), user.getName(), token);//设置生日人员
                    person = setPersonNotice(person, user, token);//获取公告
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
                                person.getUserNames2().add(user.getName());//添加指挥员人员姓名
                                //     userNames1.add(user.getName());//添加指挥员人员姓名
                                person = getPersonState(person, user.getUserid(), token);//设置在岗，出差，请假人数
                                person = getBirthdayPerson(person, user.getUserid(), user.getName(), token);//设置生日人员
                                person = setPersonNotice(person, user, token);//获取公告
                            }
                            //    person.setUserNames2(userNames1);
                        }

                    } else if (list.getName().indexOf("特勤二班") != -1) {
                        if (tq1.getUserlist() != null && tq1.getUserlist().size() > 0) {
                            person.setNumtype2(tq1.getUserlist().size() + person.getNumtype2());//消防员人数
                            //   List<String> userNames1=new ArrayList<>();
                            for (OapiUserListbypageResponse.Userlist user : tq1.getUserlist()) {
                                person.getUserNames3().add(user.getName());//添加指挥员人员姓名
                                //   userNames1.add(user.getName());//添加指挥员人员姓名
                                person = getPersonState(person, user.getUserid(), token);//设置在岗，出差，请假人数
                                person = getBirthdayPerson(person, user.getUserid(), user.getName(), token);//设置生日人员
                                person = setPersonNotice(person, user, token);//获取公告
                            }
                            //     person.setUserNames3(userNames1);
                        }

                    } else if (list.getName().indexOf("灭火一班") != -1) {
                        if (tq1.getUserlist() != null && tq1.getUserlist().size() > 0) {
                            person.setNumtype2(tq1.getUserlist().size() + person.getNumtype2());//消防员人数
                            //   List<String> userNames1=new ArrayList<>();
                            for (OapiUserListbypageResponse.Userlist user : tq1.getUserlist()) {
                                person.getUserNames4().add(user.getName());//添加指挥员人员姓名
                                //      userNames1.add(user.getName());//添加指挥员人员姓名
                                person = getPersonState(person, user.getUserid(), token);//设置在岗，出差，请假人数
                                person = getBirthdayPerson(person, user.getUserid(), user.getName(), token);//设置生日人员
                                person = setPersonNotice(person, user, token);//获取公告
                            }
                            //       person.setUserNames4(userNames1);
                        }

                    } else if (list.getName().indexOf("灭火二班") != -1) {
                        if (tq1.getUserlist() != null && tq1.getUserlist().size() > 0) {
                            person.setNumtype2(tq1.getUserlist().size() + person.getNumtype2());//消防员人数
                            //   List<String> userNames1=new ArrayList<>();
                            for (OapiUserListbypageResponse.Userlist user : tq1.getUserlist()) {
                                person.getUserNames5().add(user.getName());//添加指挥员人员姓名
                                //       userNames1.add(user.getName());//添加指挥员人员姓名
                                person = getPersonState(person, user.getUserid(), token);//设置在岗，出差，请假人数
                                person = getBirthdayPerson(person, user.getUserid(), user.getName(), token);//设置生日人员
                                person = setPersonNotice(person, user, token);//获取公告
                            }
                            //    person.setUserNames5(userNames1);
                        }

                    } else if (list.getName().indexOf("通信保障班") != -1) {
                        if (tq1.getUserlist() != null && tq1.getUserlist().size() > 0) {
                            person.setNumtype2(tq1.getUserlist().size() + person.getNumtype2());//消防员人数
                            //   List<String> userNames1=new ArrayList<>();
                            for (OapiUserListbypageResponse.Userlist user : tq1.getUserlist()) {
                                person.getUserNames6().add(user.getName());//添加指挥员人员姓名
                                //     userNames1.add(user.getName());//添加指挥员人员姓名
                                person = getPersonState(person, user.getUserid(), token);//设置在岗，出差，请假人数
                                person = getBirthdayPerson(person, user.getUserid(), user.getName(), token);//设置生日人员
                                person = setPersonNotice(person, user, token);//获取公告
                            }
                            //      person.setUserNames6(userNames1);
                        }

                    }
                }

            }
            //设置总人数
            person.setNumAll(person.getNumtype1() + person.getNumtype2());
        }

        return person;
    }

    /*
    获取大队人员信息
     */
    @Override
    public PersonlInfo getTeamPersonlInfo(String sourceId) {
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
                            person.getUserNames1().add(user.getName());
                            person.setNumtype1(person.getNumtype1()+1);//添加干部人数
                        }else if(user.getPosition().indexOf("干部")!= -1 ||user.getPosition().equals("副大队长")){
                            //设置大队干部 人员
                            person.getUserNames2().add(user.getName());
                            person.setNumtype1(person.getNumtype1()+1);//添加干部人数
                        }else{
                            //设置大队文员 人员
                            person.getUserNames3().add(user.getName());
                            person.setNumtype2(person.getNumtype2()+1);//添加文员人数
                        }
                    }else{
                        //没有职位也算文员
                        person.getUserNames3().add(user.getName());
                        person.setNumtype2(person.getNumtype2()+1);//添加文员人数
                    }
                    //    userNames1.add(user.getName());//添加指挥员人员姓名
                    person = getPersonState(person, user.getUserid(), token);//设置在岗，出差，请假人数
                    person = getBirthdayPerson(person, user.getUserid(), user.getName(), token);//设置生日人员
                    person = setPersonNotice(person, user, token);//获取公告
                }
                //   person.setUserNames1(userNames1);
            }
        }

        return person;
    }


    /*
    weiyao
    根据id查询员工上班状态（休假，出差）
     */
    PersonlInfo getPersonState(PersonlInfo person, String personId, String token) {
        OapiAttendanceScheduleListbydayResponse rsp = DingDingUtil.listbyday(personId, personId, new Date(), token);
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
            } else if (type != null && type == 3) {
                person.setNumXiujia(person.getNumXiujia() + 1);
            } else {
                person.setNumZaigang(person.getNumZaigang() + 1);
            }
        } else {
            person.setNumZaigang(person.getNumZaigang() + 1);
        }
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
    PersonlInfo setPersonNotice(PersonlInfo person, OapiUserListbypageResponse.Userlist user, String token) {
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
}
