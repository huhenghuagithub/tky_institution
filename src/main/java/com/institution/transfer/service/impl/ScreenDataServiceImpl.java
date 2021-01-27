package com.institution.transfer.service.impl;

import com.institution.transfer.service.ScreenDataService;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
public class ScreenDataServiceImpl implements ScreenDataService {

    @Override
    public void downloadFile(HttpServletResponse response, String tableName) throws Exception {

        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        XSSFSheet sheet = xssfWorkbook.createSheet(tableName);
        XSSFRow first = sheet.createRow(0);
        first.createCell(0).setCellValue("产品编码");
        first.createCell(1).setCellValue("产品名称");
        first.createCell(2).setCellValue("产品开始时间");
        first.createCell(3).setCellValue("产品结束时间");

    }

}
