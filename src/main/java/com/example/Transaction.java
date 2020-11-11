package com.example;

public class Transaction {

    double transactionAmount = 0.00;
    String transactionDate = "";
    String transactionType = "";
    String transactionID = "";

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
