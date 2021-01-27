package com.institution.transfer.dao.model;

import javax.persistence.*;

@Table(name = "xt_subject_info")
public class XtSubjectInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 科目编码
     */
    @Column(name = "xt_subject_code")
    private String xtSubjectCode;

    /**
     * 科目名称
     */
    @Column(name = "xt_subject_name")
    private String xtSubjectName;

    /**
     * 科目性质
     */
    @Column(name = "xt_subject_nature")
    private String xtSubjectNature;

    /**
     * 余额方向
     */
    @Column(name = "xt_balance_direction")
    private String xtBalanceDirection;

    /**
     * 科目类别
     */
    @Column(name = "xt_subject_area")
    private String xtSubjectArea;

    /**
     * 属性1
     */
    @Column(name = "xt_property1")
    private String xtProperty1;

    /**
     * 属性2
     */
    @Column(name = "xt_property2")
    private String xtProperty2;

    /**
     * 父级id
     */
    @Column(name = "xt_subject_parent_code")
    private String xtSubjectParentCode;

    /**
     * 全路径
     */
    @Column(name = "xt_full_path")
    private String xtFullPath;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取科目编码
     *
     * @return xt_subject_code - 科目编码
     */
    public String getXtSubjectCode() {
        return xtSubjectCode;
    }

    /**
     * 设置科目编码
     *
     * @param xtSubjectCode 科目编码
     */
    public void setXtSubjectCode(String xtSubjectCode) {
        this.xtSubjectCode = xtSubjectCode;
    }

    /**
     * 获取科目名称
     *
     * @return xt_subject_name - 科目名称
     */
    public String getXtSubjectName() {
        return xtSubjectName;
    }

    /**
     * 设置科目名称
     *
     * @param xtSubjectName 科目名称
     */
    public void setXtSubjectName(String xtSubjectName) {
        this.xtSubjectName = xtSubjectName;
    }

    /**
     * 获取科目性质
     *
     * @return xt_subject_nature - 科目性质
     */
    public String getXtSubjectNature() {
        return xtSubjectNature;
    }

    /**
     * 设置科目性质
     *
     * @param xtSubjectNature 科目性质
     */
    public void setXtSubjectNature(String xtSubjectNature) {
        this.xtSubjectNature = xtSubjectNature;
    }

    /**
     * 获取余额方向
     *
     * @return xt_balance_direction - 余额方向
     */
    public String getXtBalanceDirection() {
        return xtBalanceDirection;
    }

    /**
     * 设置余额方向
     *
     * @param xtBalanceDirection 余额方向
     */
    public void setXtBalanceDirection(String xtBalanceDirection) {
        this.xtBalanceDirection = xtBalanceDirection;
    }

    /**
     * 获取科目类别
     *
     * @return xt_subject_area - 科目类别
     */
    public String getXtSubjectArea() {
        return xtSubjectArea;
    }

    /**
     * 设置科目类别
     *
     * @param xtSubjectArea 科目类别
     */
    public void setXtSubjectArea(String xtSubjectArea) {
        this.xtSubjectArea = xtSubjectArea;
    }

    /**
     * 获取属性1
     *
     * @return xt_property1 - 属性1
     */
    public String getXtProperty1() {
        return xtProperty1;
    }

    /**
     * 设置属性1
     *
     * @param xtProperty1 属性1
     */
    public void setXtProperty1(String xtProperty1) {
        this.xtProperty1 = xtProperty1;
    }

    /**
     * 获取属性2
     *
     * @return xt_property2 - 属性2
     */
    public String getXtProperty2() {
        return xtProperty2;
    }

    /**
     * 设置属性2
     *
     * @param xtProperty2 属性2
     */
    public void setXtProperty2(String xtProperty2) {
        this.xtProperty2 = xtProperty2;
    }

    /**
     * 获取父级id
     *
     * @return xt_subject_parent_code - 父级id
     */
    public String getXtSubjectParentCode() {
        return xtSubjectParentCode;
    }

    /**
     * 设置父级id
     *
     * @param xtSubjectParentCode 父级id
     */
    public void setXtSubjectParentCode(String xtSubjectParentCode) {
        this.xtSubjectParentCode = xtSubjectParentCode;
    }

    /**
     * 获取全路径
     *
     * @return xt_full_path - 全路径
     */
    public String getXtFullPath() {
        return xtFullPath;
    }

    /**
     * 设置全路径
     *
     * @param xtFullPath 全路径
     */
    public void setXtFullPath(String xtFullPath) {
        this.xtFullPath = xtFullPath;
    }
}