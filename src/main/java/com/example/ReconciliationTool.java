package com.example;

import java.util.ArrayList;

public class ReconciliationTool {
    public ArrayList<Transaction> array1 = new ArrayList<>();
    public ArrayList<Transaction> array2 = new ArrayList<>();

    public ArrayList<Transaction> arr1_pendingReconRecords = new ArrayList<>();
    public ArrayList<Transaction> arr2_pendingReconRecords = new ArrayList<>();
    ArrayList<Transaction> reconciledListOnArr2 = new ArrayList<>();

    double bal1;
    double bal2;

    public ReconciliationTool(){ }
    public ReconciliationTool(ArrayList<Transaction> arr1, ArrayList<Transaction> arr2){

        array1 = arr1;
        array2 = arr2;

        bal1 = getArray1Balance();
        bal2 = getArray2Balance();
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


    public void initiateArr1CheckList() {
        arr1_pendingReconRecords = (ArrayList<Transaction>) array1.clone();
        arr2_pendingReconRecords = (ArrayList<Transaction>) array2.clone();
    }


    public void runReconBasedOnArr1() {

        for (Transaction txn : array1) {
            Transaction matchedTxn = reconTransactionInArr2(txn.transactionAmount,txn.toString());
            if(matchedTxn != null) {
                txn.reconciled = true;
                txn.reconciledTxnRefID = matchedTxn.toString();
                arr1_pendingReconRecords.remove(txn);
            }
        }

    }

    public Transaction reconTransactionInArr2(double txnAmt, String arr1TxnRefID){

        try{

            for (Transaction record : array2) {
                if (record.transactionAmount == txnAmt && !record.reconciled) {
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

    public ArrayList<Transaction> getArr1RecordsMissingOnArr2() {
        return arr1_pendingReconRecords;
    }

    public void printRecords(ArrayList<Transaction> array, String listname){
        Print.theseRecords(array,listname);
    }

}
