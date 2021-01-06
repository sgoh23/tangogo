package com.example;

public class Transaction {

    public double transactionAmount = 0.00;
    public String transactionDate = "";
    public boolean reconciled = false;
    public String reconciledTxnRefID;

    public Transaction(){
    }

    public Transaction(double txnAmt){
        transactionAmount = txnAmt;
    }

    public Transaction(double txnAmt, String txnDate){

        transactionAmount = txnAmt;
        transactionDate = txnDate;
    }


}
