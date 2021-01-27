package com.institution.transfer.controller;

import com.institution.transfer.common.model.Result;
import com.institution.transfer.constants.DimensionCorrespondenceConstants;
import com.institution.transfer.service.ScreenDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

@Api(value = "删选数据")
@RestController
@RequestMapping("/screenData")
public class ScreenDataController {

    @Resource
    private ScreenDataService screenDataService;

    @ApiOperation(value = "textValueNums", notes = "textValueNums")
    @GetMapping("/textValueNums")
    public Result textValueNums() {
        return new Result(DimensionCorrespondenceConstants.textValueNums);
    }

}
