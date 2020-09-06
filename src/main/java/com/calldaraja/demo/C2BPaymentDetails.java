package com.calldaraja.demo;

import javax.persistence.*;

@Entity
@Table(name="transactions_table")
public class C2BPaymentDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="mpesa_trans_code")
    private Long mpesaCodeTrans;

    @Column(name="mpesa_trans_type")
    private String transactionType;

    @Column(name="mpesa_trans_id")
    private String transID;

    @Column(name="mpesa_trans_time")
    private String transTime;

    @Column(name="mpesa_trans_ammount")
    private String transAmount;

    @Column(name="mpesa_trans_sht_code")
    private String businessShortCode;

    @Column(name="mpesa_trans_refno")
    private  String billRefNumber;

    @Column(name="mpesa_trans_invoice")
    private String invoiceNumber;

    @Column(name="mpesa_trans_orgbal")
    private String orgAccountBalance;

    @Column(name="mpesa_trans_thirdp")
    private String thirdPartyTransID;

    @Column(name="mpesa_trans_msisdn")
    private  String mSISDN;

    @Column(name="mpesa_trans_firstname")
    private String firstName;

    @Column(name="mpesa_trans_middlename")
    private String middleName;

    @Column(name="mpesa_trans_lastname")
    private String lastName;

    public C2BPaymentDetails(Long mpesaCodeTrans, String transactionType, String transID, String transTime, String transAmount, String businessShortCode, String billRefNumber, String invoiceNumber, String orgAccountBalance, String thirdPartyTransID, String mSISDN, String firstName, String middleName, String lastName) {
        this.mpesaCodeTrans = mpesaCodeTrans;
        this.transactionType = transactionType;
        this.transID = transID;
        this.transTime = transTime;
        this.transAmount = transAmount;
        this.businessShortCode = businessShortCode;
        this.billRefNumber = billRefNumber;
        this.invoiceNumber = invoiceNumber;
        this.orgAccountBalance = orgAccountBalance;
        this.thirdPartyTransID = thirdPartyTransID;
        this.mSISDN = mSISDN;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public C2BPaymentDetails() {
    }

    public Long getMpesaCodeTrans() {
        return mpesaCodeTrans;
    }

    public void setMpesaCodeTrans(Long mpesaCodeTrans) {
        this.mpesaCodeTrans = mpesaCodeTrans;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransID() {
        return transID;
    }

    public void setTransID(String transID) {
        this.transID = transID;
    }

    public String getTransTime() {
        return transTime;
    }

    public void setTransTime(String transTime) {
        this.transTime = transTime;
    }

    public String getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(String transAmount) {
        this.transAmount = transAmount;
    }

    public String getBusinessShortCode() {
        return businessShortCode;
    }

    public void setBusinessShortCode(String businessShortCode) {
        this.businessShortCode = businessShortCode;
    }

    public String getBillRefNumber() {
        return billRefNumber;
    }

    public void setBillRefNumber(String billRefNumber) {
        this.billRefNumber = billRefNumber;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getOrgAccountBalance() {
        return orgAccountBalance;
    }

    public void setOrgAccountBalance(String orgAccountBalance) {
        this.orgAccountBalance = orgAccountBalance;
    }

    public String getThirdPartyTransID() {
        return thirdPartyTransID;
    }

    public void setThirdPartyTransID(String thirdPartyTransID) {
        this.thirdPartyTransID = thirdPartyTransID;
    }

    public String getmSISDN() {
        return mSISDN;
    }

    public void setmSISDN(String mSISDN) {
        this.mSISDN = mSISDN;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
