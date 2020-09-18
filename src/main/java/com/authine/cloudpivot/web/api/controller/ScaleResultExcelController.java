package com.authine.cloudpivot.web.api.controller;


import com.authine.cloudpivot.engine.api.model.organization.UserModel;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.entity.CePingUserInfo;
import com.authine.cloudpivot.web.api.mapper.CePingResultMapper;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: weiyao
 * @Date: 2020-09-9
 * @Description: 导出武汉测评结果 excel
 */
@RestController
@RequestMapping("/controller/exportExcel")
public class ScaleResultExcelController extends BaseController{

    @Resource
    CePingResultMapper cePingResultMapper;

    //导出大队站队人数统计
    @GetMapping("/getProvidentExcel")
    public ResponseResult<String> getProvidentExcel(HttpServletResponse response) throws IOException {
      //  String welfareHandler="六";
        //总人数
        int count=cePingResultMapper.getNum();
        //总人次
        int countAll=cePingResultMapper.getNumAll();
        //全部部门
       // List<CePingUserInfo> userList= cePingResultMapper.getInfo();
        //大队集合
        List<CePingUserInfo> daList=cePingResultMapper.getDeptInfo("大队");
        //中队集合
        List<CePingUserInfo> zhongList=cePingResultMapper.getDeptInfo("站");
        //班队集合
        List<CePingUserInfo>  banList=cePingResultMapper.getDeptBanInfo("班");
      //  List<CePingUserInfo> zhongList2=new ArrayList<>(zhongList);
        for(int i=0;i<banList.size();i++){
            //将班队加入到大队
            String parentId=banList.get(i).getParentId();
//            if("2c90a43e6eb51314016eb662a2ea2f99".equals(parentId))
//                System.out.println("====================常青站下级部门开始");
            boolean is=true;
            for(int z=0;z<zhongList.size();z++){
                if(parentId.equals(zhongList.get(z).getDeptId())){
            //        System.out.println("班队："+banList.get(i).getName()+" 中队："+zhongList.get(z).getName());
                    is=false;
                    zhongList.get(z).setCount(zhongList.get(z).getCount()+banList.get(i).getCount());
                    zhongList.get(z).setPersonCount(zhongList.get(z).getPersonCount()+banList.get(i).getPersonCount());
                }

            }
            if(is){
                //没有中队
                CePingUserInfo dept=cePingResultMapper.getParentDept(parentId);
                dept.setCount(banList.get(i).getCount());
                dept.setPersonCount(banList.get(i).getPersonCount());
                zhongList.add(dept);
           //     System.out.println("添加中队："+dept.getName());
            }
        }

        //专职消防员放到上级
        for(int zz=0;zz<zhongList.size();zz++){
            String zzName=zhongList.get(zz).getName();
            if(zzName.contains("专职")){
                //专职消防员放到上级
                CePingUserInfo zzInfo=zhongList.get(zz);
                String parentId=zzInfo.getParentId();
                zhongList.remove(zz);
                zz--;
                boolean iszz=true;
                for(int kk=0;kk<zhongList.size();kk++){
                    if(parentId.equals(zhongList.get(kk).getDeptId())){
                        iszz=false;
                        zhongList.get(kk).setCount(zzInfo.getCount()+zhongList.get(kk).getCount());
                        zhongList.get(kk).setPersonCount(zzInfo.getPersonCount()+zhongList.get(kk).getPersonCount());
                        System.out.println("专职："+zzInfo.getName()+" 插入上级："+zhongList.get(kk).getName());
                    }
                }
                if(iszz){
                    //没有中队
                    CePingUserInfo dept=cePingResultMapper.getParentDept(parentId);
                    dept.setCount(zzInfo.getCount());
                    dept.setPersonCount(zzInfo.getPersonCount());
                    zhongList.add(dept);
                    System.out.println("专职新加中队："+dept.getName());
                }
            }
        }

        //更新部门人数和下级部门人数 09-17
        for(int i=0;i<zhongList.size();i++){

            String deptid=zhongList.get(i).getDeptId();
            if("2c90a43e6eb51314016eb662a2ea2f99".equals(deptid)){
                System.out.println("changqing添加中队：");
            }
            Integer deptuser=cePingResultMapper.getDeptUserCountByDeptid(deptid);
            Integer parentuser=0;
            //0 有子部门 1 无子部门
            Integer leaf=zhongList.get(i).getLeaf();
            if(0==leaf){
              //  String parentId=zhongList.get(i).getParentId();
                parentuser=cePingResultMapper.getDeptUserCountByParentId(deptid);
            }
            zhongList.get(i).setPersonCount(deptuser==null?0:deptuser+(parentuser==null?0:parentuser));
        }

        int ii,jj;
        for(ii=0;ii<zhongList.size()-1;ii++)
        {
            for(jj=0;jj<zhongList.size()-1-ii;jj++)
            {
                if(zhongList.get(jj).getCount()<zhongList.get(jj+1).getCount())
                {
                    int temp=zhongList.get(jj).getCount();
                    int ptemp=zhongList.get(jj).getPersonCount();
                    String name=zhongList.get(jj).getName();
                    zhongList.get(jj).setCount( zhongList.get(jj+1).getCount());
                    zhongList.get(jj).setPersonCount( zhongList.get(jj+1).getPersonCount());
                    zhongList.get(jj).setName(zhongList.get(jj+1).getName());
                    zhongList.get(jj+1).setCount(temp);
                    zhongList.get(jj+1).setPersonCount(ptemp);
                    zhongList.get(jj+1).setName(name);
                }
            }
        }


        //大队
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("大队测评结果");
        sheet.setDefaultRowHeight((short)(480));
        sheet.setDefaultColumnWidth(15);
        XSSFRow row = sheet.createRow(0);
        creatRow1ByG(row);
        //表头字体
        XSSFCellStyle cellStyle = setFontStyle(workbook);
    //    row.getCell(0).setCellStyle(cellStyle);
        for (int i = 0; i < row.getLastCellNum(); i++) {
            row.getCell(i).setCellStyle(cellStyle);
       }

        if(daList !=null && daList.size()>0){
            String userName="";
            int i=1;
            for (CePingUserInfo po:daList){
                XSSFRow rowi = sheet.createRow(i);
                //序号
                rowi.createCell(0).setCellValue(i);
                //大队名称
                rowi.createCell(1).setCellValue(StringUtils.trimToEmpty(po.getName()));
                //数量
                rowi.createCell(2).setCellValue(po.getCount());
                //总人数
                rowi.createCell(3).setCellValue(po.getPersonCount());
                float v = (float) ((new BigDecimal((float) po.getCount() / po.getPersonCount()).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue())*100);
                String str = v+"%";
                //占比
                rowi.createCell(4).setCellValue(str);
                 i++;
            }
        }

        //中队
        XSSFSheet sheet2 = workbook.createSheet("站队测评结果");
        sheet2.setDefaultRowHeight((short)(480));
        sheet2.setDefaultColumnWidth(15);
        XSSFRow row2 = sheet2.createRow(0);
        creatRow1ByG(row2);
        //表头字体
      //  XSSFCellStyle cellStyle = setFontStyle(workbook);
        //    row.getCell(0).setCellStyle(cellStyle);
        for (int i = 0; i < row2.getLastCellNum(); i++) {
            row2.getCell(i).setCellStyle(cellStyle);
        }

        if(zhongList !=null && zhongList.size()>0){
            int i=1;
            for (CePingUserInfo po:zhongList){
                XSSFRow rowi = sheet2.createRow(i);
                //序号
                rowi.createCell(0).setCellValue(i);
                //大队名称
                rowi.createCell(1).setCellValue(StringUtils.trimToEmpty(po.getName()));
                //数量
                rowi.createCell(2).setCellValue(po.getCount());
                //总人数
                rowi.createCell(3).setCellValue(po.getPersonCount());
                float v = (float) ((new BigDecimal((float) po.getCount() / po.getPersonCount()).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue())*100);
                String str = v+"%";
                //占比
                rowi.createCell(4).setCellValue(str);
                i++;
            }
        }
        String fname=new String(("测评人数"+count+"_总人次"+countAll).getBytes("gbk"), "iso8859-1");
        response.addHeader("Content-Disposition", "attachment;filename=" +fname+ new String("测评结果统计.xlsx".getBytes("gbk"), "iso8859-1"));
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
        response.setContentType("application/vnd.ms-excel;charset=gb2312");
        workbook.write(bufferedOutputStream);
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
        return this.getOkResponseResult("success", fname);
    }



    //第一行
    private void creatRow1ByG(XSSFRow row) {
        row.createCell(0).setCellValue("序号");
        row.createCell(1).setCellValue("大队名称");
        row.createCell(2).setCellValue("数量");
        row.createCell(3).setCellValue("总人数");
        row.createCell(4).setCellValue("占比");
    }

    //社保第一行
    private void creatRow1ByS(XSSFRow row) {
        row.createCell(0).setCellValue("序号");
        row.createCell(1).setCellValue("中队名称");
        row.createCell(2).setCellValue("数量");
        row.createCell(3).setCellValue("总人数");
        row.createCell(4).setCellValue("占比");
    }

    //设置表头字体
    private XSSFCellStyle setFontStyle(XSSFWorkbook workbook){
        XSSFFont f  = workbook.createFont();
        f.setFontHeightInPoints((short) 12);//字号
        f.setBold(true);//加粗

        XSSFCellStyle cellStyle = workbook.createCellStyle();
        //居中
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        //设置字体
        cellStyle.setFont(f);
        return cellStyle;
    }

    private Float floatCheckNull(Float f) {
        return f == null ? 0F : f;
    }
}
