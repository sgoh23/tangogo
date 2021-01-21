package com.example;

import java.util.ArrayList;

public class ReconciliationTool {
    public ArrayList<Transaction> array1;
    public ArrayList<Transaction> array2;

    public ArrayList<Transaction> arr1_pendingReconRecords = new ArrayList<>();
    public ArrayList<Transaction> arr2_pendingReconRecords = new ArrayList<>();

    double bal1;
    double bal2;

    public ReconciliationTool(ArrayList<Transaction> arr1, ArrayList<Transaction> arr2){

        array1 = arr1;
        array2 = arr2;

        bal1 = getArray1Balance();
        bal2 = getArray2Balance();

        initiateCheckList();
    }

    public double getArray1Balance(){

        double balance = 0.00;

        for (Transaction txn : array1){
                balance += txn.transactionAmount;
        }
        return balance;
    }

    public double getArray2Balance(){

        double balance = 0.00;

        for (Transaction txn : array2){
            balance += txn.transactionAmount;
        }
        return balance;
    }


    public void initiateCheckList() {
        arr1_pendingReconRecords = (ArrayList<Transaction>) array1.clone();
        arr2_pendingReconRecords = (ArrayList<Transaction>) array2.clone();
    }

    public Transaction getArr1PendingReconTxn(double amt, String date){

        for (Transaction txn : arr1_pendingReconRecords){
            if(txn.transactionAmount == amt && txn.transactionDate.equals(date)){
                return txn;
            }
        }
        return null;
    }


    public void runRecon() {

        Transaction matchedTxn;

        for (Transaction txn : array1) {

            if(txn.chequeNo != null){
                matchedTxn = reconTransactionInArr2_forCheques(txn.chequeNo,txn.transactionAmount, txn.getTransactionRefIDString());
            }
            else {
                matchedTxn = reconTransactionInArr2_withGoodMatches(txn.transactionAmount, txn.transactionDate, txn.toString());
            } if(matchedTxn == null){

                //LTA Transactions based on reference date (refdate)
                matchedTxn = reconTransactionInArr2_forLTA(txn.refdate,txn.transactionAmount,txn.getTransactionRefIDString());
            }

            if(matchedTxn != null) {
                txn.reconciled = true;
                txn.reconciledTxnRefID = matchedTxn.toString();
                arr1_pendingReconRecords.remove(txn);
                arr2_pendingReconRecords.remove(matchedTxn);
            }
        }

    }

    public Transaction reconTransactionInArr2_forCheques(String chq, double txnAmt, String arr1TxnRefID){
        for (Transaction record : array2) {
            if(record.transactionDesc.contains(chq) && record.transactionAmount == txnAmt){
               // System.out.println(chq);
                record.reconciled = true;
                record.reconciledTxnRefID = arr1TxnRefID;
                return record;
            }
        }
        return null;
    }

    public Transaction reconTransactionInArr2_forLTA(String refDate, double txnAmt, String arr1TxnRefID){

        for (Transaction record : array2) {

            if(record.transactionDate.equals(refDate) && record.transactionAmount == txnAmt){
               // System.out.println(record.transactionDate+"|"+record.transactionDesc + "|" + record.transactionAmount);
                record.reconciled = true;
                record.reconciledTxnRefID = arr1TxnRefID;
                return record;
            }
        }
        return null;
    }

    public Transaction reconTransactionInArr2_withGoodMatches(double txnAmt, String txnDate, String arr1TxnRefID){

        try{

            for (Transaction record : array2) {
                if (record.transactionAmount == txnAmt && record.transactionDate.equals(txnDate) && !record.reconciled) {
                    record.reconciled = true;
                    record.reconciledTxnRefID = arr1TxnRefID;
                    arr2_pendingReconRecords.remove(record);
                    return record;
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void printRecords(ArrayList<Transaction> array, String listname){
        Print.theseRecords(array,listname);
    }

    public Transaction getTransactionFromArray2(String txnRefID) {

        Transaction txnFound = null;
        for(Transaction txn : array2){

            if(txn.getTransactionRefIDString().equals(txnRefID)){
                txnFound = txn;
            }
        }
        return txnFound;
    }


    public void printRecordsSummary() {

        Print.summarizeRecords(arr1_pendingReconRecords,array1,"Bank Records Pending Recon");
        Print.summarizeRecords(arr2_pendingReconRecords,array2,"Company Records Pending Recon");
    }
}
