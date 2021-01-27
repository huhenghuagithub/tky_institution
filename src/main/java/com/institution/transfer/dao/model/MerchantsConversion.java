package com.institution.transfer.dao.model;

import javax.persistence.*;

@Table(name = "merchants_conversion")
public class MerchantsConversion {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 旧客商名称（错误数据）
     */
    @Column(name = "old_merchants_name")
    private String oldMerchantsName;

    /**
     * 新客商名称（正确数据）
     */
    @Column(name = "new_merchants_name")
    private String newMerchantsName;

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
     * 获取旧客商名称（错误数据）
     *
     * @return old_merchants_name - 旧客商名称（错误数据）
     */
    public String getOldMerchantsName() {
        return oldMerchantsName;
    }

    /**
     * 设置旧客商名称（错误数据）
     *
     * @param oldMerchantsName 旧客商名称（错误数据）
     */
    public void setOldMerchantsName(String oldMerchantsName) {
        this.oldMerchantsName = oldMerchantsName;
    }

    /**
     * 获取新客商名称（正确数据）
     *
     * @return new_merchants_name - 新客商名称（正确数据）
     */
    public String getNewMerchantsName() {
        return newMerchantsName;
    }

    /**
     * 设置新客商名称（正确数据）
     *
     * @param newMerchantsName 新客商名称（正确数据）
     */
    public void setNewMerchantsName(String newMerchantsName) {
        this.newMerchantsName = newMerchantsName;
    }
}