package com.institution.transfer.dao.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "company_code_comparison")
public class CompanyCodeComparison {
    /**
     * 6.0单位代码
     */
    @Id
    @Column(name = "sex_company_code")
    private String sexCompanyCode;

    /**
     * 6.0单位名称
     */
    @Column(name = "sex_company_name")
    private String sexCompanyName;

    /**
     * 6.0单位简称
     */
    @Column(name = "sex_short_name")
    private String sexShortName;

    /**
     * 是否虚拟单位
     */
    @Column(name = "bis_virtual")
    private String bisVirtual;

    /**
     * 是否核算主题
     */
    @Column(name = "is_depart")
    private String isDepart;

    /**
     * SAP单位代码
     */
    @Column(name = "sap_company_code")
    private String sapCompanyCode;

    /**
     * SAP单位名称
     */
    @Column(name = "sap_company_name")
    private String sapCompanyName;

    /**
     * 获取6.0单位代码
     *
     * @return sex_company_code - 6.0单位代码
     */
    public String getSexCompanyCode() {
        return sexCompanyCode;
    }

    /**
     * 设置6.0单位代码
     *
     * @param sexCompanyCode 6.0单位代码
     */
    public void setSexCompanyCode(String sexCompanyCode) {
        this.sexCompanyCode = sexCompanyCode;
    }

    /**
     * 获取6.0单位名称
     *
     * @return sex_company_name - 6.0单位名称
     */
    public String getSexCompanyName() {
        return sexCompanyName;
    }

    /**
     * 设置6.0单位名称
     *
     * @param sexCompanyName 6.0单位名称
     */
    public void setSexCompanyName(String sexCompanyName) {
        this.sexCompanyName = sexCompanyName;
    }

    /**
     * 获取6.0单位简称
     *
     * @return sex_short_name - 6.0单位简称
     */
    public String getSexShortName() {
        return sexShortName;
    }

    /**
     * 设置6.0单位简称
     *
     * @param sexShortName 6.0单位简称
     */
    public void setSexShortName(String sexShortName) {
        this.sexShortName = sexShortName;
    }

    /**
     * 获取是否虚拟单位
     *
     * @return bis_virtual - 是否虚拟单位
     */
    public String getBisVirtual() {
        return bisVirtual;
    }

    /**
     * 设置是否虚拟单位
     *
     * @param bisVirtual 是否虚拟单位
     */
    public void setBisVirtual(String bisVirtual) {
        this.bisVirtual = bisVirtual;
    }

    /**
     * 获取是否核算主题
     *
     * @return is_depart - 是否核算主题
     */
    public String getIsDepart() {
        return isDepart;
    }

    /**
     * 设置是否核算主题
     *
     * @param isDepart 是否核算主题
     */
    public void setIsDepart(String isDepart) {
        this.isDepart = isDepart;
    }

    /**
     * 获取SAP单位代码
     *
     * @return sap_company_code - SAP单位代码
     */
    public String getSapCompanyCode() {
        return sapCompanyCode;
    }

    /**
     * 设置SAP单位代码
     *
     * @param sapCompanyCode SAP单位代码
     */
    public void setSapCompanyCode(String sapCompanyCode) {
        this.sapCompanyCode = sapCompanyCode;
    }

    /**
     * 获取SAP单位名称
     *
     * @return sap_company_name - SAP单位名称
     */
    public String getSapCompanyName() {
        return sapCompanyName;
    }

    /**
     * 设置SAP单位名称
     *
     * @param sapCompanyName SAP单位名称
     */
    public void setSapCompanyName(String sapCompanyName) {
        this.sapCompanyName = sapCompanyName;
    }
}