package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.engine.api.facade.OrganizationFacade;
import com.authine.cloudpivot.engine.api.model.organization.DepartmentModel;
import com.authine.cloudpivot.engine.api.model.organization.UserModel;
import com.authine.cloudpivot.web.api.dubbo.DubboConfigService;
import com.authine.cloudpivot.web.api.entity.ScaleConsultDetail;
import com.authine.cloudpivot.web.api.entity.ScaleTestAcore;
import com.authine.cloudpivot.web.api.entity.TeamRecord;
import com.authine.cloudpivot.web.api.mapper.ScaleTestResultMapper;
import com.authine.cloudpivot.web.api.service.ScaleTestResultService;
import com.authine.cloudpivot.web.api.utils.Constant;
import com.esotericsoftware.minlog.Log;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * @author: weiyao
 * @time: 2020/8/1
 * @Description: 心理咨询系统--测评结果
 */
@Service
@Transactional
public class ScaleTestResultServiceImpl implements ScaleTestResultService {

    @Resource
    ScaleTestResultMapper scaleTestResultMapper;

    @Autowired
    private DubboConfigService dubboConfigService;

    //根据分数得到心里结果
    @Override
    public List<Map<String,String>> getResultByScore(String parentId, Integer score) {
        return scaleTestResultMapper.getResultByScore(parentId,score);
    }

    @Override
    public String insertScaleTestAcore(ScaleTestAcore info) {
        OrganizationFacade organizationFacade = dubboConfigService.getOrganizationFacade();
        UserModel user = organizationFacade.getUser(Constant.ADMIN_ID);
        DepartmentModel department = organizationFacade.getDepartment(user.getDepartmentId());
        info.setId( UUID.randomUUID().toString().replace("-",""));
        info.setName("测评详情");
        info.setSequenceStatus(Constant.COMPLETED_STATUS);
        info.setWorkflowInstanceId(null);
        info.setCreater(Constant.ADMIN_ID);
        info.setCreatedDeptId(department.getId());
        info.setOwner(Constant.ADMIN_ID);
        info.setOwnerDeptId(department.getId());
        info.setOwnerDeptQueryCode(department.getQueryCode());
        info.setModifier(Constant.ADMIN_ID);
        //获取钉钉云枢上的Id，前端只能获取钉钉Id
        String userId=scaleTestResultMapper.getIdByddId(info.getUserId());
        if(userId !=null){
            info.setUserId(userId);
        }
        info.setUserId("[{\"id\":\""+info.getUserId()+"\",\"type\":3}]");//测评人
     //   List<ScaleTestAcore> list=scaleTestResultMapper.getScaleTestResultInfo(info); --查询
        List<Map<String,String>> dept=scaleTestResultMapper.getDeptByUserid(userId);//插入部门
        String userdept="";
        if(dept.size()==1){
            userdept="[{\"id\":\""+dept.get(0).get("id")+"\",\"type\":1}]";
        }else if(dept.size()>1){
            userdept="[{\"id\":\""+dept.get(0).get("id")+"\",\"type\":1},"+"{\"id\":\""+dept.get(1).get("id")+"\",\"type\":1}]";
        }
        info.setUserdept(userdept);
        info.setResolved(0);
        if(info.getDanger() !=null && "0".equals(info.getDanger())){
            //正常不需要发布
            info.setResolved(1);
        }
        Integer resc=scaleTestResultMapper.insertScaleTestAcore(info);

//        String id="0e77ee9eb4294b9a895969eaaa741f82";
//        String js=scaleTestResultMapper.getoptionResult(id);

        return resc.toString();
    }

    //返回测评结果
    public List<ScaleTestAcore> getScaleTestResultInfo(ScaleTestAcore info){
        Log.info("===预警测评人钉钉Id:==="+info.getUserId());
        if(StringUtils.isNotEmpty(info.getUserId())){
            String userId=scaleTestResultMapper.getIdByddId(info.getUserId());
            String deptId=scaleTestResultMapper.getDeptIdByKey(userId);//部门Id
            //是否主管，1，是，主管可看本部门测评信息
            //主管角色 roleId:2c90a43e6eb51314016eb65007ee0223
            //查询是不是主管
            String isLeader=scaleTestResultMapper.getIsZhuGuan("2c90a43e6eb51314016eb65007ee0223",userId);
            //2c90a43e73a7e9770173ebb55cde2788:咨询师部门Id (咨询师和 杨队可看到全部 )
            if("2c90a43e73a7e9770173ebb55cde2788".equals(deptId) || "2c90a43e6ed08c91016ed08efa1e003d".equals(userId)){
                //咨询师和 杨队可看到全部
                info.setUserId("");//查询人
                info.setUserdeptId("");//部门
            }else if(StringUtils.isNotEmpty(isLeader)){
                //主管看自己部门
                info.setUserId("");//查询人
                info.setUserdeptId(deptId);//部门
            }else{
                //只能看自己
                info.setUserId(userId);
            }
        }
        info.setResolved(1);//查询已处理的
        return scaleTestResultMapper.getScaleTestResultInfo(info);
    }

    //查询档案
    @Override
    public List<ScaleConsultDetail> getScaleConsultDetail(String deptId, String ddUserId) {
//        if(StringUtils.isNotEmpty(deptId)){
//            deptId=scaleTestResultMapper.getDeptIdByddId(deptId);
//        }
        if(StringUtils.isNotEmpty(ddUserId)){
            ddUserId=scaleTestResultMapper.getIdByddId(ddUserId);
        }
        List<ScaleConsultDetail> list=scaleTestResultMapper.getScaleConsultDetail(deptId,ddUserId);
        if(CollectionUtils.isEmpty(list) && StringUtils.isNotEmpty(ddUserId)){
            list=scaleTestResultMapper.getScaleConsultDetailByPerson(ddUserId);
        }
        if(list !=null && list.size()>0){
            for(ScaleConsultDetail li:list){
                List<ScaleTestAcore> sa=scaleTestResultMapper.getScaleAcoreByUserId(li.getSonsultUser());
                if(sa!=null)
                li.setScaleTestAcoreList(sa);
            }
        }
        return list;
    }

    //返回部门测评档案
    @Override
    public TeamRecord getDeptNumInfo(String deptId) {
//        if(StringUtils.isNotEmpty(deptId)){
//            deptId=scaleTestResultMapper.getDeptIdByddId(deptId);
//        }
        TeamRecord team =new TeamRecord();
        //部门所有人员
        List<String> userList=scaleTestResultMapper.getDeptUserIdList(deptId);
        team.setTeamName(scaleTestResultMapper.getdeptName(deptId));
        //部门总人数
        team.setAllNum(userList.size());

        String danger=null;
        //参与测评人数
      //  team.setCycpNum(scaleTestResultMapper.getcepingNum(deptId,danger));
        team.setWjyj0(scaleTestResultMapper.getcepingNum(deptId,"0")); //健康
        team.setWjyj1(scaleTestResultMapper.getcepingNum(deptId,"1"));//轻度
        team.setWjyj2(scaleTestResultMapper.getcepingNum(deptId,"2"));//中度
        team.setWjyj3(scaleTestResultMapper.getcepingNum(deptId,"3"));//重度
        //危机预警人数
        team.setWjyjNum(team.getWjyj1()+team.getWjyj2()+team.getWjyj3());
        //参与测评总人数
        int cycpnum=scaleTestResultMapper.getcepingNum(deptId,"");
        team.setCycpNum(cycpnum);
        //未参与人数
        team.setWcyNum(userList.size()-team.getCycpNum());
        team.setXlsdNum(scaleTestResultMapper.getshudaoNum(deptId));
        return team;
    }

    @Override
    public List<Map<String, String>> getDeptListByName(String type,String dduserId) {
        //weiyao:19431116101255531 ;2c90a43e6eb51314016eb667507239e9
        //杨队：110041056326188470;2c90a43e6ed08c91016ed08efa1e003d
        //张卓：51594024776243 ；2c90a43e6ed08c91016ed08f834c01cf
        //陆时正:260730135237806323;2c90a43e6f21161d016f31e3cc710669
        boolean admin="260730135237806323".equals(dduserId) ||"110041056326188470".equals(dduserId)||"19431116101255531".equals(dduserId);
       //admin 表示可查看全部部门
        if(admin){
            return getDept(type);
        }else{
            String userid=scaleTestResultMapper.getIdByddId(dduserId);
            //主管角色 roleId:2c90a43e6eb51314016eb65007ee0223
            //查询是不是主管
            String isLeader=scaleTestResultMapper.getIsZhuGuan("2c90a43e6eb51314016eb65007ee0223",userid);
            if(StringUtils.isNotEmpty(isLeader)){
              //  System.out.println(userid+"：是部门主管====");
                return scaleTestResultMapper.getDeptByUserid(userid);
            }
        }

        return null;
    }

    //批量发布
    @Override
    public Integer updateResolved(List<String> ids) {
        return scaleTestResultMapper.updateResolved(ids);
    }

    List<Map<String,String>> getDept(String type){
        List<Map<String,String>> list=null;
        if("1".equals(type)){
        //  支队
            list=scaleTestResultMapper.getDeptListByZD();
        }else if("2".equals(type)){
            //  大队
            list=scaleTestResultMapper.getDeptListByDD();
        }else if("3".equals(type)){
            //  大队
            list=scaleTestResultMapper.getDeptListByName("站");
        }else if("4".equals(type)){
            //  班
            list=scaleTestResultMapper.getDeptListByName("班");
        }
        return list;
    }
}
