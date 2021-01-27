package com.institution.transfer.dao.model;

import javax.persistence.*;

@Table(name = "subject_dimension_comparison")
public class SubjectDimensionComparison {
    /**
     * 序号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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
     * 获取序号
     *
     * @return id - 序号
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置序号
     *
     * @param id 序号
     */
    public void setId(Integer id) {
        this.id = id;
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
}