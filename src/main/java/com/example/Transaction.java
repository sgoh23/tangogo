package com.example;

public class Transaction {

    public double transactionAmount;    //Sample: -42.80
    public String transactionDate = ""; //Sample: 01/09/2020
    public String transactionDesc;
    public boolean reconciled = false;
    public String reconciledTxnRefID;


    public double creditAmount;                 // Sample:  42.80
    public double debitAmount;                  // Sample : 20000.00
    public String transactionTypeCode;          // Sample: "NTRF"
    public String notes_refForAccOwner;         // Sample: "from MUHAMMAD ZUHAI"
    public String notes_statementDetailsInfo;   // Sample: "OTHR - Other FUND TRANSFER" or "PAYMENT/TRANSFER OTHR S$ SUHAIRY BIN FADLILA via PayNow: FBR3022Z"
    public String notes_ourRef;                 // Sample: "OTHR S$"
    public String notes_supplementaryDetails;   // Sample: "PAYMENT/TRANSFER"

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
    }
    public Transaction(double txnAmt, String txnDate, String txnDesc1, String txnDesc2){
        transactionAmount = txnAmt;
        transactionDate = txnDate;
        transactionDesc = txnDesc1;
        notes_supplementaryDetails = txnDesc2;
    }


    public String getTransactionRefIDString(){
        return this.toString();
    }

}
