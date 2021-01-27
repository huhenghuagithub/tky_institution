package com.institution.transfer.dao.model;

import javax.persistence.*;

@Table(name = "subject_code_comparison")
public class SubjectCodeComparison {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * SAP科目编码
     */
    @Column(name = "sap_subject_code")
    private String sapSubjectCode;

    /**
     * SAP科目名称
     */
    @Column(name = "sap_subject_name")
    private String sapSubjectName;

    /**
     * SAP功能范围代码
     */
    @Column(name = "sap_functional_scope_code")
    private String sapFunctionalScopeCode;

    /**
     * SAP功能范围名称
     */
    @Column(name = "sap_functional_scope_name")
    private String sapFunctionalScopeName;

    /**
     * 6.0科目编码
     */
    @Column(name = "sex_subject_code")
    private String sexSubjectCode;

    /**
     * 6.0科目名称
     */
    @Column(name = "sex_subject_name")
    private String sexSubjectName;

    /**
     * 获取ID
     *
     * @return id - ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置ID
     *
     * @param id ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取SAP科目编码
     *
     * @return sap_subject_code - SAP科目编码
     */
    public String getSapSubjectCode() {
        return sapSubjectCode;
    }

    /**
     * 设置SAP科目编码
     *
     * @param sapSubjectCode SAP科目编码
     */
    public void setSapSubjectCode(String sapSubjectCode) {
        this.sapSubjectCode = sapSubjectCode;
    }

    /**
     * 获取SAP科目名称
     *
     * @return sap_subject_name - SAP科目名称
     */
    public String getSapSubjectName() {
        return sapSubjectName;
    }

    /**
     * 设置SAP科目名称
     *
     * @param sapSubjectName SAP科目名称
     */
    public void setSapSubjectName(String sapSubjectName) {
        this.sapSubjectName = sapSubjectName;
    }

    /**
     * 获取6.0科目编码
     *
     * @return sex_subject_code - 6.0科目编码
     */
    public String getSexSubjectCode() {
        return sexSubjectCode;
    }

    /**
     * 设置6.0科目编码
     *
     * @param sexSubjectCode 6.0科目编码
     */
    public void setSexSubjectCode(String sexSubjectCode) {
        this.sexSubjectCode = sexSubjectCode;
    }

    /**
     * 获取6.0科目名称
     *
     * @return sex_subject_name - 6.0科目名称
     */
    public String getSexSubjectName() {
        return sexSubjectName;
    }

    /**
     * 设置6.0科目名称
     *
     * @param sexSubjectName 6.0科目名称
     */
    public void setSexSubjectName(String sexSubjectName) {
        this.sexSubjectName = sexSubjectName;
    }

    public String getSapFunctionalScopeCode() {
        return sapFunctionalScopeCode;
    }

    public void setSapFunctionalScopeCode(String sapFunctionalScopeCode) {
        this.sapFunctionalScopeCode = sapFunctionalScopeCode;
    }

    public String getSapFunctionalScopeName() {
        return sapFunctionalScopeName;
    }

    public void setSapFunctionalScopeName(String sapFunctionalScopeName) {
        this.sapFunctionalScopeName = sapFunctionalScopeName;
    }
}