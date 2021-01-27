package com.institution.transfer.dao.model;

import javax.persistence.*;

@Table(name = "condition_one_source_data")
public class ConditionOneSourceData {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * SAP公司代码
     */
    @Column(name = "sap_company_code")
    private String sapCompanyCode;

    /**
     * SAP公司凭证号
     */
    @Column(name = "sap_voucher_code")
    private String sapVoucherCode;

    /**
     * 唯一标识：SAP公司代码+SAP公司凭证号
     */
    @Column(name = "sap_company_code_voucher_code")
    private String sapCompanyCodeVoucherCode;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取SAP公司代码
     *
     * @return sap_company_code - SAP公司代码
     */
    public String getSapCompanyCode() {
        return sapCompanyCode;
    }

    /**
     * 设置SAP公司代码
     *
     * @param sapCompanyCode SAP公司代码
     */
    public void setSapCompanyCode(String sapCompanyCode) {
        this.sapCompanyCode = sapCompanyCode;
    }

    /**
     * 获取SAP公司凭证号
     *
     * @return sap_voucher_code - SAP公司凭证号
     */
    public String getSapVoucherCode() {
        return sapVoucherCode;
    }

    /**
     * 设置SAP公司凭证号
     *
     * @param sapVoucherCode SAP公司凭证号
     */
    public void setSapVoucherCode(String sapVoucherCode) {
        this.sapVoucherCode = sapVoucherCode;
    }

    /**
     * 获取唯一标识：SAP公司代码+SAP公司凭证号
     *
     * @return sap_company_code_voucher_code - 唯一标识：SAP公司代码+SAP公司凭证号
     */
    public String getSapCompanyCodeVoucherCode() {
        return sapCompanyCodeVoucherCode;
    }

    /**
     * 设置唯一标识：SAP公司代码+SAP公司凭证号
     *
     * @param sapCompanyCodeVoucherCode 唯一标识：SAP公司代码+SAP公司凭证号
     */
    public void setSapCompanyCodeVoucherCode(String sapCompanyCodeVoucherCode) {
        this.sapCompanyCodeVoucherCode = sapCompanyCodeVoucherCode;
    }
}