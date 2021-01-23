package com.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReconciliationTool {
    public ArrayList<Transaction> array1;
    public ArrayList<Transaction> array2;

    public ArrayList<Transaction> arr1_pendingReconRecords = new ArrayList<>();
    public ArrayList<Transaction> arr2_pendingReconRecords = new ArrayList<>();
    public ArrayList<Transaction> mayMatchRecords = new ArrayList<>();


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

        List<Transaction> cheques = array1.stream()
                .filter(transaction -> !transaction.getChequeNo().isEmpty() &&
                        !transaction.reconciled)
                .collect(Collectors.toList());

        cheques.forEach(c -> reconTransaction_forCheques(c.chequeNo,c.transactionAmount,c.getTransactionRefIDString()));

        List<Transaction> LTATxns = arr1_pendingReconRecords.stream()
                .filter(transaction -> !transaction.reconciled &&
                        transaction.transactionChannel.equals(transaction.LTA) &&
                        !transaction.refdate.isEmpty())
                .collect(Collectors.toList());

        LTATxns.forEach(l -> reconTransaction_forLTA(l.refdate,l.transactionAmount,l.getTransactionRefIDString()));

        List<Transaction> allOtherTxn = arr1_pendingReconRecords.stream()
                .filter(transaction -> !transaction.reconciled &&
                        transaction.transactionChannel.equals(""))
                .collect(Collectors.toList());

        allOtherTxn.forEach(a -> reconTransaction_forAllByDateAndAmount(a.transactionAmount,a.transactionDate,a.getTransactionRefIDString()));

        List<Transaction> mayMatches = arr1_pendingReconRecords.stream()
                .filter(t -> !t.reconciled &&
                        t.transactionChannel.equals(""))
                .collect(Collectors.toList());

        mayMatches.forEach(this::reconTransaction_forMayMatches);

    }

    public Transaction reconTransaction_forCheques(String chq, double txnAmt, String arr1TxnRefID){

        //System.out.println(chq);
        Transaction record = array2.stream()
                .filter(transaction -> transaction.transactionDesc.contains(chq) &&
                        transaction.transactionAmount == txnAmt)
                .findAny()
                .orElse(null);


        if(record != null){
            record.updateAsReconciled(true,arr1TxnRefID);
            record.chequeNo = chq;
            //printRecords(record,"RECORD");
            removeFromPendingRecon(record,arr1TxnRefID,array1);
        }

        return record;

    }

    public Transaction reconTransaction_forLTA(String refDate, double txnAmt, String arr1TxnRefID){

        //System.out.println(refDate+"|"+arr1TxnRefID+"|"+txnAmt);
        Transaction record = array2.stream()
                .filter(txn -> txn.transactionDate.equals(refDate) &&
                        txn.isValidDate(refDate) &&
                        !txn.reconciled &&
                        txn.transactionAmount == txnAmt)
                .findAny()
                .orElse(null);


        if(record != null){
            record.transactionChannel = record.LTA;
            record.updateAsReconciled(true,arr1TxnRefID);
            //printRecords(record, "LTA Record");

            removeFromPendingRecon(record,arr1TxnRefID,array1);
        }
        return record;
    }

    public Transaction reconTransaction_forAllByDateAndAmount(double txnAmt, String txnDate, String arr1TxnRefID){

        Transaction record = array2.stream()
                .filter(txn -> txn.transactionAmount == txnAmt &&
                        txn.isValidDate(txnDate) &&
                        txn.transactionDate.equals(txnDate) &&
                        txn.transactionChannel.equals("") &&
                        !txn.reconciled)
                .findAny()
                .orElse(null);

        if(record != null){

            record.updateAsReconciled(true,arr1TxnRefID);
            removeFromPendingRecon(record,arr1TxnRefID,array1);

        }
        return record;
    }

    public Transaction reconTransaction_forMayMatches(Transaction tryMatchingWithTxn){

        Transaction record = arr2_pendingReconRecords.stream()
                .filter(txn -> txn.transactionAmount == tryMatchingWithTxn.transactionAmount &&
                        txn.transactionChannel.equals("") &&
                        !txn.reconciled)
                .findAny()
                .orElse(null);


        if(record != null){

            tryMatchingWithTxn.updateAsReconciled(true,record.getTransactionRefIDString());
            tryMatchingWithTxn.transactionChannel = "MAY-MATCH";
            mayMatchRecords.add(tryMatchingWithTxn);

            record.updateAsReconciled(true,tryMatchingWithTxn.getTransactionRefIDString());
            record.transactionChannel = "MAY-MATCH";
            removeFromPendingRecon(record,tryMatchingWithTxn.getTransactionRefIDString(),array1);

            System.out.println(record.transactionDesc);
            System.out.println(tryMatchingWithTxn.transactionDesc);
            printRecords(record,"RECORD");

        }
        return record;
    }

    public boolean runReconForNETSTransactions() {

        CompanyBankAccount coy = new CompanyBankAccount();
        Map<String, Double> netsSummaryByDate = coy.getNETSSummary();
        boolean success = false;

        for (Map.Entry<String, Double> entry : netsSummaryByDate.entrySet()) {

            String netsDateKey = entry.getKey();
            Double amtValue = entry.getValue();

            BigDecimal bd = new BigDecimal(amtValue).setScale(2, RoundingMode.HALF_UP);
            double value = bd.doubleValue();

           // System.out.println("Key: " + netsDateKey + ", Value: " + value);

            Transaction txnFound = getArr1PendingReconNETSTxn(value, netsDateKey);

            if (txnFound != null) { //Match is found in bank record

                reconTransactionInArr1(txnFound, "DailyNETSSum-" + netsDateKey);
                reconTransactionInArr2_forNETS(txnFound);
                success = true;
            }
        }
        return success;

    }

    private void reconTransactionInArr2_forNETS(Transaction txnFound){

        List<Transaction> coyDailyNETsReconciledRecords = arr2_pendingReconRecords.stream()
                .filter(transaction -> transaction.transactionChannel.equals(transaction.NETS) &&
                        transaction.getTransactionDate().equals(txnFound.refdate))
                .collect(Collectors.toList());

        for (Transaction txn : coyDailyNETsReconciledRecords) {
            arr2_pendingReconRecords.remove(txn);
            txn.updateAsReconciled(true, txn.getTransactionRefIDString());
        }

        //Print NETS Recon Trail
       //printRecords(coyDailyNETsReconciledRecords, "NETS Details");
    }

    public boolean removeFromPendingRecon(Transaction matchedTxn, String reconWithRef, ArrayList<Transaction> fromArray){

        boolean success = false;

        Transaction reconWithTxn = fromArray.stream()
                .filter(transaction -> transaction.getTransactionRefIDString().equals(reconWithRef))
                .findAny()
                .orElse(null);

        if(reconWithTxn != null) {
            reconWithTxn.updateAsReconciled(true,matchedTxn.getTransactionRefIDString());
            arr1_pendingReconRecords.remove(reconWithTxn);
            arr2_pendingReconRecords.remove(matchedTxn);
            success = true;
        }

        return success;
    }

    public void reconTransactionInArr1(Transaction txn, String refID){
        arr1_pendingReconRecords.remove(txn);
        txn.updateAsReconciled(true,refID);
    }

    public Transaction getArr1PendingReconNETSTxn(double amt, String date){

        Transaction txnFound = array1.stream()
                .filter(transaction -> transaction.transactionChannel.equals(transaction.NETS) &&
                        transaction.getTransactionAmount() == amt &&
                        transaction.refdate.equals(date))
                .findAny()
                .orElse(null);

        // System.out.println(txnFound.transactionDesc+"|"+txnFound.getTransactionAmount());

        return txnFound;

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


    public void printRecordsSummary(ArrayList<Transaction> array, ArrayList<Transaction> baseArray, String listname) {
        Print.summarizeRecords(array,baseArray,listname);
    }
    public void printRecords(ArrayList<Transaction> array, String listname){
        Print.theseRecords(array,listname);
    }
    public void printRecords(List<Transaction> array, String listname){
        Print.theseRecords(array,listname);
    }
    public void printRecords(Transaction txn, String listname){
        Print.thisRecord(txn,listname);
    }
}
