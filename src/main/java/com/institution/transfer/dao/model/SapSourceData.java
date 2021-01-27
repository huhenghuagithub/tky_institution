package com.institution.transfer.dao.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sap_source_data")
public class SapSourceData {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * SAP公司编码
     */
    @Column(name = "sap_company_code")
    private String sapCompanyCode;

    /**
     * SAP凭证号码
     */
    @Column(name = "sap_voucher_code")
    private Long sapVoucherCode;

    /**
     * SAP过账日期
     */
    @Column(name = "sap_post_date")
    private Date sapPostDate;

    /**
     * SAP文本
     */
    @Column(name = "sap_text_value")
    private String sapTextValue;

    /**
     * 借贷方类型;S借，H贷
     */
    @Column(name = "sap_debit_credit")
    private String sapDebitCredit;

    /**
     * SAP总账
     */
    @Column(name = "sap_general_ledger")
    private String sapGeneralLedger;

    /**
     * 标准WBS元素
     */
    @Column(name = "sap_wbs_element")
    private String sapWbsElement;

    /**
     * SAP本位币金额
     */
    @Column(name = "sap_currency_amount")
    private String sapCurrencyAmount;

    /**
     * SAP反记账
     */
    @Column(name = "sap_reverse_bookkeeping")
    private String sapReverseBookkeeping;

    /**
     * SAP功能范围
     */
    @Column(name = "sap_functional_scope")
    private String sapFunctionalScope;

    /**
     * SAP制单人
     */
    @Column(name = "sap_touching_person")
    private String sapTouchingPerson;

    /**
     * SAP供应商
     */
    @Column(name = "sap_merchants_supplier")
    private String sapMerchantsSupplier;

    /**
     * SAP客户
     */
    @Column(name = "sap_merchants_customer")
    private String sapMerchantsCustomer;

    /**
     * SAP客商
     */
    @Column(name = "sap_merchants")
    private String sapMerchants;

    /**
     * 备选参考编号
     */
    @Column(name = "sap_alternative_reference_number")
    private String sapAlternativeReferenceNumber;

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
     * 获取SAP公司编码
     *
     * @return sap_company_code - SAP公司编码
     */
    public String getSapCompanyCode() {
        return sapCompanyCode;
    }

    /**
     * 设置SAP公司编码
     *
     * @param sapCompanyCode SAP公司编码
     */
    public void setSapCompanyCode(String sapCompanyCode) {
        this.sapCompanyCode = sapCompanyCode;
    }

    /**
     * 获取SAP凭证号码
     *
     * @return sap_voucher_code - SAP凭证号码
     */
    public Long getSapVoucherCode() {
        return sapVoucherCode;
    }

    /**
     * 设置SAP凭证号码
     *
     * @param sapVoucherCode SAP凭证号码
     */
    public void setSapVoucherCode(Long sapVoucherCode) {
        this.sapVoucherCode = sapVoucherCode;
    }

    /**
     * 获取SAP过账日期
     *
     * @return sap_post_date - SAP过账日期
     */
    public Date getSapPostDate() {
        return sapPostDate;
    }

    /**
     * 设置SAP过账日期
     *
     * @param sapPostDate SAP过账日期
     */
    public void setSapPostDate(Date sapPostDate) {
        this.sapPostDate = sapPostDate;
    }

    /**
     * 获取SAP文本
     *
     * @return sap_text_value - SAP文本
     */
    public String getSapTextValue() {
        return sapTextValue;
    }

    /**
     * 设置SAP文本
     *
     * @param sapTextValue SAP文本
     */
    public void setSapTextValue(String sapTextValue) {
        this.sapTextValue = sapTextValue;
    }

    /**
     * 获取借贷方类型;S借，H贷
     *
     * @return sap_debit_credit - 借贷方类型;S借，H贷
     */
    public String getSapDebitCredit() {
        return sapDebitCredit;
    }

    /**
     * 设置借贷方类型;S借，H贷
     *
     * @param sapDebitCredit 借贷方类型;S借，H贷
     */
    public void setSapDebitCredit(String sapDebitCredit) {
        this.sapDebitCredit = sapDebitCredit;
    }

    /**
     * 获取SAP总账
     *
     * @return sap_general_ledger - SAP总账
     */
    public String getSapGeneralLedger() {
        return sapGeneralLedger;
    }

    /**
     * 设置SAP总账
     *
     * @param sapGeneralLedger SAP总账
     */
    public void setSapGeneralLedger(String sapGeneralLedger) {
        this.sapGeneralLedger = sapGeneralLedger;
    }

    /**
     * 获取标准WBS元素
     *
     * @return sap_wbs_element - 标准WBS元素
     */
    public String getSapWbsElement() {
        return sapWbsElement;
    }

    /**
     * 设置标准WBS元素
     *
     * @param sapWbsElement 标准WBS元素
     */
    public void setSapWbsElement(String sapWbsElement) {
        this.sapWbsElement = sapWbsElement;
    }

    /**
     * 获取SAP本位币金额
     *
     * @return sap_currency_amount - SAP本位币金额
     */
    public String getSapCurrencyAmount() {
        return sapCurrencyAmount;
    }

    /**
     * 设置SAP本位币金额
     *
     * @param sapCurrencyAmount SAP本位币金额
     */
    public void setSapCurrencyAmount(String sapCurrencyAmount) {
        this.sapCurrencyAmount = sapCurrencyAmount;
    }

    /**
     * 获取SAP反记账
     *
     * @return sap_reverse_bookkeeping - SAP反记账
     */
    public String getSapReverseBookkeeping() {
        return sapReverseBookkeeping;
    }

    /**
     * 设置SAP反记账
     *
     * @param sapReverseBookkeeping SAP反记账
     */
    public void setSapReverseBookkeeping(String sapReverseBookkeeping) {
        this.sapReverseBookkeeping = sapReverseBookkeeping;
    }

    /**
     * 获取SAP功能范围
     *
     * @return sap_functional_scope - SAP功能范围
     */
    public String getSapFunctionalScope() {
        return sapFunctionalScope;
    }

    /**
     * 设置SAP功能范围
     *
     * @param sapFunctionalScope SAP功能范围
     */
    public void setSapFunctionalScope(String sapFunctionalScope) {
        this.sapFunctionalScope = sapFunctionalScope;
    }

    /**
     * 获取SAP制单人
     *
     * @return sap_touching_person - SAP制单人
     */
    public String getSapTouchingPerson() {
        return sapTouchingPerson;
    }

    /**
     * 设置SAP制单人
     *
     * @param sapTouchingPerson SAP制单人
     */
    public void setSapTouchingPerson(String sapTouchingPerson) {
        this.sapTouchingPerson = sapTouchingPerson;
    }

    /**
     * 获取SAP供应商
     *
     * @return sap_merchants_supplier - SAP供应商
     */
    public String getSapMerchantsSupplier() {
        return sapMerchantsSupplier;
    }

    /**
     * 设置SAP供应商
     *
     * @param sapMerchantsSupplier SAP供应商
     */
    public void setSapMerchantsSupplier(String sapMerchantsSupplier) {
        this.sapMerchantsSupplier = sapMerchantsSupplier;
    }

    /**
     * 获取SAP客户
     *
     * @return sap_merchants_customer - SAP客户
     */
    public String getSapMerchantsCustomer() {
        return sapMerchantsCustomer;
    }

    /**
     * 设置SAP客户
     *
     * @param sapMerchantsCustomer SAP客户
     */
    public void setSapMerchantsCustomer(String sapMerchantsCustomer) {
        this.sapMerchantsCustomer = sapMerchantsCustomer;
    }

    /**
     * 获取SAP客商
     *
     * @return sap_merchants - SAP客商
     */
    public String getSapMerchants() {
        return sapMerchants;
    }

    /**
     * 设置SAP客商
     *
     * @param sapMerchants SAP客商
     */
    public void setSapMerchants(String sapMerchants) {
        this.sapMerchants = sapMerchants;
    }

    /**
     * 获取备选参考编号
     *
     * @return sap_alternative_reference_number - 备选参考编号
     */
    public String getSapAlternativeReferenceNumber() {
        return sapAlternativeReferenceNumber;
    }

    /**
     * 设置备选参考编号
     *
     * @param sapAlternativeReferenceNumber 备选参考编号
     */
    public void setSapAlternativeReferenceNumber(String sapAlternativeReferenceNumber) {
        this.sapAlternativeReferenceNumber = sapAlternativeReferenceNumber;
    }
}