package com.institution.transfer.service.impl;

import com.institution.transfer.common.model.Result;
import com.institution.transfer.constants.DimensionCorrespondenceConstants;
import com.institution.transfer.dao.mapper.SapSourceDataMapper;
import com.institution.transfer.dao.mapper.XtSubjectInfoMapper;
import com.institution.transfer.dao.model.XtSubjectInfo;
import com.institution.transfer.service.DataMigrationService;
import com.institution.transfer.utils.ReadExcelUtil;
import com.institution.transfer.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class DataMigrationServiceImpl implements DataMigrationService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private SapSourceDataMapper sapSourceDataMapper;

    @Resource
    private XtSubjectInfoMapper xtSubjectInfoMapper;

    @Resource
    private ThreadPoolTaskExecutor executor;

    @Resource
    private ReadExcelUtil readExcelUtil;

    @Override
    public Result selectSqlResultVO(Integer start, Integer end) {
//        List<SqlResultVO> sqlResultVOS = sapSourceDataMapper.selectSqlResultVOByStartAndEnd(0, 0,"1000");
        List<SqlResultVO> sqlResultVOS = new ArrayList<>();
        //设置6.0科目编码、名称
        //getSexSubjectCodeAndName(sqlResultVOS);
        /*getAuxiliaryValue(sqlResultVOS);
        //设置借贷金额正负数
        getCreditDebit(sqlResultVOS);
        getVoucherType(sqlResultVOS);*/
        return new Result(sqlResultVOS);
    }

    @Override
    public void downloadFile(HttpServletResponse response, String tableName, String all, String screen) throws Exception {

        OutputStream outputStream =null;
        //压缩输出流
        ZipOutputStream zipOutputStream =null;
        try {
            outputStream = response.getOutputStream();
            //压缩输出流
            zipOutputStream = new ZipOutputStream(outputStream);
            //查出来6.0和sap系统的单位编码对应关系。
            List<Map<String, String>> list = sapSourceDataMapper.selectCompanyCodes(tableName);
            for (int i=0;i<list.size();i++) {
                //todo 为什么5590特殊？
                if (null==list.get(i)||"5590".equals(list.get(i).get("sapCompanyCode"))){
                    continue;
                }
                //创建6.0凭证工作簿
                SXSSFWorkbook workbook = new SXSSFWorkbook();
                // 打开压缩功能 防止占用过多磁盘
                workbook.setCompressTempFiles(true);
                Sheet sheet = workbook.createSheet(list.get(i).get("sexCompanyName"));
                // 创建一个工作表
                Row row = sheet.createRow(0);
                Cell cell0 = row.createCell(0, CellType.STRING);
                cell0.setCellValue("主体账簿");
                Cell cell1 = row.createCell(1, CellType.STRING);
                cell1.setCellValue("日期");
                Cell cell2 = row.createCell(2, CellType.STRING);
                cell2.setCellValue("凭证号");
                Cell cell3 = row.createCell(3, CellType.STRING);
                cell3.setCellValue("摘要");
                Cell cell4 = row.createCell(4, CellType.STRING);
                cell4.setCellValue("科目编码");
                Cell cell5 = row.createCell(5, CellType.STRING);
                cell5.setCellValue("科目名称");
                Cell cell6 = row.createCell(6, CellType.STRING);
                cell6.setCellValue("辅助项");
                Cell cell7 = row.createCell(7, CellType.STRING);
                cell7.setCellValue("借方");
                Cell cell8 = row.createCell(8, CellType.STRING);
                cell8.setCellValue("贷方");
                Cell cell9 = row.createCell(9, CellType.STRING);
                cell9.setCellValue("制单人");
                Cell cell10 = row.createCell(10, CellType.STRING);
                cell10.setCellValue("审核人");
                /*Cell cell11 = row.createCell(11, CellType.STRING);
                cell11.setCellValue("SAP公司编码");
                Cell cell12 = row.createCell(12, CellType.STRING);
                cell12.setCellValue("6.0公司编码");
                Cell cell13 = row.createCell(13, CellType.STRING);
                cell13.setCellValue("SAP凭证号");
                Cell cell14 = row.createCell(14, CellType.STRING);
                cell14.setCellValue("客户");
                Cell cell15 = row.createCell(15, CellType.STRING);
                cell15.setCellValue("供应商");
                Cell cell16 = row.createCell(16, CellType.STRING);
                cell16.setCellValue("SAP科目编码");
                Cell cell17 = row.createCell(17, CellType.STRING);
                cell17.setCellValue("唯一标识");*/

                //创建SAP与6.0凭证号对应关系工作簿
                SXSSFWorkbook workbookVoucherCode = new SXSSFWorkbook();
                Sheet sheetVoucherCode = workbookVoucherCode.createSheet(list.get(i).get("sexCompanyName"));
                Row rowVoucherCode = sheetVoucherCode.createRow(0);
                Cell cellVoucherCode0 = rowVoucherCode.createCell(0, CellType.STRING);
                cellVoucherCode0.setCellValue("SAP公司编码");
                Cell cellVoucherCode1 = rowVoucherCode.createCell(1, CellType.STRING);
                cellVoucherCode1.setCellValue("SAP公司名称");
                Cell cellVoucherCode2 = rowVoucherCode.createCell(2, CellType.STRING);
                cellVoucherCode2.setCellValue("6.0公司编码");
                Cell cellVoucherCode3 = rowVoucherCode.createCell(3, CellType.STRING);
                cellVoucherCode3.setCellValue("6.0公司名称");
                Cell cellVoucherCode4 = rowVoucherCode.createCell(4, CellType.STRING);
                cellVoucherCode4.setCellValue("SAP凭证号");
                Cell cellVoucherCode5 = rowVoucherCode.createCell(5, CellType.STRING);
                cellVoucherCode5.setCellValue("6.0凭证号");
                Cell cellVoucherCode6 = rowVoucherCode.createCell(6, CellType.STRING);
                cellVoucherCode6.setCellValue("SAP过账日期");

                try {
                    List<SqlResultVO> sqlResultVOS;
                    sqlResultVOS=sapSourceDataMapper.selectSqlResultVOByStartAndEnd(0, 0,list.get(i).get("sapCompanyCode"),tableName);
                    //源数据针对凭证号编码
                    //List<Map<String, Object>> voucherMapList = sapSourceDataMapper.selectVoucherType(list.get(i).get("sapCompanyCode"),tableName);
                    //设置借贷金额正负数
                    getCreditDebit(sqlResultVOS);
                    //key:sap公司代码_sap凭证号_sap过账时间;value:凭证数据的借贷列表
                    Map<String,List<VoucherDetailsVO>> map=new LinkedHashMap<>();
                    //循环数据将数据配置成key:sap公司代码_sap凭证号_sap过账时间;value:凭证数据的借贷列表用来判断借贷类型
                    for (SqlResultVO voucherMap : sqlResultVOS) {
                        if (!"1".equals(all)){
                            if ("1".equals(screen)){
                                if (DimensionCorrespondenceConstants.conditionOneDataSet.contains(voucherMap.getSapCompanyCode()+voucherMap.getSapVoucherCode())){
                                    continue;
                                }
                            }else if ("0".equals(screen)){
                                if (!DimensionCorrespondenceConstants.conditionOneDataSet.contains(voucherMap.getSapCompanyCode()+voucherMap.getSapVoucherCode())){
                                    continue;
                                }
                            }
                        }

                        //SapGeneralLedger： sap总账 大致思路：包含总账有功能范围拼1000，否则不做处理。
                        String sexSubjectCode="";
                        if (DimensionCorrespondenceConstants.functionalScopeSubject.contains(voucherMap.getSapGeneralLedger())){
                            if(StringUtils.isEmpty(voucherMap.getSapFunctionalScope())){
                                //FIXME 查询6.0对应科目编码时需要对应功能范围的SAP科目，当源数据没有功能范围时，默认在sap总账后面1000
                                sexSubjectCode=DimensionCorrespondenceConstants.subjectAndFunctionalScopeMap.get(
                                        voucherMap.getSapGeneralLedger()+"1000");
                            }else {
                                sexSubjectCode=DimensionCorrespondenceConstants.subjectAndFunctionalScopeMap.get(
                                        voucherMap.getSapGeneralLedger()+voucherMap.getSapFunctionalScope());
                            }
                        }else {
                            sexSubjectCode=DimensionCorrespondenceConstants.subjectAndFunctionalScopeMap.get(voucherMap.getSapGeneralLedger());
                        }


                        StringBuffer key=new StringBuffer();
                        //sap公司代码_sap凭证号_sap过账时间
                        key.append(voucherMap.getSapCompanyCode()).append("_")
                                .append(voucherMap.getSapVoucherCode()).append("_")
                                .append(voucherMap.getSapPostDate());
                        List<VoucherDetailsVO> list1 = map.get(key.toString());
                        if (null ==  list1 ||list1.size()==0){
                            list1=new ArrayList<>();
                            VoucherDetailsVO voucherDetailsVO = new VoucherDetailsVO();
                            voucherDetailsVO.setSapDebitCredit(voucherMap.getSapDebitCredit());
                            voucherDetailsVO.setSapReverseBookkeeping(voucherMap.getSapReverseBookkeeping());
                            voucherDetailsVO.setCredit(voucherMap.getCredit());
                            voucherDetailsVO.setDebit(voucherMap.getDebit());
                            voucherDetailsVO.setSexSubjectCode(sexSubjectCode);
                            list1.add(voucherDetailsVO);
                        }else {
                            list1 = map.get(key.toString());
                            VoucherDetailsVO voucherDetailsVO = new VoucherDetailsVO();
                            voucherDetailsVO.setSapDebitCredit(voucherMap.getSapDebitCredit());
                            voucherDetailsVO.setSapReverseBookkeeping(voucherMap.getSapReverseBookkeeping());
                            voucherDetailsVO.setCredit(voucherMap.getCredit());
                            voucherDetailsVO.setDebit(voucherMap.getDebit());
                            voucherDetailsVO.setSexSubjectCode(sexSubjectCode);
                            list1.add(voucherDetailsVO);
                        }
                        map.put(key.toString(),list1);
                    }
                    //循环数据给SAP凭证编6.0的凭证号
                    for (Map.Entry<String, List<VoucherDetailsVO>> voucherVO : map.entrySet()) {
                        //SAP公司代码
                        String sapCompanyCode=voucherVO.getKey().substring(0,voucherVO.getKey().indexOf("_"));
                        //SAP凭证号
                        String sapVoucherCode=voucherVO.getKey().substring(voucherVO.getKey().indexOf("_")+1
                                ,voucherVO.getKey().lastIndexOf("_"));
                        //SAP过账日期
                        String sapPostDate=voucherVO.getKey().substring(voucherVO.getKey().lastIndexOf("_")+1);
                        //借方科目集合
                        Set<String> creditSet=new HashSet<>();
                        //贷方科目集合
                        Set<String> debitSet=new HashSet<>();
                        //过账日期月份
                        String monthString= null;
                        try {
                            monthString = sapPostDate.substring(0,7);
                        } catch (Exception e) {
                            logger.error("{}发生异常====={}",voucherVO,sapPostDate);
                        }
                        for (VoucherDetailsVO voucherDetailsVO : voucherVO.getValue()) {
                            if (!StringUtils.isEmpty(voucherDetailsVO.getSexSubjectCode())&&voucherDetailsVO.getSexSubjectCode().length()>=4){

                                StringBuffer stringBuffer=new StringBuffer();
                                stringBuffer.append(sapCompanyCode).append("_").append(sapVoucherCode).append("_")
                                        .append(voucherDetailsVO.getSexSubjectCode());

                                if ("S".equals(voucherDetailsVO.getSapDebitCredit())){
                                    creditSet.add(voucherDetailsVO.getSexSubjectCode().substring(0,4));
                                }else if ("H".equals(voucherDetailsVO.getSapDebitCredit())){
                                    debitSet.add(voucherDetailsVO.getSexSubjectCode().substring(0,4));
                                }
                                /*if (StringUtils.isEmpty(voucherDetailsVO.getCredit())){
                                    Long aLong = Long.valueOf(voucherDetailsVO.getDebit().replaceAll("\\.", ""));
                                    if (null==DimensionCorrespondenceConstants.oneVoucherAndOneSubjectAndZeroMap.get(stringBuffer.toString())){
                                        DimensionCorrespondenceConstants.oneVoucherAndOneSubjectAndZeroMap.put(stringBuffer.toString(),aLong);
                                    }else {
                                        DimensionCorrespondenceConstants.oneVoucherAndOneSubjectAndZeroMap.put(stringBuffer.toString()
                                                ,DimensionCorrespondenceConstants.oneVoucherAndOneSubjectAndZeroMap.get(stringBuffer.toString())+aLong);
                                    }
                                }else {
                                    Long aLong = Long.valueOf(voucherDetailsVO.getCredit().replaceAll("\\.", ""));
                                    if (null==DimensionCorrespondenceConstants.oneVoucherAndOneSubjectAndZeroMap.get(stringBuffer.toString())){
                                        DimensionCorrespondenceConstants.oneVoucherAndOneSubjectAndZeroMap.put(stringBuffer.toString(),aLong);
                                    }else {
                                        DimensionCorrespondenceConstants.oneVoucherAndOneSubjectAndZeroMap.put(stringBuffer.toString()
                                                ,DimensionCorrespondenceConstants.oneVoucherAndOneSubjectAndZeroMap.get(stringBuffer.toString())+aLong);
                                    }
                                }*/


                            }
                        }
                        String voucherType = getVoucherType(creditSet,debitSet);
                        StringBuffer keyStringBuffer = new StringBuffer();
                        String key = keyStringBuffer.append(sapCompanyCode).append("_")
                                .append(monthString).append("_").append(sapVoucherCode).toString();
                        if (StringUtils.isEmpty(DimensionCorrespondenceConstants.sexVoucherType.get(sapCompanyCode+monthString+voucherType))){
                            DimensionCorrespondenceConstants.sexVoucherType.put(sapCompanyCode+monthString+voucherType,"0001");
                            DimensionCorrespondenceConstants.sexVoucherCodeAndType.put(key,voucherType+"-0001");
                        }else {
                            String s = DimensionCorrespondenceConstants.sexVoucherCodeAndType.get(key);
                            if (StringUtils.isEmpty(s)){
                                int nums = Integer.valueOf(DimensionCorrespondenceConstants.sexVoucherType.get(sapCompanyCode+monthString+voucherType)) + 1;
                                int length = (nums + "").length();
                                StringBuffer stringBuffer=new StringBuffer();
                                for (int j = 0; j < 4-length; j++) {
                                    stringBuffer.append("0");
                                }
                                stringBuffer.append(nums);
                                DimensionCorrespondenceConstants.sexVoucherType.put(sapCompanyCode+monthString+voucherType,stringBuffer.toString());
                                DimensionCorrespondenceConstants.sexVoucherCodeAndType.put(key,voucherType+"-"+stringBuffer.toString());
                            }
                        }

                    }

                    /*for (Map.Entry<String, Long> stringBigDecimalEntry : DimensionCorrespondenceConstants.oneVoucherAndOneSubjectAndZeroMap.entrySet()) {
                        if (0L!=stringBigDecimalEntry.getValue()){
                            DimensionCorrespondenceConstants.oneVoucherAndOneSubjectAndZeroMap.remove(stringBigDecimalEntry.getKey());
                        }
                    }*/
                    //设置6.0科目编码、名称
                    getSexSubjectCodeAndName(sqlResultVOS);
                    /*//设置辅助项值
                    getAuxiliaryValue(sqlResultVOS);*/
                    //获取凭证号
                    getVoucherType(sqlResultVOS);
                    int j=1;

                    //SAP和6.0凭证号对应关系对象列表
                    Set<VoucherCodeMappingVO> voucherCodeMappingVOS=new HashSet<>();

                    //循环写入6.0凭证数据
                    for (SqlResultVO sqlResultVO : sqlResultVOS) {
                        if (!"1".equals(all)){
                            if ("1".equals(screen)){
                                if (DimensionCorrespondenceConstants.conditionOneDataSet.contains(sqlResultVO.getSapCompanyCode()+sqlResultVO.getSapVoucherCode())){
                                    continue;
                                }
                            }else if ("0".equals(screen)){
                                if (!DimensionCorrespondenceConstants.conditionOneDataSet.contains(sqlResultVO.getSapCompanyCode()+sqlResultVO.getSapVoucherCode())){
                                    continue;
                                }
                            }
                        }
                        VoucherCodeMappingVO voucherCodeMappingVO=new VoucherCodeMappingVO();
                        BeanUtils.copyProperties(sqlResultVO,voucherCodeMappingVO);
                        voucherCodeMappingVOS.add(voucherCodeMappingVO);

                        DimensionCorrespondenceConstants.screenVoucherNums++;

                        Row rowJ = sheet.createRow(j);
                        //主体账簿---6.0公司编码
                        Cell cellJ0 = rowJ.createCell(0, CellType.STRING);
                        cellJ0.setCellValue(sqlResultVO.getSexCompanyName());
                        //日期----SAP过账日期
                        Cell cellJ1 = rowJ.createCell(1, CellType.STRING);
                        cellJ1.setCellValue(sqlResultVO.getSapPostDate());
                        //凭证号
                        Cell cellJ2 = rowJ.createCell(2, CellType.STRING);
                        cellJ2.setCellValue(sqlResultVO.getSexVoucherCodeAndType());
                        //摘要---文本
                        Cell cellJ3 = rowJ.createCell(3, CellType.STRING);
                        //计算摘要为空的数据条数
                        if (StringUtils.isEmpty(sqlResultVO.getSapTextValue())){
                            DimensionCorrespondenceConstants.textValueNums=DimensionCorrespondenceConstants.textValueNums+1;
                        }
                        //FIXME 2021-01-19对凭证摘要修改为凭证摘要\备选参考编号，若备选参考编号为空则拼接SAP凭证号
                        StringBuffer textValue=new StringBuffer();
                        textValue.append(StringUtils.isEmpty(sqlResultVO.getSapTextValue())
                                        ?sqlResultVO.getSexCompanyName()+sqlResultVO.getSapPostDate().substring(0,4)+"凭证" :sqlResultVO.getSapTextValue())
                                .append("\\")
                                .append(StringUtils.isEmpty(sqlResultVO.getSapAlternativeReferenceNumber())
                                        ?sqlResultVO.getSapVoucherCode():sqlResultVO.getSapAlternativeReferenceNumber());
                        cellJ3.setCellValue(textValue.toString());
                        //科目编码---6.0科目编码
                        Cell cellJ4 = rowJ.createCell(4, CellType.STRING);
                        cellJ4.setCellValue(sqlResultVO.getSexSubjectCode());
                        //科目名称
                        Cell cellJ5 = rowJ.createCell(5, CellType.STRING);
                        cellJ5.setCellValue(sqlResultVO.getSexSubjectCode()+"\\"+sqlResultVO.getSexSubjectName());
                        //辅助项---维度值
                        Cell cellJ6 = rowJ.createCell(6, CellType.STRING);
                        cellJ6.setCellValue(sqlResultVO.getAuxiliaryValue());
                        //借方
                        Cell cellJ7 = rowJ.createCell(7);
                        //贷方
                        Cell cellJ8 = rowJ.createCell(8);
                        if (StringUtils.isNotEmpty(sqlResultVO.getCredit())){
                            cellJ7.setCellValue(Double.valueOf(sqlResultVO.getCredit()));
                        }
                        if (StringUtils.isNotEmpty(sqlResultVO.getDebit())){
                            cellJ8.setCellValue(Double.valueOf(sqlResultVO.getDebit()));
                        }
                        //制单人----SAP全名
                        Cell cellJ9 = rowJ.createCell(9, CellType.STRING);
                        cellJ9.setCellValue(sqlResultVO.getSapTouchingPerson());
                        //审核人
                        Cell cellJ10 = rowJ.createCell(10, CellType.STRING);
                        cellJ10.setCellValue(sqlResultVO.getSapTouchingPerson().equals(sqlResultVO.getFirstReviewer())?
                                sqlResultVO.getSecondReviewer():sqlResultVO.getFirstReviewer());
                        if ("6220".equals(sqlResultVO.getSapCompanyCode())){
                            if ("2020-06-30".compareTo(sqlResultVO.getSapPostDate())>=0){
                                //韩楚波  乔敬东
                                //审核人
                                cellJ10.setCellValue(sqlResultVO.getSapTouchingPerson().equals("韩楚波")?
                                        "乔敬东":"韩楚波");
                            }else {
                                //韩映彩  杨富中
                                //审核人
                                cellJ10.setCellValue(sqlResultVO.getSapTouchingPerson().equals("韩映彩")?
                                        "杨富中":"韩映彩");
                            }
                        }
                        /*//SAP公司代码
                        Cell cellJ11 = rowJ.createCell(11, CellType.STRING);
                        cellJ11.setCellValue(sqlResultVO.getSapCompanyCode());
                        //6.0公司代码
                        Cell cellJ12 = rowJ.createCell(12, CellType.STRING);
                        cellJ12.setCellValue(sqlResultVO.getSexCompanyCode());
                        //SAP凭证号
                        Cell cellJ13 = rowJ.createCell(13, CellType.STRING);
                        cellJ13.setCellValue(sqlResultVO.getSapVoucherCode());
                        //客户
                        Cell cellJ14 = rowJ.createCell(14, CellType.STRING);
                        cellJ14.setCellValue(sqlResultVO.getSapMerchantsCustomer());
                        //供应商
                        Cell cellJ15 = rowJ.createCell(15, CellType.STRING);
                        cellJ15.setCellValue(sqlResultVO.getSapMerchantsSupplier());
                        //SAP科目编码
                        Cell cellJ16 = rowJ.createCell(16, CellType.STRING);
                        cellJ16.setCellValue(sqlResultVO.getSapGeneralLedger());
                        //唯一标识
                        Cell cellJ17 = rowJ.createCell(17, CellType.STRING);
                        cellJ17.setCellValue(sqlResultVO.getId());*/
                        j++;
                    }

                    //将写凭证号对应关系数据入excel文档中--区分公司生成不同的文件
                    int jVoucherCode=1;
                    for (VoucherCodeMappingVO voucherCodeMappingVO : voucherCodeMappingVOS) {
                        Row rowJVoucherCode = sheetVoucherCode.createRow(jVoucherCode);
                        //SAP公司编码
                        Cell cellJVoucherCode0 = rowJVoucherCode.createCell(0, CellType.STRING);
                        cellJVoucherCode0.setCellValue(voucherCodeMappingVO.getSapCompanyCode());
                        //SAP公司名称
                        Cell cellJVoucherCode1 = rowJVoucherCode.createCell(1, CellType.STRING);
                        cellJVoucherCode1.setCellValue(voucherCodeMappingVO.getSapCompanyName());
                        //6.0公司编码
                        Cell cellJVoucherCode2 = rowJVoucherCode.createCell(2, CellType.STRING);
                        cellJVoucherCode2.setCellValue(voucherCodeMappingVO.getSexCompanyCode());
                        //6.0公司名称
                        Cell cellJVoucherCode3 = rowJVoucherCode.createCell(3, CellType.STRING);
                        cellJVoucherCode3.setCellValue(voucherCodeMappingVO.getSexCompanyName());
                        //SAP凭证号
                        Cell cellJVoucherCode4 = rowJVoucherCode.createCell(4, CellType.STRING);
                        cellJVoucherCode4.setCellValue(voucherCodeMappingVO.getSapVoucherCode());
                        //6.0凭证号
                        Cell cellJVoucherCode5 = rowJVoucherCode.createCell(5, CellType.STRING);
                        cellJVoucherCode5.setCellValue(voucherCodeMappingVO.getSexVoucherCodeAndType());
                        //SAP过账日期
                        Cell cellJVoucherCode6 = rowJVoucherCode.createCell(6, CellType.STRING);
                        cellJVoucherCode6.setCellValue(voucherCodeMappingVO.getSapPostDate());
                        jVoucherCode++;
                    }

                }catch (Exception e){
                    logger.error("线程{}发生异常",Thread.currentThread().getName(),e);
                }
                String encodeName = URLEncoder.encode("凭证数据.zip", StandardCharsets.UTF_8.toString());
                response.setContentType("application/octet-stream; charset=utf-8");
                response.setHeader("Content-Disposition", "attachment; filename=" + encodeName);

                //重点开始,创建压缩文件
                ZipEntry z = new ZipEntry(list.get(i).get("sapCompanyCode")+list.get(i).get("sexCompanyName")
                        +list.get(i).get("sexCompanyCode") + ".xlsx");
                zipOutputStream.putNextEntry(z);
                //写入一个压缩文件
                workbook.write(zipOutputStream);

//                重点开始,创建压缩文件
                ZipEntry zVoucherCode = new ZipEntry(list.get(i).get("sapCompanyCode")+list.get(i).get("sexCompanyName")
                       +list.get(i).get("sexCompanyCode")+")-SAP凭证号与6.0凭证号对应关系" + ".xlsx");
                zipOutputStream.putNextEntry(zVoucherCode);
//                写入一个压缩文件
                workbookVoucherCode.write(zipOutputStream);
            }
            zipOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //注意关闭顺序，否则可能文件错误
            if (zipOutputStream != null) {
                zipOutputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

    @Override
    public void downloadMerchantsFile(HttpServletResponse response) throws Exception {
        OutputStream outputStream =null;
        //压缩输出流
        ZipOutputStream zipOutputStream =null;
        try {
            outputStream = response.getOutputStream();
            //压缩输出流
            zipOutputStream = new ZipOutputStream(outputStream);
            //创建工作簿
            SXSSFWorkbook workbookConcise = new SXSSFWorkbook();
            // 打开压缩功能 防止占用过多磁盘
            workbookConcise.setCompressTempFiles(true);
            Sheet sheetConcise = workbookConcise.createSheet("账簿客商对应");
            // 创建一个工作表
            Row rowConcise = sheetConcise.createRow(0);
            Cell cellConcise0 = rowConcise.createCell(0, CellType.STRING);
            cellConcise0.setCellValue("主体账簿");
            Cell cellConcise1 = rowConcise.createCell(1, CellType.STRING);
            cellConcise1.setCellValue("辅助项");

            int i=1;
            for (String s : DimensionCorrespondenceConstants.sapMerchantsAndSexCompanyNameMap) {
                String[] split = s.split("###");
                Row rowJConcise = sheetConcise.createRow(i);
                //主体账簿---6.0公司编码
                Cell cellJConcise0 = rowJConcise.createCell(0, CellType.STRING);
                cellJConcise0.setCellValue(split[1]);
                //辅助项---维度值
                Cell cellJConcise1 = rowJConcise.createCell(1, CellType.STRING);
                cellJConcise1.setCellValue(split[0]);
                i++;
            }

            String encodeName = URLEncoder.encode("账簿客商对应数据.zip", StandardCharsets.UTF_8.toString());
            response.setContentType("application/octet-stream; charset=utf-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + encodeName);

            //重点开始,创建压缩文件
            ZipEntry z = new ZipEntry("账簿客商对应数据.xlsx");
            zipOutputStream.putNextEntry(z);
            //写入一个压缩文件
            workbookConcise.write(zipOutputStream);

            zipOutputStream.flush();

        }catch (Exception e){
            e.printStackTrace();
        } finally {
            //注意关闭顺序，否则可能文件错误
            if (zipOutputStream != null) {
                zipOutputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

    @Override
    public void changeMerchantsName(HttpServletResponse response, MultipartFile multipartFile) throws Exception {
        List<List<String>> lists =new ArrayList<>();
        try {
           lists = readExcelUtil.readExcel(multipartFile);
        } catch (IOException e) {
            logger.error("读取上传文件发生异常",e);
            return;
        }
        for (List<String> list : lists) {
            //list为excle中其中一行的数据
            if (list.get(6).contains("【客商")){
                //index是字符串第一次出现的索引下标。
                String merchantsName=list.get(6).substring(list.get(6).indexOf("：")+1,list.get(6).indexOf("】"));
                if (DimensionCorrespondenceConstants.merchantsConversionMap.containsKey(merchantsName)){
                    list.set(6,list.get(6).replace(merchantsName
                            ,DimensionCorrespondenceConstants.merchantsConversionMap.get(merchantsName)));
                }
            }
        }

        //创建6.0凭证工作簿
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        // 打开压缩功能 防止占用过多磁盘
        workbook.setCompressTempFiles(true);
        Sheet sheet = workbook.createSheet(lists.get(0).get(0));

        // 创建一个工作表
        Row row = sheet.createRow(0);
        Cell cell0 = row.createCell(0, CellType.STRING);
        cell0.setCellValue("主体账簿");
        Cell cell1 = row.createCell(1, CellType.STRING);
        cell1.setCellValue("日期");
        Cell cell2 = row.createCell(2, CellType.STRING);
        cell2.setCellValue("凭证号");
        Cell cell3 = row.createCell(3, CellType.STRING);
        cell3.setCellValue("摘要");
        Cell cell4 = row.createCell(4, CellType.STRING);
        cell4.setCellValue("科目编码");
        Cell cell5 = row.createCell(5, CellType.STRING);
        cell5.setCellValue("科目名称");
        Cell cell6 = row.createCell(6, CellType.STRING);
        cell6.setCellValue("辅助项");
        Cell cell7 = row.createCell(7, CellType.STRING);
        cell7.setCellValue("借方");
        Cell cell8 = row.createCell(8, CellType.STRING);
        cell8.setCellValue("贷方");
        Cell cell9 = row.createCell(9, CellType.STRING);
        cell9.setCellValue("制单人");
        Cell cell10 = row.createCell(10, CellType.STRING);
        cell10.setCellValue("审核人");

        int j=1;
        for (List<String> list : lists) {
            Row rowJ = sheet.createRow(j);
            //主体账簿---6.0公司名称
            Cell cellJ0 = rowJ.createCell(0, CellType.STRING);
            cellJ0.setCellValue(list.get(0));
            //日期----SAP过账日期
            Cell cellJ1 = rowJ.createCell(1, CellType.STRING);
            cellJ1.setCellValue(list.get(1));
            //凭证号
            Cell cellJ2 = rowJ.createCell(2, CellType.STRING);
            cellJ2.setCellValue(list.get(2));
            //摘要---文本
            Cell cellJ3 = rowJ.createCell(3, CellType.STRING);
            cellJ3.setCellValue(list.get(3));
            //科目编码---6.0科目编码
            Cell cellJ4 = rowJ.createCell(4, CellType.STRING);
            cellJ4.setCellValue(list.get(4));
            //科目名称
            Cell cellJ5 = rowJ.createCell(5, CellType.STRING);
            cellJ5.setCellValue(list.get(5));
            //辅助项---维度值
            Cell cellJ6 = rowJ.createCell(6, CellType.STRING);
            cellJ6.setCellValue(list.get(6));
            //借方
            Cell cellJ7 = rowJ.createCell(7);
            cellJ7.setCellValue(list.get(7));
            //贷方
            Cell cellJ8 = rowJ.createCell(8);
            cellJ8.setCellValue(list.get(8));
            //制单人----SAP全名
            Cell cellJ9 = rowJ.createCell(9, CellType.STRING);
            cellJ9.setCellValue(list.get(9));
            //审核人
            Cell cellJ10 = rowJ.createCell(10, CellType.STRING);
            cellJ10.setCellValue(list.get(10));

        }
    }

    @Override
    public void getSubjectFullPath() throws Exception {
        List<XtSubjectInfo> xtSubjectInfos = xtSubjectInfoMapper.selectAll();

        for (XtSubjectInfo xtSubjectInfo : xtSubjectInfos) {
            executor.submit(()->{
                StringBuffer xtSubjectFullPath = new StringBuffer();
                XtSubjectInfo queryXtSubjectInfo = new XtSubjectInfo();
                String xtSubjectCode = xtSubjectInfo.getXtSubjectCode();
                String[] split = xtSubjectCode.split("-");
                StringBuffer stringBufferCode=new StringBuffer();
                stringBufferCode.append(split[0]);

                queryXtSubjectInfo.setXtSubjectCode(stringBufferCode.toString());
                XtSubjectInfo resultXtSubjectInfo = xtSubjectInfoMapper.selectOne(queryXtSubjectInfo);
                xtSubjectFullPath.append(resultXtSubjectInfo.getXtSubjectName());

                for (int i = 1; i < split.length; i++) {
                    stringBufferCode.append("-").append(split[i]);
                    queryXtSubjectInfo.setXtSubjectCode(stringBufferCode.toString());
                    resultXtSubjectInfo = xtSubjectInfoMapper.selectOne(queryXtSubjectInfo);
                    xtSubjectFullPath.append("\\").append(resultXtSubjectInfo.getXtSubjectName());
                }

                queryXtSubjectInfo.setId(xtSubjectInfo.getId());
                queryXtSubjectInfo.setXtFullPath(xtSubjectFullPath.toString());
                xtSubjectInfoMapper.updateByPrimaryKeySelective(queryXtSubjectInfo);
            });
        }

    }

    //设置6.0科目编码、名称
    private Boolean getSexSubjectCodeAndName(List<SqlResultVO> sqlResultVOS){
        for (SqlResultVO sqlResultVO : sqlResultVOS) {
            String sexSubjectCode="";
            if (DimensionCorrespondenceConstants.functionalScopeSubject.contains(sqlResultVO.getSapGeneralLedger())){
                if(StringUtils.isEmpty(sqlResultVO.getSapFunctionalScope())){
                    //FIXME 查询6.0对应科目编码时需要对应功能范围的SAP科目，当源数据没有功能范围时，默认1000
                    sexSubjectCode=DimensionCorrespondenceConstants.subjectAndFunctionalScopeMap.get(
                            sqlResultVO.getSapGeneralLedger()+"1000");
                }else {
                    sexSubjectCode=DimensionCorrespondenceConstants.subjectAndFunctionalScopeMap.get(
                            sqlResultVO.getSapGeneralLedger()+sqlResultVO.getSapFunctionalScope());
                }
            }else {
                sexSubjectCode=DimensionCorrespondenceConstants.subjectAndFunctionalScopeMap.get(sqlResultVO.getSapGeneralLedger());
            }
            sqlResultVO.setSexSubjectCodeAndBar(sexSubjectCode);
            if (StringUtils.isEmpty(sexSubjectCode)){
                sqlResultVO.setSexSubjectCode("");
            }else {
                StringBuffer stringBuffer = new StringBuffer();
                String[] split = sexSubjectCode.split("-");
                for (int i = 0; i < split.length; i++) {
                    if (split[i].length()==1){
                        stringBuffer.append("0");
                    }
                    stringBuffer.append(split[i]);
                }
                sqlResultVO.setSexSubjectCode(stringBuffer.toString());
            }
            sqlResultVO.setSexSubjectName(DimensionCorrespondenceConstants.sexSubjectFullPathMap.get(sexSubjectCode));
            List<DimensionMessageVO> dimensionMessageVOS = DimensionCorrespondenceConstants.subjectDimensionMap.get(sexSubjectCode);
            sqlResultVO.setDimensionMessageVOS(dimensionMessageVOS);

            //set辅助项
            if (null == sqlResultVO.getDimensionMessageVOS() || sqlResultVO.getDimensionMessageVOS().isEmpty()){
                sqlResultVO.setAuxiliaryValue("");
                continue;
            }
            StringBuffer stringBuffer=new StringBuffer();
            for (DimensionMessageVO dimensionMessageVO : sqlResultVO.getDimensionMessageVOS()) {
                //FIXME 当辅助项为银行账号时，即辅助编码为GA17时，不拼接辅助项值
                if ("GA17".equals(dimensionMessageVO.getSexDimensionCode())){
                    continue;
                }
                stringBuffer.append("【").append(dimensionMessageVO.getSexDimensionName()).append("：");
                switch (dimensionMessageVO.getSexDimensionCode()){
                    case "GA01"://默认值
                    case "GA02"://默认值
                    case "GA07" :
                    case "GA10"://默认值
                    case "GA22"://默认值
                    case "CU"://默认值
                        stringBuffer.append(DimensionCorrespondenceConstants.dimensionCorrespondenceMap
                                .get(dimensionMessageVO.getSexDimensionCode()));
                        break;
                    case "GA04":
                        if (DimensionCorrespondenceConstants.dimensionValueGA04List.contains(sqlResultVO.getSapGeneralLedger())){
                            //科目代码
                            StringBuffer keyGA04=new StringBuffer();
                            keyGA04.append(sqlResultVO.getSapGeneralLedger())
                                    .append(dimensionMessageVO.getSexDimensionCode());
                            stringBuffer.append(StringUtils.isEmpty(DimensionCorrespondenceConstants.dimensionCorrespondenceMap.get(keyGA04.toString()))
                                    ? "其他" : DimensionCorrespondenceConstants.dimensionCorrespondenceMap.get(keyGA04.toString()));
                        }else {
                            ////WBS编码
                            if (!StringUtils.isEmpty(sqlResultVO.getSapWbsElement()) && sqlResultVO.getSapWbsElement().length()>8){
                                StringBuffer keyGA04=new StringBuffer();
                                keyGA04.append(sqlResultVO.getSapWbsElement().substring(0,1)).append(sqlResultVO.getSapWbsElement().substring(9))
                                        .append(dimensionMessageVO.getSexDimensionCode());
                                stringBuffer.append(StringUtils.isEmpty(DimensionCorrespondenceConstants.dimensionCorrespondenceMap.get(keyGA04.toString()))
                                        ? "其他" : DimensionCorrespondenceConstants.dimensionCorrespondenceMap.get(keyGA04.toString()));
                            }else {
                                stringBuffer.append("其他");
                            }
                        }
                        break;
                    case "GA05"://科目编码
                    case "GA0805"://科目编码
                    case "GA0807"://科目编码
                        StringBuffer keyGA05=new StringBuffer();
                        keyGA05.append(sqlResultVO.getSapGeneralLedger())
                                .append(dimensionMessageVO.getSexDimensionCode());
                        stringBuffer.append(DimensionCorrespondenceConstants.dimensionCorrespondenceMap
                                .get(keyGA05.toString()));
                        break;
                    case "GA09"://根据单位代码
                        StringBuffer keyGA09=new StringBuffer();
                        keyGA09.append(sqlResultVO.getSapCompanyCode())
                                .append(dimensionMessageVO.getSexDimensionCode());
                        stringBuffer.append(DimensionCorrespondenceConstants.dimensionCorrespondenceMap
                                .get(keyGA09.toString()));
                        break;
                    case "GA17"://科目编码加单位编码
                        StringBuffer keyGA17=new StringBuffer();
                        keyGA17.append(sqlResultVO.getSapGeneralLedger()).append(sqlResultVO.getSapCompanyCode())
                                .append(dimensionMessageVO.getSexDimensionCode());
                        stringBuffer.append(DimensionCorrespondenceConstants.dimensionCorrespondenceMap
                                .get(keyGA17.toString()));
                        break;
                    case "GSKS":
                        //先取客户编码，如果为空去取供应商编码，如果还为空就去取6.0对应的本公司名称
                        String sexMerchantsName=StringUtils.isEmpty(sqlResultVO.getSapMerchantsCustomer())
                                ? (
                                StringUtils.isEmpty(sqlResultVO.getSapMerchantsSupplier())
                                        ? sqlResultVO.getSexCompanyName()
                                        : DimensionCorrespondenceConstants.supplierCodeComparisonMap.get(sqlResultVO.getSapMerchantsSupplier())
                        )
                                : DimensionCorrespondenceConstants.customerCodeComparisonMap.get(sqlResultVO.getSapMerchantsCustomer());
                        DimensionCorrespondenceConstants.sapMerchantsAndSexCompanyNameMap.add(sexMerchantsName+"###"+sqlResultVO.getSexCompanyName());
                        stringBuffer.append(sexMerchantsName);
                        break;
                    default:
                        break;
                }
                stringBuffer.append("】");
            }
            sqlResultVO.setAuxiliaryValue(stringBuffer.toString());
        }
        return true;
    }

    //set辅助项
    private void getAuxiliaryValue(List<SqlResultVO> sqlResultVOS){
        for (SqlResultVO sqlResultVO : sqlResultVOS) {

        }
    }

    //set借贷方金额正负数
    private void getCreditDebit(List<SqlResultVO> sqlResultVOS){
        for (SqlResultVO sqlResultVO : sqlResultVOS) {
            //SAP借贷方 S:借---credit；H:贷---debit
            if ("S".equals(sqlResultVO.getSapDebitCredit())){
                sqlResultVO.setCredit(sqlResultVO.getSapCurrencyAmount());
                //FIXME 注释掉判断条件
                /*if (StringUtils.isEmpty(sqlResultVO.getSapReverseBookkeeping())){
                    //借贷标识=借方且反记账标识为空，则为借方 金额为正数
                    sqlResultVO.setCredit(sqlResultVO.getSapCurrencyAmount());
                }else if ("X".equals(sqlResultVO.getSapReverseBookkeeping())){
                    //借贷标识=借方且反记账标识为X，则为贷方，金额为负数
                    sqlResultVO.setDebit("-"+sqlResultVO.getSapCurrencyAmount());
                }*/
            }
            if ("H".equals(sqlResultVO.getSapDebitCredit())){
                sqlResultVO.setDebit(sqlResultVO.getSapCurrencyAmount());
                //FIXME 注释掉判断条件
                /*if (StringUtils.isEmpty(sqlResultVO.getSapReverseBookkeeping())){
                    //借贷标识=贷方且反记账标识为空，则为贷方 金额为正数
                    sqlResultVO.setDebit(sqlResultVO.getSapCurrencyAmount());
                }else if ("X".equals(sqlResultVO.getSapReverseBookkeeping())){
                    //借贷标识=贷方且反记账标识为X，则为借方，金额为负数
                    sqlResultVO.setCredit("-"+sqlResultVO.getSapCurrencyAmount());
                }*/
            }
        }
    }

    //凭证类型
    private void getVoucherType(List<SqlResultVO> sqlResultVOS){
        for (SqlResultVO sqlResultVO : sqlResultVOS) {
            //过账日期月份
            String monthString=sqlResultVO.getSapPostDate().substring(0,7);
            StringBuffer keyStringBuffer = new StringBuffer();
            String key =keyStringBuffer.append(sqlResultVO.getSapCompanyCode()).append("_")
                    .append(monthString).append("_").append(sqlResultVO.getSapVoucherCode()).toString();
            sqlResultVO.setSexVoucherCodeAndType(DimensionCorrespondenceConstants.sexVoucherCodeAndType
                    .getOrDefault(key,""));
        }
    }


    private String getVoucherType(Set<String> creditSet, Set<String> debitSet){

        //条件五：凭证借方既有1001和1002时，凭证类型为银收
        if (creditSet.contains("1001") && creditSet.contains("1002")){
            return "银收";
        }
        //条件六：凭证贷方既有1001和1002时，凭证类型为银付
        if (debitSet.contains("1001") && debitSet.contains("1002")){
            return "银付";
        }
        //条件一：凭证借方为1001*且贷方不为货币资金科目时，凭证类型为现收
        if (creditSet.contains("1001")){
            Boolean isMonetaryCapital=false;
            for (String s : debitSet) {
                if (DimensionCorrespondenceConstants.monetaryCapitalSubject.contains(s)){
                    isMonetaryCapital=true;
                    break;
                }
            }
            if (!isMonetaryCapital){
                return "现收";
            }
        }
        //条件二：凭证借方为1002且贷方不为货币资金科目时，凭证类型为银收
        if (creditSet.contains("1002")){
            Boolean isMonetaryCapital=false;
            for (String s : debitSet) {
                if (DimensionCorrespondenceConstants.monetaryCapitalSubject.contains(s)){
                    isMonetaryCapital=true;
                    break;
                }
            }
            if (!isMonetaryCapital){
                return "银收";
            }
        }
        //条件三：凭证贷方为1001时，凭证类型为现付
        if (debitSet.contains("1001")){
            return "现付";
        }
        //条件四：凭证贷方为1002时，凭证类型为银付
        if (debitSet.contains("1002")){
            return "银付";
        }
        //条件七：凭证借贷方均不为货币资金科目时，凭证类型为转账
        Boolean isMonetaryCapitalAndDebit=false;
        Boolean isMonetaryCapitalAndCredit=false;
        for (String debit : debitSet) {
            if (DimensionCorrespondenceConstants.monetaryCapitalSubject.contains(debit)) {
                isMonetaryCapitalAndDebit = true;
                break;
            }
        }
        for (String credit : creditSet) {
            if (DimensionCorrespondenceConstants.monetaryCapitalSubject.contains(credit)){
                isMonetaryCapitalAndCredit=true;
                break;
            }
        }
        if (!isMonetaryCapitalAndDebit && !isMonetaryCapitalAndCredit){
            return "转账";
        }
        return "转账";
    }


}






/*CountDownLatch countDownLatch=new CountDownLatch(5);
        for (int i = 1; i <= 5; i++) {
            Integer start;
            Integer end;
            if (i !=5){
                start=(i-1)*nums+1;
                end=i*nums;
            }else {
                start=(i-1)*nums+1;
                end=i *nums+mod;
            }
            executor.submit(()->{
                try {
                    List<SqlResultVO> sqlResultVOS;
                    logger.error("线程{}查询表格数据{}到{}",Thread.currentThread().getName(),start,end);
                    sqlResultVOS=sapSourceDataMapper.selectSqlResultVOByStartAndEnd(start, end);
                    //设置辅助项值
                    getAuxiliaryValue(sqlResultVOS);
                    //设置借贷金额正负数
                    getCreditDebit(sqlResultVOS);
                    //获取凭证号
                    getVoucherType(sqlResultVOS);
                    // 创建一个工作表
                    Sheet sheet = workbook.createSheet(start+"到"+end);
                    Row row = sheet.createRow(0);
                    Cell cell0 = row.createCell(0, CellType.STRING);
                    cell0.setCellValue("主体账簿");
                    Cell cell1 = row.createCell(1, CellType.STRING);
                    cell1.setCellValue("日期");
                    Cell cell2 = row.createCell(2, CellType.STRING);
                    cell2.setCellValue("凭证号");
                    Cell cell3 = row.createCell(3, CellType.STRING);
                    cell3.setCellValue("摘要");
                    Cell cell4 = row.createCell(4, CellType.STRING);
                    cell4.setCellValue("科目编码");
                    Cell cell5 = row.createCell(5, CellType.STRING);
                    cell5.setCellValue("科目名称");
                    Cell cell6 = row.createCell(6, CellType.STRING);
                    cell6.setCellValue("辅助项");
                    Cell cell7 = row.createCell(7, CellType.STRING);
                    cell7.setCellValue("借方");
                    Cell cell8 = row.createCell(8, CellType.STRING);
                    cell8.setCellValue("贷方");
                    Cell cell9 = row.createCell(9, CellType.STRING);
                    cell9.setCellValue("制单人");
                    Cell cell10 = row.createCell(10, CellType.STRING);
                    cell10.setCellValue("审核人");
                    int j=1;
                    for (SqlResultVO sqlResultVO : sqlResultVOS) {
                        Row rowJ = sheet.createRow(j);
                        Cell cellJ0 = rowJ.createCell(0, CellType.STRING);
                        cellJ0.setCellValue(sqlResultVO.getSexCompanyName());
                        Cell cellJ1 = rowJ.createCell(1, CellType.STRING);
                        cellJ1.setCellValue(sqlResultVO.getSapPostDate());
                        //TODO 凭证号
                        Cell cellJ2 = rowJ.createCell(2, CellType.STRING);
                        cellJ2.setCellValue(sqlResultVO.getSexVoucherCodeAndType());
                        Cell cellJ3 = rowJ.createCell(3, CellType.STRING);
                        cellJ3.setCellValue(sqlResultVO.getSapTextValue());
                        Cell cellJ4 = rowJ.createCell(4, CellType.STRING);
                        cellJ4.setCellValue(sqlResultVO.getSexSubjectCode());
                        //TODO 科目名称
                        Cell cellJ5 = rowJ.createCell(5, CellType.STRING);
                        cellJ5.setCellValue(sqlResultVO.getSexSubjectCode()+"\\"+sqlResultVO.getSexSubjectName());
                        Cell cellJ6 = rowJ.createCell(6, CellType.STRING);
                        cellJ6.setCellValue(sqlResultVO.getAuxiliaryValue());
                        Cell cellJ7 = rowJ.createCell(7, CellType.STRING);
                        cellJ7.setCellValue(StringUtils.isEmpty(sqlResultVO.getCredit())?"":sqlResultVO.getCredit());
                        Cell cellJ8 = rowJ.createCell(8, CellType.STRING);
                        cellJ8.setCellValue(StringUtils.isEmpty(sqlResultVO.getDebit())?"":sqlResultVO.getDebit());
                        Cell cellJ9 = rowJ.createCell(9, CellType.STRING);
                        cellJ9.setCellValue(sqlResultVO.getSapTouchingPerson());
                        //TODO 审核人
                        Cell cellJ10 = rowJ.createCell(10, CellType.STRING);
                        cellJ10.setCellValue("审核人");
                        j++;
                    }
                }catch (Exception e){
                    logger.error("线程{}发生异常",Thread.currentThread().getName(),e);
                }finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();*/
