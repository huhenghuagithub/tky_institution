package com.institution.transfer.vo;

import lombok.Data;

import java.util.List;

@Data
public class SubjectDimensionVO {

    /**
     * 6.0科目编码
     */
    private String sexSubjectCode;

    /**
     * 6.0科目名称
     */
    private String sexSubjectName;

    /**
     * 6.0维度List
     */
    private DimensionMessageVO dimensionMessageVOS;

}
