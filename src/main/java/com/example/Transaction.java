package com.example;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class Transaction{

    public double transactionAmount;    //Sample: -42.80
    public String transactionDate = ""; //Sample: 01/09/2020
    public String transactionDesc = "";
    public boolean reconciled = false;
    public String reconciledTxnRefID="";
    public String chequeNo = "";
    public String refdate = "";
    public String transactionChannel = "";


    public double creditAmount;                 // Sample:  42.80
    public double debitAmount;                  // Sample : 20000.00
    public String transactionTypeCode;          // Sample: "NTRF"
    public String notes_refForAccOwner = "";         // Sample: "from MUHAMMAD ZUHAI"
    public String notes_statementDetailsInfo;   // Sample: "OTHR - Other FUND TRANSFER" or "PAYMENT/TRANSFER OTHR S$ SUHAIRY BIN FADLILA via PayNow: FBR3022Z"
    public String notes_ourRef="";                 // Sample: "OTHR S$"
    public String notes_supplementaryDetails = "";   // Sample: "PAYMENT/TRANSFER"

    public final static String CHEQUE = "CHEQUE";
    public final static String GIRO_PAYMENT = "GIRO PAYMENT";
    public final static String LTA = "LTA";
    public final static String NETS = "NETS -- NETS";
    public final static String POS_SETTLEMENT = "POS SETTLEMENT";

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

        if(!transactionDesc.isEmpty() && ((transactionDesc.contains(NETS)) || notes_supplementaryDetails.equals(POS_SETTLEMENT))){
            setThisAsNETSTransaction();
        }

    }
    public Transaction(double txnAmt, String txnDate, String txnDesc1, String txnDesc2, String txnDesc3, String txnDesc4){
        transactionAmount = txnAmt;
        transactionDate = txnDate;
        transactionDesc = txnDesc1;
        notes_refForAccOwner = txnDesc2;
        notes_ourRef = txnDesc3;
        notes_supplementaryDetails = txnDesc4;

        if(!notes_supplementaryDetails.isEmpty() && (notes_supplementaryDetails.contains(CHEQUE) )){
            chequeNo = notes_refForAccOwner;
            transactionChannel = CHEQUE;
        }

        if(!notes_supplementaryDetails.isEmpty() && (notes_supplementaryDetails.contains(GIRO_PAYMENT))){
            chequeNo = notes_refForAccOwner;
            transactionChannel = GIRO_PAYMENT;
        }

        if(!transactionDesc.isEmpty() && (transactionDesc.contains(LTA))){
            transactionChannel = LTA;

            if(isValidDate(notes_refForAccOwner) && !notes_refForAccOwner.isEmpty()) {
                refdate = notes_refForAccOwner;
            }
        }

        if(!transactionDesc.isEmpty() && ((transactionDesc.contains(NETS)) || notes_supplementaryDetails.equals(POS_SETTLEMENT))){
            setThisAsNETSTransaction();
        }

    }

    public void setThisAsNETSTransaction(){
        transactionChannel = NETS;

        if(isValidDate(notes_ourRef) && !notes_ourRef.isEmpty()) {
            refdate = notes_ourRef;
        }

    }

    public String getTransactionRefIDString(){
        return this.toString();
    }

    public double getTransactionAmount(){ return this.transactionAmount;}

    public String getTransactionDate(){ return this.transactionDate;}

    public String getTransactionChannel(){ return this.transactionChannel;}

    public String getChequeNo(){return this.chequeNo;}

    public void updateAsReconciled(boolean status, String refID){

        if(!this.reconciled && this.reconciledTxnRefID.isEmpty()) {
            this.reconciled = status;
            this.reconciledTxnRefID = refID;
        }
    }

    public boolean isValidDate(final String date) {

        boolean valid = false;

        if(!date.isEmpty()) {
            //System.out.println(date);

            try {

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.US);

                // ResolverStyle.STRICT for 30, 31 days checking, and also leap year.
                // LocalDate.parse(date, dtf.withResolverStyle(ResolverStyle.STRICT));
                LocalDateTime localDateTime = LocalDate.parse(date, dtf).atStartOfDay();
                valid = true;

            } catch (DateTimeParseException e) {
                // e.printStackTrace();
                valid = false;
            }

        }

        //System.out.println(valid);
        return valid;
    }
}

