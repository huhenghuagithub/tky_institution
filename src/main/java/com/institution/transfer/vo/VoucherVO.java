package com.institution.transfer.vo;

import lombok.Data;

import java.util.List;

@Data
public class VoucherVO {

    /**
     * SAP源数据ID
     */
    private Integer id;

    /**
     * SAP单位代码
     */
    private String sapCompanyCode;

    /**
     * SAP凭证号
     */
    private String sapVoucherCode;

    /**
     * SAP过账日期
     */
    private String sapPostDate;

    /**
     * 凭证号对应多个6.0科目信息
     */
    List<VoucherDetailsVO> voucherDetailsVOS;

    public static void main(String[] args) {

        String s="1000_100000083_2020-07-31";

        //SAP公司代码
        String sapCompanyCode=s.substring(0,s.indexOf("_"));
        //SAP凭证号
        String sapVoucherCode=s.substring(s.indexOf("_")+1,s.lastIndexOf("_"));
        //SAP过账日期
        String sapPostDate=s.substring(s.lastIndexOf("_")+1);

        System.out.println(sapCompanyCode);
        System.out.println(sapVoucherCode);
        System.out.println(sapPostDate);
        System.out.println(sapPostDate.substring(5,7));
    }

}
