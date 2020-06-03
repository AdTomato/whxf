package com.authine.cloudpivot.web.api.utils;

import com.authine.cloudpivot.web.api.entity.CreateCalendarVo;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.*;
import com.dingtalk.api.response.*;
import com.esotericsoftware.minlog.Log;
import com.taobao.api.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;


/**
 * 对接钉钉的工具类
 * @author weiyao
 * @date 2020-05-08
 */
@Slf4j
public class DingDingUtil {

    /**
     * 企业Id
     */
    private static String CORPID = "dingydna2ntaym0hoika";

    /**
     * 企业应用的凭证密钥
     */
    private static String CORPSECRET = "AgMZ9kJTLwgIuQS2CVRgbu1wufF_1G4VKM09TDQ7f3p1dECdozaKJBWr-zHNkCcd";

    /**
     * 流程模板唯一标识
     * 消防员请假单
     */
    private static String PROCESSCODEBYXFY = "PROC-5C82DFFE-C890-42CC-831F-51F6FB3FA269";

    /**
     * 流程模板唯一标识
     * 专职消防员请假单：PROC-B7FFF912-25FE-40EF-A7A0-E04E8F3DCA30
     */
    private static String PROCESSCODEBYZZXFY = "PROC-B7FFF912-25FE-40EF-A7A0-E04E8F3DCA30";

    /**
     * 流程模板唯一标识
     * 通用审批：PROC-8E6D2992-B665-4848-8757-77AF55546306
     */
    private static String PROCESSCODEBYTYSP = "PROC-8E6D2992-B665-4848-8757-77AF55546306";

    /**
     * 流程模板唯一标识
     * 商旅出差：PROC-07BBDF5E-F582-409D-9878-37AABD470E77
     */
    private static String PROCESSCODEBYSLCC = "PROC-07BBDF5E-F582-409D-9878-37AABD470E77";

    /**
     * 流程模板唯一标识
     * 公差勤务：PROC-D6308678-78E1-416F-933B-CE4459FB27E9
     */
    private static String PROCESSCODEBYGCQW = "PROC-D6308678-78E1-416F-933B-CE4459FB27E9";

    /**
     * 流程模板唯一标识
     * 干部请假单： PROC-4633A02B-5710-4201-98C0-6608D962EACF
     */
    private static String PROCESSCODEBYGBQJ = "PROC-4633A02B-5710-4201-98C0-6608D962EACF";
    @Autowired
    RedisUtils redisUtils;

    /**
     * 自动分配微应用的ID
     */
    public static Long AGENTID = 324614090L;

    public static String getToken () {
        DefaultDingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
        OapiGettokenRequest request = new OapiGettokenRequest();
        request.setAppkey(CORPID);
        request.setAppsecret(CORPSECRET);
        request.setHttpMethod("GET");
        OapiGettokenResponse response = null;
        try {
            response = client.execute(request);
        } catch (ApiException e) {
            log.info("获取token错误:"+e.getErrMsg());
        }
        return response.getAccessToken();
    }

    public static String createCalendar(CreateCalendarVo calendarVo) {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/calendar/create");
        OapiCalendarCreateRequest request = new OapiCalendarCreateRequest();
        OapiCalendarCreateRequest.OpenCalendarCreateVo creatVo = new OapiCalendarCreateRequest.OpenCalendarCreateVo();
        creatVo.setUuid(UUID.randomUUID().toString().replaceAll("-", ""));
        creatVo.setBizId("test123");
        creatVo.setCreatorUserid(calendarVo.getCreator_userid());
        creatVo.setSummary(calendarVo.getSummary());
        //creatVo.setReceiverUserids(Arrays.asList(calendarVo.getReceiver_userids()));
        creatVo.setReceiverUserids(calendarVo.getReceiver_userids());
        OapiCalendarCreateRequest.DatetimeVo start = new OapiCalendarCreateRequest.DatetimeVo();
        start.setUnixTimestamp(calendarVo.getStart_time().getTime()-10000);
        creatVo.setStartTime(start);
        OapiCalendarCreateRequest.DatetimeVo end = new OapiCalendarCreateRequest.DatetimeVo();
        end.setUnixTimestamp(calendarVo.getEnd_time().getTime());
        creatVo.setEndTime(end);
        creatVo.setCalendarType("notification");
        OapiCalendarCreateRequest.OpenCalendarSourceVo source = new OapiCalendarCreateRequest.OpenCalendarSourceVo();
        source.setTitle(calendarVo.getTitle());
        source.setUrl("http://www.dingtalk.com/page=xxx");
        creatVo.setSource(source);
        request.setCreateVo(creatVo);
        OapiCalendarCreateResponse response;
        try {
            response = client.execute(request, DingDingUtil.getToken());
            if(response.getResult()!=null){
                Log.info("日程创建成功,日程id为:"+ response.getResult().getDingtalkCalendarId());
                return response.getResult().getDingtalkCalendarId();
            }
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *查询企业考勤排班详情 (固定班制只能查到未来15天的排班信息)
     * @param workDate  排班时间，只取年月日部分
     * @param offset 偏移位置，从0开始，后续传offset+size的值。当返回结果中的has_more为false时，表示没有多余的数据了。
     * @param size  分页大小，最大200，默认值：200
     * @return
     */
    public  OapiAttendanceListscheduleResponse.AtScheduleListForTopVo listschedule(Date workDate, Long offset, Long size){
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/attendance/listschedule");
        OapiAttendanceListscheduleRequest request = new OapiAttendanceListscheduleRequest();
        request.setWorkDate(new Date());
        request.setOffset(0L);
        request.setSize(100L);
        OapiAttendanceListscheduleResponse execute = null;
        OapiAttendanceListscheduleResponse.AtScheduleListForTopVo result =null;
        try {
            execute= client.execute(request,getToken());
            result = execute.getResult();
        } catch (ApiException e) {
            log.info("获取token错误:"+e.getErrMsg());

        }
        return result;
    }

    /**
     * 查询成员排班信息(查询某人某天的排班信息)
     * @param op_user_id 操作人userId
     * @param user_id  用户userId
     * @param date_time 查询哪天的数据，unix时间戳
     * @return
     */

    public static OapiAttendanceScheduleListbydayResponse listbyday(String op_user_id , String user_id, Date date_time,String token){
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/attendance/schedule/listbyday");
        OapiAttendanceScheduleListbydayRequest req = new OapiAttendanceScheduleListbydayRequest();
        req.setOpUserId(op_user_id);
        req.setUserId(user_id);
        req.setDateTime(date_time.getTime());
        OapiAttendanceScheduleListbydayResponse rsp = null;
        // List<OapiAttendanceScheduleListbydayResponse.TopScheduleVo> result = null;
        try {
            rsp = client.execute(req, token);
            if (rsp.getErrcode() == 0) {
                //    result= rsp.getResult();

                return rsp;
            }

        } catch (ApiException e) {
            log.info("获取token错误:"+e.getErrMsg());
        }
        //  System.out.println(rsp.getBody());
        return rsp;
    }


    /**
     *排班制考勤组排班 (给排班制考勤组成员进行排班)
     * @param op_user_id  操作人userId
     * @param group_id   考勤组id
     * @param schedules     排班详情
     * @return
     */
    public Boolean async(String op_user_id,long group_id, OapiAttendanceGroupScheduleAsyncRequest.TopScheduleParam[] schedules){
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/attendance/group/schedule/async");
        OapiAttendanceGroupScheduleAsyncRequest req = new OapiAttendanceGroupScheduleAsyncRequest();
        req.setOpUserId("dd_test");
        req.setGroupId(2987L);
        List<OapiAttendanceGroupScheduleAsyncRequest.TopScheduleParam> list2 = new ArrayList<OapiAttendanceGroupScheduleAsyncRequest.TopScheduleParam>();
        OapiAttendanceGroupScheduleAsyncRequest.TopScheduleParam obj3 = new OapiAttendanceGroupScheduleAsyncRequest.TopScheduleParam();
        list2.add(obj3);
        obj3.setShiftId(1L);
        obj3.setWorkDate(1564985177000L);
        obj3.setIsRest(false);
        obj3.setUserid("dd_test");
        req.setSchedules(list2);
        OapiAttendanceGroupScheduleAsyncResponse rsp = null;
        Boolean success = false;
        try {
            rsp = client.execute(req, getToken());
            success = rsp.getSuccess();
        } catch (ApiException e) {
            log.info("获取token错误:"+e.getErrMsg());
        }
        System.out.println(rsp.getBody());
        return success;
    }

    /**
     * 查询排班打卡结果(查询排班的打卡结果，包含用户打卡时间、迟到、早退、内勤及外勤等内容)
     * @param op_user_id  操作人userId
     * @param schedule_ids  排班id
     * @return
     */
    public List<OapiAttendanceScheduleResultListbyidsResponse.TopScheduleResultVo> listbyids(String op_user_id,Number[] schedule_ids){
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/attendance/schedule/result/listbyids");
        OapiAttendanceScheduleResultListbyidsRequest req = new OapiAttendanceScheduleResultListbyidsRequest();
        req.setOpUserId("dd_dd");
        req.setScheduleIds("1234,3214");
        OapiAttendanceScheduleResultListbyidsResponse rsp = null;
        List<OapiAttendanceScheduleResultListbyidsResponse.TopScheduleResultVo> result = null;
        try {
            rsp = client.execute(req, getToken());
            result = rsp.getResult();
        } catch (ApiException e) {
            log.info("获取token错误:"+e.getErrMsg());
        }
        System.out.println(rsp.getBody());
        return result;
    }


    /**
     * 批量查询班次摘要信息(分页获取企业内所有班次的摘要信息，包含班次名称和id)
     * @param op_user_id
     * @param cursor
     * @return
     */
    public OapiAttendanceShiftListResponse.PageResult listAttendance(String op_user_id,Number cursor){
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/attendance/shift/list");
        OapiAttendanceShiftListRequest req = new OapiAttendanceShiftListRequest();
        req.setOpUserId("dd_test");
        req.setCursor(1234L);
        OapiAttendanceShiftListResponse execute = null;
        OapiAttendanceShiftListResponse.PageResult result = null;
        try {
            execute = client.execute(req, getToken());
            result = execute.getResult();
        } catch (ApiException e) {
            log.info("获取token错误:"+e.getErrMsg());
        }
        return result;
    }


    /**
     * 查询班次详情(根据班次id查询班次详情信息)
     * @param op_user_id 操作人userId
     * @param shift_id 班次id
     * @return
     */
    public OapiAttendanceShiftQueryResponse.TopShiftVo  queryAttendanceById(String op_user_id,Number shift_id){
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/attendance/shift/query");
        OapiAttendanceShiftQueryRequest req = new OapiAttendanceShiftQueryRequest();
        req.setOpUserId("dd_dd");
        req.setShiftId(2445L);
        OapiAttendanceShiftQueryResponse rsp = null;
        OapiAttendanceShiftQueryResponse.TopShiftVo result = null;

        try {
            rsp = client.execute(req, getToken());
            result = rsp.getResult();
        } catch (ApiException e) {
            e.printStackTrace();
        }
        System.out.println(rsp.getBody());
        return result;
    }


    /**
     * 按名称搜索班次(根据名称模糊搜索班次，返回班次名称和id信息)
     * @param op_user_id 操作人userId
     * @param shift_name 班次名称
     * @return
     */
    public  List<OapiAttendanceShiftSearchResponse.TopMinimalismShiftVO>  queryAttendanceByName(String op_user_id,String shift_name){
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/attendance/shift/search");
        OapiAttendanceShiftSearchRequest req = new OapiAttendanceShiftSearchRequest();
        req.setOpUserId("dd_dd");
        req.setShiftName("常白班");
        OapiAttendanceShiftSearchResponse rsp = null;
        List<OapiAttendanceShiftSearchResponse.TopMinimalismShiftVO> result = null;
        try {
            rsp = client.execute(req, getToken());
            result = rsp.getResult();
        } catch (ApiException e) {
            e.printStackTrace();
        }
        System.out.println(rsp.getBody());
        return result;
    }

    /**
     * 批量获取企业考勤组详情
     * @return
     */
    public   OapiAttendanceGetsimplegroupsResponse.AtGroupListForTopVo getsimplegroups(){
        DefaultDingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/attendance/getsimplegroups");
        OapiAttendanceGetsimplegroupsRequest request = new OapiAttendanceGetsimplegroupsRequest();
        OapiAttendanceGetsimplegroupsResponse execute = null;
        OapiAttendanceGetsimplegroupsResponse.AtGroupListForTopVo result = null;
        try {
            execute = client.execute(request,getToken());
            result = execute.getResult();
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 获取用户考勤组
     * @param userid 员工在企业内的UserID，企业用来唯一标识用户的字段
     * @return
     */
    public  OapiAttendanceGetusergroupResponse.AtGroupFullForTopVo getusergroup(String  userid){
        DefaultDingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/attendance/getusergroup");
        OapiAttendanceGetusergroupRequest request = new OapiAttendanceGetusergroupRequest();
        request.setUserid("1226682231742708");
        OapiAttendanceGetusergroupResponse response = null;
        OapiAttendanceGetusergroupResponse.AtGroupFullForTopVo result =null;
        try {
            response = client.execute(request,getToken());
            result = response.getResult();
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 获取打卡结果
     * @param workDateFrom  查询考勤打卡记录的起始工作日 格式为“yyyy-MM-dd HH:mm:ss”
     * @param workDateTo    查询考勤打卡记录的结束工作日
     * @param userIdList    员工在企业内的UserID列表，企业用来唯一标识用户的字段。最多不能超过50个
     * @return
     */
    public static List<OapiAttendanceListResponse.Recordresult> queryAttendanceList(String workDateFrom, String workDateTo, List userIdList){
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/attendance/list");
        OapiAttendanceListRequest request = new OapiAttendanceListRequest();
        request.setWorkDateFrom(workDateFrom);
        request.setWorkDateTo(workDateTo);
        request.setUserIdList(userIdList);
        request.setOffset(0L);   //表示获取考勤数据的起始点，第一次传0，如果还有多余数据，下次获取传的offset值为之前的offset+limit，0、1、2...依次递增
        request.setLimit(50L);    //表示获取考勤数据的条数，最大不能超过50条
        OapiAttendanceListResponse response = null;
        List<OapiAttendanceListResponse.Recordresult> recordresult  = null;
        try {
            response = client.execute(request, getToken());
            if (response.getErrcode() == 0) {
                recordresult= response.getRecordresult();
            }
        } catch (ApiException e) {
            e.printStackTrace();
        }
        System.out.println(response.getBody());
        return recordresult;
    }



    /**
     * 获取用户公告数据
     * @param userId
     * @return
     */
    public static List<OapiBlackboardListtoptenResponse.OapiBlackboardVo> listtopten(String userId,String token){
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/blackboard/listtopten");
        OapiBlackboardListtoptenRequest request = new OapiBlackboardListtoptenRequest();
        request.setUserid(userId);
        OapiBlackboardListtoptenResponse execute = null;
        List<OapiBlackboardListtoptenResponse.OapiBlackboardVo> blackboardList = null;
        try {
            execute = client.execute(request, token);
            if (execute.getErrcode() == 0) {
                blackboardList = execute.getBlackboardList();
            }
        } catch (ApiException e) {
            log.info("获取token错误:"+e.getErrMsg());
        }
        //   log.info("用户发布的公告信息:"+execute.getBody());
        return blackboardList;
    }


    /**
     * 获取用户详情
     * @param userId
     * @return
     */
    public static OapiUserGetResponse getUserDetail(String userId){
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/get");
        OapiUserGetRequest request = new OapiUserGetRequest();
        request.setUserid(userId);
        request.setHttpMethod("GET");
        OapiUserGetResponse response = null;
        //  List<OapiBlackboardListtoptenResponse.OapiBlackboardVo> oapiBlackboardVos = null;
        // List<OapiUserGetResponse.Roles> roles = null;
        try {
            response = client.execute(request, getToken());
            if (response.getErrcode() == 0) {
                return response;
            }
        } catch (ApiException e) {
            log.info("获取token错误:"+e.getErrMsg());
        }
        return response;
    }

    /**
     * 获取部门用户详情
     * @param deptId
     * @return
     */
    public static OapiUserListbypageResponse getDeptUserByDeptId(String deptId,String token){
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/listbypage");
        OapiUserListbypageRequest request = new OapiUserListbypageRequest();
        request.setDepartmentId(Long.parseLong(deptId));//获取的部门id，1表示根部门
        request.setOffset(0L);//与size参数同时设置时才生效，此参数代表偏移量，偏移量从0开始
        request.setSize(100L);//支持分页查询，与offset参数同时设置时才生效，此参数代表分页大小，最大100
        request.setOrder("custom");
        request.setHttpMethod("GET");
        OapiUserListbypageResponse response =null;
        try {
            response = client.execute(request, token);
            if (response.getErrcode() == 0) {
                return response;
            }else{
                log.info("错误原因:"+response.getErrmsg());
            }
        } catch (ApiException e) {
            log.info("获取token错误:"+e.getErrMsg());
        }
        return response;
    }

    /**
     * 发起时间在某时间段内的审批实例id列表（七天之前到当前时间）
     * @param userIds
     * @return
     */
    public static OapiProcessinstanceListidsResponse   getApprovalIds(String userIds,String token){
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/processinstance/listids");
        OapiProcessinstanceListidsRequest req = new OapiProcessinstanceListidsRequest();
     //   req.setProcessCode(PROCESSCODEBYSLCC);//商旅出差
        req.setProcessCode(PROCESSCODEBYGCQW);//公差勤务
        //  Date[] dates=DateUtil.getWeek(); 本周一，本周日
        long current=System.currentTimeMillis();    //当前时间毫秒数（时间戳）
        long daysAgo7=current-24*60*60*1000*7;//七天之前时间戳
        req.setStartTime(daysAgo7);
        req.setEndTime(current);
        req.setSize(10L);//分页参数，每页大小，最多传20，默认值：20
        req.setCursor(0L);
        req.setUseridList(userIds);//发起人用户id列表，用逗号分隔，最大列表长度：10
        OapiProcessinstanceListidsResponse  response =null;
        try {
            response = client.execute(req, token);
            if (response.getErrcode() == 0) {
                return response;
            }else{
                log.info("错误原因:"+response.getErrmsg());
            }
        } catch (ApiException e) {
            log.info("获取token错误:"+e.getErrMsg());
        }
        return null;
    }

    /**
     * 发起时间在某时间段内的审批实例id列表（当前时间到 提前 days 天）
     * @param process 审批模板
     * @param days 提前的天数
     * @return
     */
    public static OapiProcessinstanceListidsResponse   getApprovalIdsByProcess(String userIds,String token,String process,int days){
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/processinstance/listids");
        OapiProcessinstanceListidsRequest req = new OapiProcessinstanceListidsRequest();
        req.setProcessCode(process);//审批模板
        //  Date[] dates=DateUtil.getWeek(); 本周一，本周日
        long current=System.currentTimeMillis();    //当前时间毫秒数（时间戳）
        long daysAgo=current-24*60*60*1000*days;//七天之前时间戳
        req.setStartTime(daysAgo);
        req.setEndTime(current);
        req.setSize(10L);//分页参数，每页大小，最多传20，默认值：20
        req.setCursor(0L);
        req.setUseridList(userIds);//发起人用户id列表，用逗号分隔，最大列表长度：10
        OapiProcessinstanceListidsResponse  response =null;
        try {
            response = client.execute(req, token);
            if (response.getErrcode() == 0) {
                return response;
            }else{
                log.info("错误原因:"+response.getErrmsg());
            }
        } catch (ApiException e) {
            log.info("获取token错误:"+e.getErrMsg());
        }
        return null;
    }

    /**
     * 根据审批实例id调用此接口获取审批实例详情，包括审批表单信息、操作记录列表、操作人、抄送人、审批任务列表等。
     * @param processId
     * @return
     */
    public static OapiProcessinstanceGetResponse   getApprovalDetail(String processId,String token){
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/processinstance/get");
        OapiProcessinstanceGetRequest request = new OapiProcessinstanceGetRequest();
        request.setProcessInstanceId(processId);//审批实例Id
        OapiProcessinstanceGetResponse   response =null;
        try {
            response = client.execute(request, token);
            if (response.getErrcode() == 0) {
                return response;
            }else{
                log.info("错误原因:"+response.getErrmsg());
            }
        } catch (ApiException e) {
            log.info("获取token错误:"+e.getErrMsg());
        }
        return response;
    }

    /**
     * 获取(子)部门列表
     * @param deptId
     * @return
     */
    public static OapiDepartmentListResponse    getDeptList(String deptId,String token){
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/department/list");
        OapiDepartmentListRequest request = new OapiDepartmentListRequest();
        request.setId(deptId);//父部门id（如果不传，默认部门为根部门，根部门ID为1）
        request.setHttpMethod("GET");
        OapiDepartmentListResponse    response =null;
        try {
            response = client.execute(request, token);
            if (response.getErrcode() == 0) {
                return response;
            }else{
                log.info("错误原因:"+response.getErrmsg());
            }
        } catch (ApiException e) {
            log.info("获取token错误:"+e.getErrMsg());
        }
        return response;
    }

    /**
     * 获取员工花名册信息--生日信息
     * @param userIdList
     * @return
     */
    public static OapiSmartworkHrmEmployeeListResponse     getBirthDayforEmployee(String userIdList,String token){
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/smartwork/hrm/employee/list");
        OapiSmartworkHrmEmployeeListRequest req = new OapiSmartworkHrmEmployeeListRequest();
        req.setUseridList(userIdList);//员工userid列表，最大列表长度：20
        //需要获取的花名册字段列表，最大列表长度：20。具体业务字段的code参见附录（大小写敏感）。不传入该参数时，企业可获取所有字段信息。
        req.setFieldFilterList("sys02-birthTime");//sys02-birthTime：生日。具体业务字段的code参见附录（大小写敏感）。不传入该参数时，企业可获取所有字段信息。
        OapiSmartworkHrmEmployeeListResponse   response =null;
        try {
            response = client.execute(req, token);
            if (response.getErrcode() == 0) {
                return response;
            }else{
                log.info("错误原因:"+response.getErrmsg());
            }
        } catch (ApiException e) {
            log.info("获取token错误:"+e.getErrMsg());
        }
        return response;
    }

    /**
     * 查询指定企业下的指定用户在指定时间（当天时间）段内的请假状态
     * @param userIdList
     * @return
     */
    public static OapiAttendanceGetleavestatusResponse    getleavestatus(String userIdList,String token){
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/attendance/getleavestatus");
        OapiAttendanceGetleavestatusRequest req = new OapiAttendanceGetleavestatusRequest();
        req.setUseridList(userIdList);
        Date date=new   Date(); //取时间
        //获取当天首尾时间戳
        long current=System.currentTimeMillis();    //当前时间毫秒数
        //今天零点零分零秒的毫秒数
        long zeroT=current/(1000*3600*24)*(1000*3600*24)- TimeZone.getDefault().getRawOffset();
        long endT=zeroT+24*60*60*1000-1;  //今天23点59分59秒的毫秒数

        req.setStartTime(zeroT);
        req.setEndTime(endT);
        req.setOffset(0L);
        req.setSize(10L);
        OapiAttendanceGetleavestatusResponse response =null;
        try {
            response = client.execute(req, token);
            if (response.getErrcode() == 0) {
                return response;
            }else{
                log.info("错误原因:"+response.getErrmsg());
            }
        } catch (ApiException e) {
            log.info("获取token错误:"+e.getErrMsg());
        }
        return response;
    }

    /**
     * 角色====>>>>>获取角色下的员工列表
     * @param roleId
     * @return
     */
    public static OapiRoleSimplelistResponse     getRoleLists(String roleId,String token,int page){
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/role/simplelist");
        OapiRoleSimplelistRequest request = new OapiRoleSimplelistRequest();
        request.setRoleId(Long.parseLong(roleId));
        request.setOffset((long)page);//分页偏移，默认值：0
        request.setSize(200L);//分页大小，默认值：20，最大值200
        OapiRoleSimplelistResponse  response =null;
        try {
            response = client.execute(request, token);
            if (response.getErrcode() == 0) {
                return response;
            }else{
                log.info("错误原因:"+response.getErrmsg());
            }
        } catch (ApiException e) {
            log.info("获取token错误:"+e.getErrMsg());
        }
        return response;
    }

}
