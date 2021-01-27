package com.institution.transfer.vo;

import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
public class VoucherCodeMappingVO {

    /**
     * SAP源数据ID
     */
    private Integer id;

    /**
     * SAP单位代码
     */
    private String sapCompanyCode="";

    /**
     * SAP单位名称
     */
    private String sapCompanyName="";

    /**
     * 6.0单位代码
     */
    private String sexCompanyCode="";

    /**
     * 6.0单位名称
     */
    private String sexCompanyName="";

    /**
     * SAP凭证号
     */
    private String sapVoucherCode="";

    /**
     * 6.0凭证类型+凭证号
     */
    private String sexVoucherCodeAndType="";

    /**
     * SAP过账日期
     */
    private String sapPostDate="";

    public VoucherCodeMappingVO() {
    }

    public VoucherCodeMappingVO(Integer id, String sapCompanyCode, String sapCompanyName, String sexCompanyCode, String sexCompanyName, String sapVoucherCode, String sexVoucherCodeAndType, String sapPostDate) {
        this.id = id;
        this.sapCompanyCode = sapCompanyCode;
        this.sapCompanyName = sapCompanyName;
        this.sexCompanyCode = sexCompanyCode;
        this.sexCompanyName = sexCompanyName;
        this.sapVoucherCode = sapVoucherCode;
        this.sexVoucherCodeAndType = sexVoucherCodeAndType;
        this.sapPostDate = sapPostDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VoucherCodeMappingVO that = (VoucherCodeMappingVO) o;
        return sapCompanyCode.equals(that.sapCompanyCode) &&
                sapVoucherCode.equals(that.sapVoucherCode) &&
                sexVoucherCodeAndType.equals(that.sexVoucherCodeAndType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sapCompanyCode, sapVoucherCode, sexVoucherCodeAndType);
    }
}
