package com.example;

public class Transaction{

    public double transactionAmount;    //Sample: -42.80
    public String transactionDate = ""; //Sample: 01/09/2020
    public String transactionDesc;
    public boolean reconciled = false;
    public String reconciledTxnRefID;
    public String chequeNo;
    public String refdate;
    public String transactionChannel="";


    public double creditAmount;                 // Sample:  42.80
    public double debitAmount;                  // Sample : 20000.00
    public String transactionTypeCode;          // Sample: "NTRF"
    public String notes_refForAccOwner = "";         // Sample: "from MUHAMMAD ZUHAI"
    public String notes_statementDetailsInfo;   // Sample: "OTHR - Other FUND TRANSFER" or "PAYMENT/TRANSFER OTHR S$ SUHAIRY BIN FADLILA via PayNow: FBR3022Z"
    public String notes_ourRef;                 // Sample: "OTHR S$"
    public String notes_supplementaryDetails = "";   // Sample: "PAYMENT/TRANSFER"

    final static String CHEQUE = "CHEQUE";
    final static String GIRO_PAYMENT = "GIRO PAYMENT";
    final static String LTA = "LTA";
    final static String NETS = "NETS";

    public Transaction(){
    }

    public Transaction(double txnAmt, String txnDate){
        transactionAmount = txnAmt;
        transactionDate = txnDate;
    }

    public Transaction(double txnAmt, String txnDate, String txnDesc){
        transactionAmount = txnAmt;
        transactionDate = txnDate;
        transactionDesc = txnDesc;

        if(!transactionDesc.isEmpty() && (transactionDesc.contains(NETS))){
            transactionChannel = NETS;
        }

    }
    public Transaction(double txnAmt, String txnDate, String txnDesc1, String txnDesc2, String txnDesc3){
        transactionAmount = txnAmt;
        transactionDate = txnDate;
        transactionDesc = txnDesc1;
        notes_refForAccOwner = txnDesc2;
        notes_supplementaryDetails = txnDesc3;

        if(!notes_supplementaryDetails.isEmpty() && (notes_supplementaryDetails.contains(CHEQUE) || notes_supplementaryDetails.contains(GIRO_PAYMENT))){
            chequeNo = notes_refForAccOwner;
        }

        if(!transactionDesc.isEmpty() && (transactionDesc.contains(LTA))){
            refdate = notes_refForAccOwner;
        }

        if(!transactionDesc.isEmpty() && (transactionDesc.contains(NETS))){
            transactionChannel = NETS;
        }


    }

    public String getTransactionRefIDString(){
        return this.toString();
    }

    public double getTransactionAmount(){ return this.transactionAmount;}

    public String getTransactionDate(){ return this.transactionDate;}

    public String getTransactionChannel(){ return this.transactionChannel;}
}
