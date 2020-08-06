package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.engine.api.facade.OrganizationFacade;
import com.authine.cloudpivot.engine.api.model.organization.DepartmentModel;
import com.authine.cloudpivot.engine.api.model.organization.UserModel;
import com.authine.cloudpivot.web.api.dubbo.DubboConfigService;
import com.authine.cloudpivot.web.api.entity.ScaleTestAcore;
import com.authine.cloudpivot.web.api.mapper.ScaleTestResultMapper;
import com.authine.cloudpivot.web.api.service.ScaleTestResultService;
import com.authine.cloudpivot.web.api.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Integer resc=scaleTestResultMapper.insertScaleTestAcore(info);

        String id="0e77ee9eb4294b9a895969eaaa741f82";
        String js=scaleTestResultMapper.getoptionResult(id);

        return resc.toString();
    }
}
