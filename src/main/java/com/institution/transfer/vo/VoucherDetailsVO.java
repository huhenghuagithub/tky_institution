package com.institution.transfer.vo;

import lombok.Data;

@Data
public class VoucherDetailsVO {

    /**
     * SAP借贷方 H:贷---debit；S:借---credit
     */
    private String sapDebitCredit;

    /**
     * SAP反记账
     */
    private String sapReverseBookkeeping="";

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
    private String sexSubjectCode;

    /**
     * 6.0科目名称
     */
    private String sexSubjectName;

}
