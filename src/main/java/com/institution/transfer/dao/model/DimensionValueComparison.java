package com.institution.transfer.dao.model;

import javax.persistence.*;

@Table(name = "dimension_value_comparison")
public class DimensionValueComparison {
    /**
     * 序号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * SAP代码
     */
    @Column(name = "sap_code")
    private String sapCode;

    /**
     * SAP名称
     */
    @Column(name = "sap_name")
    private String sapName;

    /**
     * 判断规则说明
     */
    @Column(name = "judgment_rule")
    private String judgmentRule;

    /**
     * 6.0维度编码
     */
    @Column(name = "sex_dimension_code")
    private String sexDimensionCode;

    /**
     * 6.0维度名称
     */
    @Column(name = "sex_dimension_name")
    private String sexDimensionName;

    /**
     * 6.0维度值
     */
    @Column(name = "sex_dimension_value")
    private String sexDimensionValue;

    /**
     * 获取序号
     *
     * @return id - 序号
     */
    public String getId() {
        return id;
    }

    /**
     * 设置序号
     *
     * @param id 序号
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取SAP代码
     *
     * @return sap_code - SAP代码
     */
    public String getSapCode() {
        return sapCode;
    }

    /**
     * 设置SAP代码
     *
     * @param sapCode SAP代码
     */
    public void setSapCode(String sapCode) {
        this.sapCode = sapCode;
    }

    /**
     * 获取SAP名称
     *
     * @return sap_name - SAP名称
     */
    public String getSapName() {
        return sapName;
    }

    /**
     * 设置SAP名称
     *
     * @param sapName SAP名称
     */
    public void setSapName(String sapName) {
        this.sapName = sapName;
    }

    /**
     * 获取判断规则说明
     *
     * @return judgment_rule - 判断规则说明
     */
    public String getJudgmentRule() {
        return judgmentRule;
    }

    /**
     * 设置判断规则说明
     *
     * @param judgmentRule 判断规则说明
     */
    public void setJudgmentRule(String judgmentRule) {
        this.judgmentRule = judgmentRule;
    }

    /**
     * 获取6.0维度编码
     *
     * @return sex_dimension_code - 6.0维度编码
     */
    public String getSexDimensionCode() {
        return sexDimensionCode;
    }

    /**
     * 设置6.0维度编码
     *
     * @param sexDimensionCode 6.0维度编码
     */
    public void setSexDimensionCode(String sexDimensionCode) {
        this.sexDimensionCode = sexDimensionCode;
    }

    /**
     * 获取6.0维度名称
     *
     * @return sex_dimension_name - 6.0维度名称
     */
    public String getSexDimensionName() {
        return sexDimensionName;
    }

    /**
     * 设置6.0维度名称
     *
     * @param sexDimensionName 6.0维度名称
     */
    public void setSexDimensionName(String sexDimensionName) {
        this.sexDimensionName = sexDimensionName;
    }

    /**
     * 获取6.0维度值
     *
     * @return sex_dimension_value - 6.0维度值
     */
    public String getSexDimensionValue() {
        return sexDimensionValue;
    }

    /**
     * 设置6.0维度值
     *
     * @param sexDimensionValue 6.0维度值
     */
    public void setSexDimensionValue(String sexDimensionValue) {
        this.sexDimensionValue = sexDimensionValue;
    }
}