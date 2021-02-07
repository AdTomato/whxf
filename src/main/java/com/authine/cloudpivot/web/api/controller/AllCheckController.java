package com.authine.cloudpivot.web.api.controller;

import com.authine.cloudpivot.engine.enums.ErrCode;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.dto.Drinking;
import com.authine.cloudpivot.web.api.entity.UserInfoByCheck;
import com.authine.cloudpivot.web.api.jiaqinxinxi.*;
import com.authine.cloudpivot.web.api.mapper.RoleVacationInfoMapper;
import com.authine.cloudpivot.web.api.service.AllCheckService;
import com.authine.cloudpivot.web.api.utils.DingDingUtil;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import com.dingtalk.api.response.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author: weiyao
 * @time: 2020/8/19
 * @Description: 全员考评
 */
@RestController
@Slf4j
@RequestMapping("/controller/allCheck")
public class AllCheckController extends BaseController {

    @Autowired
    AllCheckService allCheckService;

    @Resource
    RoleVacationInfoMapper roleVacationInfoMapper;

    /**
     * 查询支队，大队等部门
     */
    @GetMapping("/getDeptName")
    public ResponseResult<Object> getDeptName(String name) {
        if (StringUtils.isNotEmpty(name)) {
            List<Map<String,String>> list=allCheckService.getDeptListByName(name);
            return this.getErrResponseResult(list, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
        } else {
            return this.getErrResponseResult(null, 404L, "没有名称");
        }
    }

    /**
     * 根据部门查询用户信息
     */
    @GetMapping("/getUserByDept")
    public ResponseResult<Object> getUserByDept(String deptId,String userId) {
            List<UserInfoByCheck> list=allCheckService.getUserListByDept(deptId,userId);
            return this.getErrResponseResult(list, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }

    /**
     * 返回用户详细信息年龄，职务
     */
    @GetMapping("/getUserDetailInfoByDd")
    public ResponseResult<Object> getUserDetailInfoByDd(String dduserId) {
        if (StringUtils.isNotEmpty(dduserId)) {
            Map<String,Object> map=new HashMap<>();
            //获取用户详情，部门信息
            OapiUserGetResponse userDetail=DingDingUtil.getUserDetail(dduserId,DingDingUtil.getToken());
            map.put("position",userDetail.getPosition());//职务
            map.put("age","");//职务

            OapiSmartworkHrmEmployeeListResponse us = DingDingUtil.getEmployeeInfo(dduserId, DingDingUtil.getToken(),"sys02-birthTime");
            List<OapiSmartworkHrmEmployeeListResponse.EmpFieldInfoVO> result = us.getResult();
            String birthday="";
            if (result.get(0).getFieldList().size() > 0) {
                List<OapiSmartworkHrmEmployeeListResponse.EmpFieldVO>  lis=result.get(0).getFieldList();
                for(OapiSmartworkHrmEmployeeListResponse.EmpFieldVO li :lis){
                    //sys02-birthTime,sys02-sexType,sys02-certNo
                    if("sys02-birthTime".equals(li.getFieldCode())){
                        birthday=li.getLabel();
                        if(birthday !=null && birthday.length()>4){
                           int byear=Integer.parseInt(birthday.substring(0,4));
                            Calendar cal = Calendar.getInstance();
                            int  age = cal.get(Calendar.YEAR)-byear;//当前年份

                            map.put("age",age);
                        }
                    }
                }
            }

            return this.getErrResponseResult(map, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
        }else{
            return this.getErrResponseResult(null, 404L, "钉钉Id为空");
        }

    }

    /**
     * 获取考勤数据
     * @param type:7代表过去一周 30，代表最近一个月（默认）
     * @param dduserId 用户钉钉Id
     */
    @GetMapping("/getClockInfo")
    public ResponseResult<Object> getClockInfo(String dduserId,String type) {
        if (StringUtils.isNotEmpty(dduserId)) {
            Map<String,Object> map = new HashMap<>();
            String token=DingDingUtil.getToken();
            OapiAttendanceGetattcolumnsResponse ros=DingDingUtil.getAttcolumns(token);
            List<OapiAttendanceGetattcolumnsResponse.ColumnForTopVo> columList=ros.getResult().getColumns();
          /*  应出勤天数 ==Id为：82834769
            补卡次数 ==Id为：82834770 -
            出勤班次 ==Id为：82834771
            出勤天数 ==Id为：82834772 -
            休息天数 ==Id为：82834773 -
            工作时长 ==Id为：82834774
            迟到次数 ==Id为：82834775 -
            迟到时长 ==Id为：82834776
            严重迟到次数 ==Id为：82834777 -
            严重迟到时长 ==Id为：82834778
            旷工迟到天数 ==Id为：82834779
            早退次数 ==Id为：82834780
            早退时长 ==Id为：82834781
            上班缺卡次数 ==Id为：82834782
            下班缺卡次数 ==Id为：82834783
            旷工天数 ==Id为：82834784
            出差时长 ==Id为：82834785
            外出时长 ==Id为：82834786
            考勤结果 ==Id为：82834792
            班次 ==Id为：82834793   */
            String ids="82834769,82834770,82834771,82834772,82834773,82834774,82834775,82834776,82834777," +
                    "82834778,82834779,82834780,82834781,82834782,82834783,82834784,82834785,82834786,82834792";
//            for(OapiAttendanceGetattcolumnsResponse.ColumnForTopVo id:columList){
//               // ids=ids+id.getId()+",";
//                System.out.println("=====name:==="+id.getName()+" ==Id为："+id.getId());
//            }
           // ids=ids.substring(0,ids.length()-1);
            Calendar da = Calendar.getInstance();
            //过去七天
            da.setTime(new Date());
            if(StringUtils.isNotEmpty(type) && "7".equals(type)){
                da.add(Calendar.DATE, - 7);
            }else{
                da.add(Calendar.DATE, - 30);
            }

            Date startDate = da.getTime();
            OapiAttendanceGetcolumnvalResponse cloList=DingDingUtil.getAttcolumnInfo(dduserId,ids,token,startDate,new Date());
            if(cloList.getResult() !=null && cloList.getResult().getColumnVals().size()>0){
                List<OapiAttendanceGetcolumnvalResponse.ColumnValForTopVo> vo= cloList.getResult().getColumnVals();
                for(OapiAttendanceGetcolumnvalResponse.ColumnValForTopVo list :vo){
                 //   System.out.println("=====id值："+list.getColumnVo().getId());
                    if("82834772".equals(list.getColumnVo().getId().toString())){
                         Float count=0F;
                        //出勤天数
                        for(OapiAttendanceGetcolumnvalResponse.ColumnDayAndVal valist :list.getColumnVals()){
                            count=count+Float.valueOf(valist.getValue());
                        }
                        map.put("chuqingtianshu",count);
                    }else if("82834769".equals(list.getColumnVo().getId().toString())){
                        //应出勤天数
                        Float count=0F;
                        for(OapiAttendanceGetcolumnvalResponse.ColumnDayAndVal valist :list.getColumnVals()){
                            count=count+Float.valueOf(valist.getValue());
                        }
                        map.put("yingchuqingtianshu",count);

                    }else if("82834773".equals(list.getColumnVo().getId().toString())){
                        //休息天数
                        Float count=0F;
                        for(OapiAttendanceGetcolumnvalResponse.ColumnDayAndVal valist :list.getColumnVals()){
                            count=count+Float.valueOf(valist.getValue());
                        }
                        map.put("xiuxitianshu",count);

                    }else if("82834770".equals(list.getColumnVo().getId().toString())){
                        //补卡次数
                        Integer count=0;
                        for(OapiAttendanceGetcolumnvalResponse.ColumnDayAndVal valist :list.getColumnVals()){
                            count=count+Integer.valueOf(valist.getValue());
                        }
                        map.put("bukacishu",count);

                    }else if("82834775".equals(list.getColumnVo().getId().toString())){
                        //迟到次数
                        Integer count=0;
                        for(OapiAttendanceGetcolumnvalResponse.ColumnDayAndVal valist :list.getColumnVals()){
                            count=count+Integer.valueOf(valist.getValue());
                        }
                        map.put("chidaocishu",count);

                    }else if("82834777".equals(list.getColumnVo().getId().toString())){
                        //严重迟到次数
                        Integer count=0;
                        for(OapiAttendanceGetcolumnvalResponse.ColumnDayAndVal valist :list.getColumnVals()){
                            count=count+Integer.valueOf(valist.getValue());
                        }
                        map.put("yanzhongchidao",count);

                    }else if("82834779".equals(list.getColumnVo().getId().toString())){
                        //旷工迟到天数
                        Integer count=0;
                        for(OapiAttendanceGetcolumnvalResponse.ColumnDayAndVal valist :list.getColumnVals()){
                            count=count+Integer.valueOf(valist.getValue());
                        }
                        map.put("kuanggongchidao",count);

                    }else if("82834780".equals(list.getColumnVo().getId().toString())){
                        //早退次数
                        Integer count=0;
                        for(OapiAttendanceGetcolumnvalResponse.ColumnDayAndVal valist :list.getColumnVals()){
                            count=count+Integer.valueOf(valist.getValue());
                        }
                        map.put("zaotuicishu",count);

                    }else if("82834784".equals(list.getColumnVo().getId().toString())){
                        //旷工天数
                        Integer count=0;
                        for(OapiAttendanceGetcolumnvalResponse.ColumnDayAndVal valist :list.getColumnVals()){
                            count=count+Integer.valueOf(valist.getValue());
                        }
                        map.put("kuanggongtianshu",count);

                    }else if("82834785".equals(list.getColumnVo().getId().toString())){
                        //出差时长
                        Float count=0F;
                        for(OapiAttendanceGetcolumnvalResponse.ColumnDayAndVal valist :list.getColumnVals()){
                            count=count+Float.valueOf(valist.getValue());
                        }
                        map.put("chuchaishichang",count);

                    }else if("82834786".equals(list.getColumnVo().getId().toString())){
                        //外出时长
                        Float count=0F;
                        for(OapiAttendanceGetcolumnvalResponse.ColumnDayAndVal valist :list.getColumnVals()){
                            count=count+Float.valueOf(valist.getValue());
                        }
                        map.put("waichushichang",count);

                    }
                }
            }

            return this.getErrResponseResult(map, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
        } else {
            return this.getErrResponseResult(null, 404L, "没有用户Id");
        }
    }


    /**
     * 角色请假人员详情
     *
     * @return {@link ResponseResult<Object>}
     */
    @GetMapping("/getPersonVacationInfo")
    public ResponseResult<Object> getPersonVacationInfo(){

/*        String getCadreLeave();
        String getFiremenLeave();
        String getProfessionalPlayers();
        String getClerkLeave();*/
        LerveDto lerveDto = new LerveDto();

        List<CadreLeave> cadreLeave = roleVacationInfoMapper.getCadreLeave();
        lerveDto.setCadreLeavelist(cadreLeave);
        List<FiremenLeave> firemenLeave = roleVacationInfoMapper.getFiremenLeave();
        lerveDto.setFiremenLeaveList(firemenLeave);
        List<ProfessionalPlayers> professionalPlayers = roleVacationInfoMapper.getProfessionalPlayers();
        lerveDto.setProfessionalPlayersList(professionalPlayers);
        List<ClerkLeave> clerkLeave = roleVacationInfoMapper.getClerkLeave();
        lerveDto.setClerkLeaveList(clerkLeave);

        if (lerveDto == null) {
            return this.getErrResponseResult("无数据", ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
        }else {
            return this.getErrResponseResult(lerveDto, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
        }

    }

    /**
     * 休假审批情况
     *
     * @return {@link ResponseResult<Object>}
     */
    @GetMapping("/getAboutVacations")
    public ResponseResult<Object> getAboutVacations(String dingDingId) {
        //获取token
        String token = DingDingUtil.getToken();
        if (StringUtils.isNotEmpty(dingDingId)) {
            //获取当月请假信息
//            OapiAttendanceGetleavestatusResponse noteForLeave = DingDingUtil.getleavestatus(dingDingId, token);

            OapiProcessinstanceListidsResponse approvalIds = DingDingUtil.getApprovalGanbuIds(dingDingId, token);
//            System.out.println("approvalIds = " + approvalIds);
            List<String> qingJiaList = approvalIds.getResult().getList();
//            System.out.println("noteForLeave = " + noteForLeave);
            if (qingJiaList == null&& qingJiaList.size() == 0) {
                return this.getErrResponseResult("当月无请假审批记录", ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
            } else {
                Map<String,Object> map=new HashMap<>();
                LeaveInformation leaveInformation = new LeaveInformation();
//                List<LeaveInformation> leaveInformationList = null;
                ArrayList<LeaveInformation> list = new ArrayList<>();
                for (String qingjiaid : qingJiaList) {
                    OapiProcessinstanceGetResponse approvalDetail = DingDingUtil.getApprovalDetail(qingjiaid, token);
                    //新建干部休假信息类
                    leaveInformation.setCreateTime(approvalDetail.getProcessInstance().getCreateTime());
                    List<OapiProcessinstanceGetResponse.FormComponentValueVo> a =   approvalDetail.getProcessInstance().getFormComponentValues();
                    for (OapiProcessinstanceGetResponse.FormComponentValueVo formComponentValueVo : a) {
//                        System.out.println("formComponentValueVo = " + formComponentValueVo.getName() +"+"+ formComponentValueVo.getValue());


                        if ("请假事由".equals(formComponentValueVo.getName()) ){
                            leaveInformation.setReason(formComponentValueVo.getValue());
                        }


                        if ("[\"开始时间\",\"结束时间\"]".equals(formComponentValueVo.getName()) ){
                          /*  System.out.println( "0"+ formComponentValueVo.getValue().charAt(0));
                            System.out.println( "1"+ formComponentValueVo.getValue().charAt(1));
                            System.out.println( "2"+ formComponentValueVo.getValue().charAt(2));
                            System.out.println( "3"+ formComponentValueVo.getValue().charAt(3));
                            System.out.println( "4444"+ formComponentValueVo.getValue().charAt(4));
                            System.out.println("toooo = " + formComponentValueVo.getValue().toString());
                            System.out.println("formComponentValueVo = " + formComponentValueVo.getValue().substring(2,18).toString());
                            System.out.println("时间 = " + formComponentValueVo.getValue());*/
                            leaveInformation.setStartTime(formComponentValueVo.getValue().substring(2,18).toString());
                            leaveInformation.setEndTime(formComponentValueVo.getValue().substring(21,36).toString());
                        }
                        if ("*关联审批单".equals(formComponentValueVo.getName()) ){
//                            System.out.println("name = " + formComponentValueVo.getValue().substring(2,4));
                            leaveInformation.setName(formComponentValueVo.getValue().substring(2,5));
                        }
                    }

//                    leaveInformationList.set(1,leaveInformation);
//                    map.put("假勤数据",leaveInformation);
                    list.add(leaveInformation);

                }

//                return this.getErrResponseResult(leaveInformationList, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
                return this.getErrResponseResult(list, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
            }
        }else
        {
            return this.getErrResponseResult(null, 404L, "钉钉Id为空");
        }

    }

    /**
     * 饮酒报备记录
     *
     * @param dingDingId 用户钉钉id
     * @return {@link ResponseResult<Object>}
     */
    @GetMapping("/getDrinking")
    public ResponseResult<Object> getDrinking(String dingDingId) {
        //获取token
        String token = DingDingUtil.getToken();
        if (StringUtils.isNotEmpty(dingDingId)) {

            OapiProcessinstanceListidsResponse userIdDrinkId = DingDingUtil.getApprovalId(dingDingId, token);
            List<String> userIdLists = userIdDrinkId.getResult().getList();
            if (userIdLists == null && userIdLists.size() == 0) {
                return this.getErrResponseResult("近一周无饮酒报备记录", ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());

            } else {
                Map<String,Object> map=new HashMap<>();
                for (String userIdList : userIdLists) {
                    OapiProcessinstanceGetResponse approvalDetail = DingDingUtil.getApprovalDetail(userIdList, token);
                    Drinking drinking = new Drinking();
                    List<OapiProcessinstanceGetResponse.FormComponentValueVo> a =   approvalDetail.getProcessInstance().getFormComponentValues();

//                    System.out.println("a = " + a);
                    for (OapiProcessinstanceGetResponse.FormComponentValueVo formComponentValueVo : a) {
//                        System.out.println("formComponentValueVo = " + formComponentValueVo.getName() +"+"+ formComponentValueVo.getValue());

                        if ("饮酒时间".equals(formComponentValueVo.getName())){
//                            System.out.println("formComponentValueVo = " + formComponentValueVo.getName() + formComponentValueVo.getValue());
                            drinking.setDate(formComponentValueVo.getValue());
                        }
                        if ("饮酒地点".equals(formComponentValueVo.getName())){
                            drinking.setSite(formComponentValueVo.getValue());
                        }
                        if ("饮酒事由".equals(formComponentValueVo.getName()) ){
                            drinking.setReason(formComponentValueVo.getValue());
                        }
                        ;map.put("饮酒报备",drinking);
                    }
//                    drinking.getDate(approvalDetail.getProcessInstance().getFormComponentValues().);
//                    map.put("饮酒报备",approvalDetail.getProcessInstance().getFormComponentValues());
                }
                return this.getErrResponseResult(map, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
            }
        }
        else
            {
                return this.getErrResponseResult(null, 404L, "钉钉Id为空");
            }

    }
}