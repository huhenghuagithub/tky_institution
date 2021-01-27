package com.institution.transfer.controller;

import com.alibaba.fastjson.JSONObject;
import com.institution.transfer.constants.DimensionCorrespondenceConstants;
import com.institution.transfer.common.model.Result;
import com.institution.transfer.dao.mapper.SapSourceDataMapper;
import com.institution.transfer.dao.mapper.SubjectDimensionComparisonMapper;
import com.institution.transfer.service.DataMigrationService;
import com.institution.transfer.vo.SubjectDimensionVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@Api(value = "读数据")
@RestController
@RequestMapping("/migration")
public class DataMigrationController {

    @Resource
    private SapSourceDataMapper sapSourceDataMapper;

    @Resource
    private SubjectDimensionComparisonMapper subjectDimensionComparisonMapper;

    @Resource
    private DataMigrationService dataMigrationService;

    @ApiOperation(value = "textValueNums", notes = "textValueNums")
    @GetMapping("/textValueNums")
    public Result textValueNums() {
        return new Result(DimensionCorrespondenceConstants.textValueNums);
    }

    @ApiOperation(value = "统计单位-凭证类型-凭证号", notes = "统计单位-凭证类型-凭证号")
    @GetMapping("/sexVoucherType")
    public Result sexVoucherType() {
        return new Result(DimensionCorrespondenceConstants.sexVoucherType);
    }

    @ApiOperation(value = "需要拆分的凭证条数", notes = "需要拆分的凭证条数")
    @GetMapping("/screenVoucherNums")
    public Result screenVoucherNums() {
        return new Result(DimensionCorrespondenceConstants.screenVoucherNums);
    }

    @ApiOperation(value = "oneVoucherAndOneSubjectAndZeroMap", notes = "oneVoucherAndOneSubjectAndZeroMap")
    @GetMapping("/oneVoucherAndOneSubjectAndZeroMap")
    public Result oneVoucherAndOneSubjectAndZeroMap() {
        return new Result(DimensionCorrespondenceConstants.oneVoucherAndOneSubjectAndZeroMap);
    }

    @ApiOperation(value = "测试查询数据SQL", notes = "测试查询数据SQL")
    @GetMapping("/testSelectSql")
    public Result testSelectSql(@RequestParam("start") Integer start,
                                @RequestParam("end") Integer end) {
        return dataMigrationService.selectSqlResultVO(start, end);
    }

    @ApiOperation(value = "生成文件", notes = "生成文件")
    @GetMapping("/downloadFile")
    public void downloadFile(HttpServletResponse response,@RequestParam("tableName") String tableName
            ,@RequestParam("all") String all,@RequestParam("screen") String screen) throws Exception {
        //todo 为什么是all和screen
        dataMigrationService.downloadFile(response,tableName,all,screen);
    }

    @ApiOperation(value = "生成文件账簿客商对应文件", notes = "生成文件账簿客商对应文件")
    @PostMapping("/downloadMerchantsFile")
    public void downloadMerchantsFile(HttpServletResponse response) throws Exception {
        dataMigrationService.downloadMerchantsFile(response);
    }

    @ApiOperation(value = "转换凭证数据内错误的客商名称", notes = "转换凭证数据内错误的客商名称")
    @PostMapping("/changeMerchantsName")
    public void changeMerchantsName(HttpServletResponse response,@RequestParam("multipartFile")  MultipartFile multipartFile) throws Exception {
        dataMigrationService.changeMerchantsName(response,multipartFile);
    }

    @ApiOperation(value = "生成目录全路径", notes = "生成目录全路径")
    @GetMapping("/subjectFullPath")
    public void getSubjectFullPath() throws Exception {
        dataMigrationService.getSubjectFullPath();
    }

    public static void main(String[] args) {
        String s="【客商：呼张铁路客运专线有限责任公司】";
        System.out.println(s.substring(s.indexOf("：")+1,s.indexOf("】")));
        System.out.println(s.replace("太中银铁路公司筹备组","太中银铁路公司筹备组123"));
        System.out.println("2020-06-30".compareTo("2020-05-30")>=0);
    }

}
