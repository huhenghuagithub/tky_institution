package com.institution.transfer.dao.model;

import javax.persistence.*;

@Table(name = "customer_code_comparison")
public class CustomerCodeComparison {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * SAP客商
     */
    @Column(name = "sap_merchants_code")
    private String sapMerchantsCode;

    /**
     * 6.0客商编码
     */
    @Column(name = "sex_merchants_code")
    private String sexMerchantsCode;

    /**
     * 6.0客商名称
     */
    @Column(name = "sex_merchants_name")
    private String sexMerchantsName;

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
     * 获取SAP客商
     *
     * @return sap_merchants_code - SAP客商
     */
    public String getSapMerchantsCode() {
        return sapMerchantsCode;
    }

    /**
     * 设置SAP客商
     *
     * @param sapMerchantsCode SAP客商
     */
    public void setSapMerchantsCode(String sapMerchantsCode) {
        this.sapMerchantsCode = sapMerchantsCode;
    }

    /**
     * 获取6.0客商编码
     *
     * @return sex_merchants_code - 6.0客商编码
     */
    public String getSexMerchantsCode() {
        return sexMerchantsCode;
    }

    /**
     * 设置6.0客商编码
     *
     * @param sexMerchantsCode 6.0客商编码
     */
    public void setSexMerchantsCode(String sexMerchantsCode) {
        this.sexMerchantsCode = sexMerchantsCode;
    }

    /**
     * 获取6.0客商名称
     *
     * @return sex_merchants_name - 6.0客商名称
     */
    public String getSexMerchantsName() {
        return sexMerchantsName;
    }

    /**
     * 设置6.0客商名称
     *
     * @param sexMerchantsName 6.0客商名称
     */
    public void setSexMerchantsName(String sexMerchantsName) {
        this.sexMerchantsName = sexMerchantsName;
    }
}