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

        //Run for cheques
        List<Transaction> cheques = array1.stream()
                .filter(transaction -> !transaction.getChequeNo().isEmpty() &&
                        !transaction.reconciled)
                .collect(Collectors.toList());

        cheques.forEach(c -> reconTransaction_forCheques(c.chequeNo,c.transactionAmount,c.getTransactionRefIDString()));

        //Run for LTA Fees
        List<Transaction> LTATxns = arr1_pendingReconRecords.stream()
                .filter(transaction -> !transaction.reconciled &&
                        transaction.transactionChannel.equals(transaction.LTA) &&
                        !transaction.refdate.isEmpty())
                .collect(Collectors.toList());

        LTATxns.forEach(l -> reconTransaction_forLTA(l.refdate,l.transactionAmount,l.getTransactionRefIDString()));

        //Run for all good matches
        List<Transaction> allOtherTxn = arr1_pendingReconRecords.stream()
                .filter(transaction -> !transaction.reconciled &&
                        transaction.transactionChannel.equals(""))
                .collect(Collectors.toList());

        allOtherTxn.forEach(a -> reconTransaction_forAllByDateAndAmount(a.transactionAmount,a.transactionDate,a.getTransactionRefIDString()));

        //Run for suggested may-matches
        List<Transaction> mayMatches = arr1_pendingReconRecords.stream()
                .filter(t -> !t.reconciled &&
                        t.transactionChannel.equals(""))
                .collect(Collectors.toList());

        mayMatches.forEach(this::reconTransaction_SuggestedMayMatches);


    }

    public void reconTransaction_forLumpsumCharges(Map<String, Double> groupSummary, String categoryname) {

        List<Transaction> gclist = array1.stream()
                .filter(t -> t.transactionChannel.equals(categoryname) &&
                        !t.reconciled)
                .collect(Collectors.toList());

        double sumOfCharges = gclist.stream().mapToDouble(Transaction::getTransactionAmount).sum();

        System.out.println(groupSummary);
        System.out.println(categoryname+"TOTAL: " + sumOfCharges);

        for (Map.Entry<String, Double> entry : groupSummary.entrySet()) {

            String datekey = entry.getKey();
            Double amtValue = entry.getValue();

            BigDecimal bd = new BigDecimal(amtValue).setScale(2, RoundingMode.HALF_UP);
            double value = bd.doubleValue();

            List<Transaction> pendingDailylist = array1.stream()
                    .filter(t -> t.transactionChannel.equals(categoryname) &&
                            t.transactionDate.equals(datekey))
                    .collect(Collectors.toList());

            pendingDailylist.forEach(l -> {
                try {
                    l.updateAsReconciled(true, "Manual User Entry for "+categoryname+" on " + l.transactionDate);
                    updateReconciledRecords(l, l.getTransactionRefIDString(), array1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public void reconTransaction_forCheques(String chq, double txnAmt, String arr1TxnRefID){

        //System.out.println(chq);
        Transaction record = array2.stream()
                .filter(transaction -> transaction.transactionDesc.contains(chq) &&
                        transaction.transactionAmount == txnAmt)
                .findAny()
                .orElse(null);


        if(record != null){
            record.chequeNo = chq;
            //printRecords(record,"RECORD");
            updateReconciledRecords(record,arr1TxnRefID,array1);
        }

    }
    public void reconTransaction_forLTA(String refDate, double txnAmt, String arr1TxnRefID){

        Transaction record = array2.stream()
                .filter(txn -> txn.transactionDate.equals(refDate) &&
                        txn.isValidDate(refDate) &&
                        !txn.reconciled &&
                        txn.transactionAmount == txnAmt)
                .findAny()
                .orElse(null);


        if(record != null) {
            record.transactionChannel = record.LTA;
            updateReconciledRecords(record, arr1TxnRefID, array1);
        }
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

          //  record.updateAsReconciled(true,arr1TxnRefID);
            updateReconciledRecords(record,arr1TxnRefID,array1);

        }
        return record;
    }
    public void reconTransaction_SuggestedMayMatches(Transaction tryMatchingWithTxn){

        Transaction record = arr2_pendingReconRecords.stream()
                .filter(txn -> txn.transactionAmount == tryMatchingWithTxn.transactionAmount &&
                        txn.transactionChannel.equals("") &&
                        !txn.reconciled)
                .findAny()
                .orElse(null);


        if(record != null){

           // tryMatchingWithTxn.updateAsReconciled(true,record.getTransactionRefIDString());
            tryMatchingWithTxn.transactionChannel = "MAY-MATCH";
            mayMatchRecords.add(tryMatchingWithTxn);

           // record.updateAsReconciled(true,tryMatchingWithTxn.getTransactionRefIDString());
            record.transactionChannel = "MAY-MATCH";
            updateReconciledRecords(record,tryMatchingWithTxn.getTransactionRefIDString(),array1);

        }
    }

    public void runReconForNETSTransactions(Map<String,Double> netsSummaryByDate) {

//        CompanyBankAccount coy = new CompanyBankAccount();
//        Map<String, Double> netsSummaryByDate = coy.getNETSSummary();

        boolean success = false;

        for (Map.Entry<String, Double> entry : netsSummaryByDate.entrySet()) {

            String netsDateKey = entry.getKey();
            Double amtValue = entry.getValue();

            BigDecimal bd = new BigDecimal(amtValue).setScale(2, RoundingMode.HALF_UP);
            double value = bd.doubleValue();

           // System.out.println("Key: " + netsDateKey + ", Value: " + value);

            Transaction txnFound = array1.stream()
                    .filter(transaction -> transaction.transactionChannel.equals(transaction.NETS) &&
                            transaction.getTransactionAmount() == value &&
                            transaction.refdate.equals(netsDateKey))
                    .findAny()
                    .orElse(null);

            if (txnFound != null) { //Match is found in bank record

                reconTransactionInArr1(txnFound, "DailyNETSSum-" + netsDateKey);
                reconTransactionInArr2_forNETS(txnFound);
                success = true;
            }
        }


    }
    public void reconTransactionInArr1(Transaction txn, String refID){
        arr1_pendingReconRecords.remove(txn);
        txn.updateAsReconciled(true,refID);
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

    public void updateReconciledRecords(Transaction matchedTxn, String reconWithRef, ArrayList<Transaction> reconWithThisArray){

        Transaction reconWithTxn = reconWithThisArray.stream()
                .filter(transaction -> transaction.getTransactionRefIDString().equals(reconWithRef))
                .findAny()
                .orElse(null);

        if(reconWithTxn != null) {
            matchedTxn.updateAsReconciled(true,reconWithTxn.getTransactionRefIDString());
            reconWithTxn.updateAsReconciled(true,matchedTxn.getTransactionRefIDString());
            arr1_pendingReconRecords.remove(reconWithTxn);
            arr2_pendingReconRecords.remove(matchedTxn);
            //System.out.println("RECON WITH" + reconWithTxn.refdate + "|"+reconWithTxn.transactionAmount);

        }

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
