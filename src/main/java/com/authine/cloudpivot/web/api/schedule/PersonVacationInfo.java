package com.authine.cloudpivot.web.api.schedule;

import com.authine.cloudpivot.engine.api.facade.OrganizationFacade;
import com.authine.cloudpivot.engine.api.model.organization.DepartmentModel;
import com.authine.cloudpivot.engine.api.model.organization.UserModel;
import com.authine.cloudpivot.web.api.dubbo.DubboConfigService;
import com.authine.cloudpivot.web.api.entity.RoleVacationInfo;
import com.authine.cloudpivot.web.api.mapper.RoleVacationInfoMapper;
import com.authine.cloudpivot.web.api.utils.Constant;
import com.authine.cloudpivot.web.api.utils.DateUtil;
import com.authine.cloudpivot.web.api.utils.DingDingUtil;
import com.dingtalk.api.response.OapiAttendanceGetleavestatusResponse;
import com.dingtalk.api.response.OapiMessageCorpconversationAsyncsendV2Response;
import com.dingtalk.api.response.OapiRoleSimplelistResponse;
import com.dingtalk.api.response.OapiUserGetResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


/*
Component
泛指各种组件，就是说当我们的类不属于各种归类的时候（不属于@Controller、@Services等的时候），
我们就可以使用@Component来标注这个类。
 */
/**
 * @author weiyao
 * @date 2020-06-02
 * @desc 定时任务获取对应 角色  下人员所有请假信息
 */
@Component
@Slf4j
public class PersonVacationInfo {


@Resource
RoleVacationInfoMapper roleVacationInfoMapper;

@Autowired
private DubboConfigService dubboConfigService;




//   @Scheduled(cron = "0 30 8 * * ? ")    //定时器，每天早上八点半执行一次
  // @Scheduled(cron = "0 0/5 * * * ? ")   //四分钟执行一次
    @Scheduled(cron = "0 0 9,21 * * ? *")   //定时器,每天早上九点和晚上九点执行一次
    public void getPersonVacationInfo() {
        log.info("开始执行获取角色下人员所有请假信息......");
        //获取token
        String token = DingDingUtil.getToken();
        int isInsert=roleVacationInfoMapper.getTodayCount();
        //今日没有查询
        if(isInsert<1){
        int countGb =0;//干部总人数
        int countQinjia=0;//请假总人数
        String roleVacationId= UUID.randomUUID().toString().replace("-","");//保存本地请假信息Id
        List<RoleVacationInfo> infoList=new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            String roleid = roleVacationInfoMapper.getddRoleId("干部");//干部钉钉角色id
            if (!StringUtils.isNotBlank(roleid)) {
                roleid = "557300626";
            }
            //干部角色id :557300626
            //消防员：1482045166，专职消防员：1261340294
            OapiRoleSimplelistResponse roleList = DingDingUtil.getRoleLists(roleid, token, i * 200);
            if (roleList.getResult().getList()!=null && roleList.getResult().getList().size() > 0) {
                countGb = countGb + roleList.getResult().getList().size();
                int sortKey = 1;
                //遍历每一个干部
                for (OapiRoleSimplelistResponse.OpenEmpSimple sim : roleList.getResult().getList()) {
                    //当天是否存在请假
                    OapiAttendanceGetleavestatusResponse qingjia = DingDingUtil.getleavestatus(sim.getUserid(), token);
                    if (qingjia.getResult().getLeaveStatus() != null && qingjia.getResult().getLeaveStatus().size() > 0) {
                        //获取用户详情，部门信息
                        OapiUserGetResponse userDetail=DingDingUtil.getUserDetail(sim.getUserid(),token);
                        //当天存在请假
                        //        System.out.println("===============干部请假人确认===============" + sim.getName());
                        countQinjia++;
                        RoleVacationInfo role = new RoleVacationInfo();
                        role.setParentId(roleVacationId);
                        role.setSortKey(sortKey);
                        role.setVacationName(sim.getName());
                        role.setUserId(sim.getUserid());
                        role.setId(UUID.randomUUID().toString().replace("-", ""));
                        if(userDetail !=null){
                            role.setPsition(userDetail.getPosition());//职务信息
                            //获取部门名称
                            List<Long> deptIdList=userDetail.getDepartment();
                            String deptName="";
                            if(deptIdList!=null){
                                for(Long deptid :deptIdList){
                                    String dname = roleVacationInfoMapper.getDeptNameByDDdeptId(String.valueOf(deptid));
                                    deptName=deptName+dname+" ";
                                }

                            }
                            role.setDeptName(deptName);
                        }

                        infoList.add(role);
                    }
                    sortKey++;
                }

            } else {
                break;
            }
        }
        //插入
            OrganizationFacade organizationFacade = dubboConfigService.getOrganizationFacade();
            UserModel user = organizationFacade.getUser(Constant.ADMIN_ID);
            DepartmentModel department = organizationFacade.getDepartment(user.getDepartmentId());
            RoleVacationInfo role = new RoleVacationInfo();
            role.setId(roleVacationId);
            role.setName("请假信息");
            role.setSequenceStatus(Constant.COMPLETED_STATUS);
            role.setWorkflowInstanceId(null);
            role.setCreater(Constant.ADMIN_ID);
            role.setCreatedDeptId(department.getId());
            role.setOwner(Constant.ADMIN_ID);
            role.setOwnerDeptId(department.getId());
            role.setOwnerDeptQueryCode(department.getQueryCode());
            role.setModifier(Constant.ADMIN_ID);
            role.setAllroleNum(countGb);//总人数
            role.setRoleName("干部");
            role.setVacationDate(new Date());
            role.setVacationNum(countQinjia);//请假人数
            role.setInworkNum(countGb-countQinjia);//在岗人数
        Integer isSucc=roleVacationInfoMapper.insertRoleVacation(role);
         //   log.info("主表插入是成功isSucc="+isSucc);
        Integer isSuccDetaul=roleVacationInfoMapper.insertVacationDetailList(infoList);
         //   log.info("子表插入是成功isSucc="+isSuccDetaul);

        //发送消息通知
 /*
            userList:通知人集合
            魏姚：19431116101255531  李姗珊：manager5388
            张卓：51594024776243  李坤懋：015907166926133173
            杨宏国：110041056326188470  陆时正:260730135237806323
            柯龙海:273626253926863917
             */
         String userList="19431116101255531,manager5388,51594024776243,015907166926133173,110041056326188470,260730135237806323,273626253926863917";
        //    String userList="19431116101255531";
         String message= DateUtil.getDate()+ " 干部总人数 "+countGb+" 人；"+"其中请假人数 "+countQinjia+" 人;"+"在岗人数 "+(countGb-countQinjia)+" 人";
        OapiMessageCorpconversationAsyncsendV2Response response =DingDingUtil.sendMessage(userList,token,message);
        }
    }

    @Scheduled(cron = "0 0 9,21 * * ? *")   //定时器,每天早上九点和晚上九点执行一次
//    @Scheduled(cron = "0 29 8 * * ? ")    //定时器，每天早上八点二十五执行一次--消防员
 //    @Scheduled(cron = "0 0/5 * * * ? ")   //四分钟执行一次
    public void getPersonXFYVacationInfo() {
        log.info("开始执行获取消防员角色下人员所有请假信息......");
        //获取token
        String token = DingDingUtil.getToken();
        int isInsert=roleVacationInfoMapper.getXfyTodayCount();
        //今日没有查询
        if(isInsert<1){
            int countGb =0;//干部总人数
            int countQinjia=0;//请假总人数
            String roleVacationId= UUID.randomUUID().toString().replace("-","");//保存本地请假信息Id
            List<RoleVacationInfo> infoList=new ArrayList<>();
            String roleid = roleVacationInfoMapper.getddRoleId("消防员");//干部钉钉角色id
            if (!StringUtils.isNotBlank(roleid)) {
                roleid = "1482045166";
            }
            for (int i = 0; i < 10; i++) {//922人
                //干部角色id :557300626
                //消防员：1482045166，专职消防员：1261340294
                OapiRoleSimplelistResponse roleList = DingDingUtil.getRoleLists(roleid, token, i * 200);
                if (roleList.getResult().getList()!=null && roleList.getResult().getList().size() > 0) {
                    countGb = countGb + roleList.getResult().getList().size();
                    int sortKey = 1;
                    //遍历每一个干部
                    for (OapiRoleSimplelistResponse.OpenEmpSimple sim : roleList.getResult().getList()) {
                        //当天是否存在请假
                        OapiAttendanceGetleavestatusResponse qingjia = DingDingUtil.getleavestatus(sim.getUserid(), token);
                        if (qingjia.getResult().getLeaveStatus() != null && qingjia.getResult().getLeaveStatus().size() > 0) {
                            //获取用户详情，部门信息
                            OapiUserGetResponse userDetail=DingDingUtil.getUserDetail(sim.getUserid(),token);
                            //当天存在请假
                            //        System.out.println("===============干部请假人确认===============" + sim.getName());
                            countQinjia++;
                            RoleVacationInfo role = new RoleVacationInfo();
                            role.setParentId(roleVacationId);
                            role.setSortKey(sortKey);
                            role.setVacationName(sim.getName());
                            role.setUserId(sim.getUserid());
                            role.setId(UUID.randomUUID().toString().replace("-", ""));
                            if(userDetail !=null){
                                role.setPsition(userDetail.getPosition());//职务信息
                                //获取部门名称
                                List<Long> deptIdList=userDetail.getDepartment();
                                String deptName="";
                                if(deptIdList!=null){
                                    for(Long deptid :deptIdList){
                                        String dname = roleVacationInfoMapper.getDeptNameByDDdeptId(String.valueOf(deptid));
                                        deptName=deptName+dname+" ";
                                    }

                                }
                                role.setDeptName(deptName);
                            }

                            infoList.add(role);
                        }
                        sortKey++;
                    }

                } else {
                    break;
                }
            }
            //插入
            OrganizationFacade organizationFacade = dubboConfigService.getOrganizationFacade();
            UserModel user = organizationFacade.getUser(Constant.ADMIN_ID);
            DepartmentModel department = organizationFacade.getDepartment(user.getDepartmentId());
            RoleVacationInfo role = new RoleVacationInfo();
            role.setId(roleVacationId);
            role.setName("请假信息");
            role.setSequenceStatus(Constant.COMPLETED_STATUS);
            role.setWorkflowInstanceId(null);
            role.setCreater(Constant.ADMIN_ID);
            role.setCreatedDeptId(department.getId());
            role.setOwner(Constant.ADMIN_ID);
            role.setOwnerDeptId(department.getId());
            role.setOwnerDeptQueryCode(department.getQueryCode());
            role.setModifier(Constant.ADMIN_ID);
            role.setAllroleNum(countGb);//总人数
            role.setRoleName("消防员");
            role.setVacationDate(new Date());
            role.setVacationNum(countQinjia);//请假人数
            role.setInworkNum(countGb-countQinjia);//在岗人数
            Integer isSucc=roleVacationInfoMapper.insertXfyRoleVacation(role);
            //   log.info("主表插入是成功isSucc="+isSucc);
            Integer isSuccDetaul=roleVacationInfoMapper.insertXfyVacationDetailList(infoList);
            //   log.info("子表插入是成功isSucc="+isSuccDetaul);

            //发送消息通知
             /*
            userList:通知人集合
            魏姚：19431116101255531  李姗珊：manager5388
            张卓：51594024776243  李坤懋：015907166926133173
            杨宏国：110041056326188470  陆时正:260730135237806323
            柯龙海:273626253926863917
             */
            String userList="19431116101255531,manager5388,51594024776243,015907166926133173,110041056326188470,260730135237806323,273626253926863917";
         //   String userList="19431116101255531";
            String message= DateUtil.getDate()+ " 消防员总人数 "+countGb+" 人；"+"其中请假人数 "+countQinjia+" 人;"+"在岗人数 "+(countGb-countQinjia)+" 人";
            OapiMessageCorpconversationAsyncsendV2Response response =DingDingUtil.sendMessage(userList,token,message);
        }
    }

    @Scheduled(cron = "0 0 9,21 * * ? *")   //定时器,每天早上九点和晚上九点执行一次
//    @Scheduled(cron = "0 27 8 * * ? ")    //定时器，每天早上八点二十执行一次--专职消防员
   //  @Scheduled(cron = "0 0/5 * * * ? ")   //四分钟执行一次
    public void getPersonZZXFYVacationInfo() {
        log.info("开始执行获取专职消防员角色下人员所有请假信息......");
        //获取token
        String token = DingDingUtil.getToken();
        int isInsert=roleVacationInfoMapper.getZzxfyTodayCount();
        //今日没有查询
        if(isInsert<1){
            int countGb =0;//干部总人数
            int countQinjia=0;//请假总人数
            String roleVacationId= UUID.randomUUID().toString().replace("-","");//保存本地请假信息Id
            List<RoleVacationInfo> infoList=new ArrayList<>();

            String roleid = roleVacationInfoMapper.getddRoleId("专职消防员");//干部钉钉角色id
            if (!StringUtils.isNotBlank(roleid)) {
                roleid = "1261340294";
            }
            for (int i = 0; i < 15; i++) {//1406人

                //干部角色id :557300626
                //消防员：1482045166，专职消防员：1261340294
                OapiRoleSimplelistResponse roleList = DingDingUtil.getRoleLists(roleid, token, i * 200);
                if (roleList.getResult().getList()!=null && roleList.getResult().getList().size() > 0) {
                    countGb = countGb + roleList.getResult().getList().size();
                    int sortKey = 1;
                    //遍历每一个干部
                    for (OapiRoleSimplelistResponse.OpenEmpSimple sim : roleList.getResult().getList()) {
                        //当天是否存在请假
                        OapiAttendanceGetleavestatusResponse qingjia = DingDingUtil.getleavestatus(sim.getUserid(), token);
                        if (qingjia.getResult().getLeaveStatus() != null && qingjia.getResult().getLeaveStatus().size() > 0) {
                            //获取用户详情，部门信息
                            OapiUserGetResponse userDetail=DingDingUtil.getUserDetail(sim.getUserid(),token);
                            //当天存在请假
                            //        System.out.println("===============干部请假人确认===============" + sim.getName());
                            countQinjia++;
                            RoleVacationInfo role = new RoleVacationInfo();
                            role.setParentId(roleVacationId);
                            role.setSortKey(sortKey);
                            role.setVacationName(sim.getName());
                            role.setUserId(sim.getUserid());
                            role.setId(UUID.randomUUID().toString().replace("-", ""));
                            if(userDetail !=null){
                                role.setPsition(userDetail.getPosition());//职务信息
                                //获取部门名称
                                List<Long> deptIdList=userDetail.getDepartment();
                                String deptName="";
                                if(deptIdList!=null){
                                    for(Long deptid :deptIdList){
                                        String dname = roleVacationInfoMapper.getDeptNameByDDdeptId(String.valueOf(deptid));
                                        deptName=deptName+dname+" ";
                                    }

                                }
                                role.setDeptName(deptName);
                            }

                            infoList.add(role);
                        }
                        sortKey++;
                    }

                } else {
                    break;
                }
            }
            //插入
            OrganizationFacade organizationFacade = dubboConfigService.getOrganizationFacade();
            UserModel user = organizationFacade.getUser(Constant.ADMIN_ID);
            DepartmentModel department = organizationFacade.getDepartment(user.getDepartmentId());
            RoleVacationInfo role = new RoleVacationInfo();
            role.setId(roleVacationId);
            role.setName("请假信息");
            role.setSequenceStatus(Constant.COMPLETED_STATUS);
            role.setWorkflowInstanceId(null);
            role.setCreater(Constant.ADMIN_ID);
            role.setCreatedDeptId(department.getId());
            role.setOwner(Constant.ADMIN_ID);
            role.setOwnerDeptId(department.getId());
            role.setOwnerDeptQueryCode(department.getQueryCode());
            role.setModifier(Constant.ADMIN_ID);
            role.setAllroleNum(countGb);//总人数
            role.setRoleName("专职消防员");
            role.setVacationDate(new Date());
            role.setVacationNum(countQinjia);//请假人数
            role.setInworkNum(countGb-countQinjia);//在岗人数
            Integer isSucc=roleVacationInfoMapper.insertZzxfyRoleVacation(role);
            //   log.info("主表插入是成功isSucc="+isSucc);
            Integer isSuccDetaul=roleVacationInfoMapper.insertZzxfyVacationDetailList(infoList);
            //   log.info("子表插入是成功isSucc="+isSuccDetaul);

            //发送消息通知
           /*
            userList:通知人集合
            魏姚：19431116101255531  李姗珊：manager5388
            张卓：51594024776243  李坤懋：015907166926133173
            杨宏国：110041056326188470  陆时正:260730135237806323
            柯龙海:273626253926863917
             */
            String userList="19431116101255531,manager5388,51594024776243,015907166926133173,110041056326188470,260730135237806323,273626253926863917";
          //  String userList="19431116101255531";
            String message= DateUtil.getDate()+ " 专职消防员总人数 "+countGb+" 人；"+"其中请假人数 "+countQinjia+" 人;"+"在岗人数 "+(countGb-countQinjia)+" 人";
            OapiMessageCorpconversationAsyncsendV2Response response =DingDingUtil.sendMessage(userList,token,message);
        }
    }
}
