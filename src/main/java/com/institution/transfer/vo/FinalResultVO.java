package com.institution.transfer.vo;

import lombok.Data;

@Data
public class FinalResultVO {

    /**
     *主体账簿
     * 单位名称
     */
    private String sexCompanyName;

    /**
     *日期
     * sap过账日期
     */
    private String date;

    /**
     *凭证号
     */
    private String voucherNo;

    /**
     *摘要
     * 文本
     */
    private String abstractValue;

    /**
     *科目编码
     * sap总账
     */
    private String subjectCode;

    /**
     *科目名称
     */
    private String subjectName;

    /**
     *辅助项
     */
    private String auxiliaryItems;

    /**
     *借方
     */
    private String debit;

    /**
     *贷方
     */
    private String credit;

    /**
     *制单人
     */
    private String touchingPerson;

    /**
     *审核人
     */
    private String examinePerson;

}
