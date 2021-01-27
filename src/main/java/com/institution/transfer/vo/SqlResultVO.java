package com.institution.transfer.vo;

import lombok.Data;

import java.util.List;

@Data
public class SqlResultVO {

    /**
     * SAP源数据ID
     */
    private Integer id;

    /**
     * SAP单位代码
     */
    private String sapCompanyCode="";

    /**
     * SAP单位名称
     */
    private String sapCompanyName="";

    /**
     * 6.0单位代码
     */
    private String sexCompanyCode="";

    /**
     * 6.0单位名称
     */
    private String sexCompanyName="";

    /**
     * 第一审核人
     */
    private String firstReviewer="";

    /**
     * 第二审核人
     */
    private String secondReviewer="";

    /**
     * sap总账（科目代码）
     */
    private String sapGeneralLedger="";

    /**
     * SAP凭证号
     */
    private String sapVoucherCode="";

    /**
     * 6.0凭证类型+凭证号
     */
    private String sexVoucherCodeAndType="";

    /**
     * SAP过账日期
     */
    private String sapPostDate="";

    /**
     * SAP文本
     */
    private String sapTextValue="";

    /**
     * SAP借贷方 S:借---credit；H:贷---debit
     */
    private String sapDebitCredit="";

    /**
     * SAP借贷方 金额
     */
    private String sapCurrencyAmount="";

    /**
     * SAP借方金额
     */
    private String credit="";

    /**
     * SAP贷方金额
     */
    private String debit="";

    /**
     * 6.0科目编码
     */
    private String sexSubjectCode="";

    /**
     * 6.0科目编码不去-的编码
     */
    private String sexSubjectCodeAndBar="";

    /**
     * 6.0科目名称
     */
    private String sexSubjectName="";

    /**
     * 维度信息列表
     */
    private List<DimensionMessageVO> dimensionMessageVOS;

    /**
     * 辅助项值
     */
    private String auxiliaryValue="";

    /**
     * SAP标准WBS元素
     */
    private String sapWbsElement="";

    /**
     * SAP反记账
     */
    private String sapReverseBookkeeping="";

    /**
     * SAP功能范围
     */
    private String sapFunctionalScope="";

    /**
     * SAP制单人
     */
    private String sapTouchingPerson="";

    /**
     * SAP客商
     */
    private String sapMerchants="";

    /**
     * SAP供应商
     */
    private String sapMerchantsSupplier="";

    /**
     * SAP客户
     */
    private String sapMerchantsCustomer="";

    /**
     * 备选参考编号
     */
    private String sapAlternativeReferenceNumber="";
    
}
