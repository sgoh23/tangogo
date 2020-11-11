package com.example;

import java.util.ArrayList;
import java.util.Calendar;

public class BankStatement extends Transaction{

    ArrayList<Transaction> bankRecords = new ArrayList<>();
    ArrayList<Transaction> unreconciledRecords = new ArrayList<>();
    ArrayList<Transaction> reconciledRecords = new ArrayList<>();

    public Calendar statementStartDate = Calendar.getInstance();
    public Calendar statementEndDate = Calendar.getInstance();
    public String statementPeriod = null;

//   public static void main(String[] args) {
//
//        BankStatement bs = new BankStatement();
//        bs.setStatementPeriod(9,2020);
//        bs.initialThisMonthStatement(bs.statementPeriod);
//           bs.printRecordList(bs.bankRecords,"Bank Records");
//           bs.printRecordList(bs.reconciledRecords,"Reconciled Records");
//           bs.printRecordList(bs.unreconciledRecords,"UNReconciled Records");
//
//       int i = bs.findUnreconciledBankRecordWithAmt(232.00);
//        bs.printRecordList(bs.bankRecords,"Bank Records");
//        bs.printRecordList(bs.reconciledRecords,"Reconciled Records");
//        bs.printRecordList(bs.unreconciledRecords,"UNReconciled Records");
//
//    }
    public BankStatement(){

    }

    public BankStatement(String period){

        int month = Integer.parseInt(period.substring(0,period.indexOf("-"))); //to get month and convert to int;
        int year = Integer.parseInt(period.substring(period.indexOf("-")+1)); //to get year and convert to int;
        statementPeriod = setStatementPeriod(month,year);
        initialThisMonthStatement(statementPeriod);
    }


    public String setStatementPeriod(int mth, int year){

        statementPeriod = mth + "-" + year;
        statementStartDate.set(year,mth-1,1,0,0,0);
        statementEndDate.set(year,mth-1,statementStartDate.getActualMaximum(Calendar.DATE),0,0,0);
   //     System.out.println("Statement period is set starting "+ statementStartDate.getTime()+ " TO "+ statementEndDate.getTime());

        return statementPeriod;
    }

    public String getStatementPeriod(){
        return statementPeriod;
    }

    public int initialThisMonthStatement(String period){

        //hard upload first, implement the excel reading later
        // bankRecords.put("DD/MM/YYYY",0.00);
        bankRecords.clear();
        bankRecords.add(new Transaction(260.00,"01/09/2020"));
        bankRecords.add(new Transaction(232.00,"01/09/2020"));
        bankRecords.add(new Transaction(232.00,"02/09/2020"));
        bankRecords.add(new Transaction(200.00,"01/09/2020"));
        bankRecords.add(new Transaction(814.45,"01/09/2020"));

        initialUnreconciledRecords();

        return bankRecords.size();
    }

    public void initialUnreconciledRecords(){
        unreconciledRecords =  (ArrayList<Transaction>) bankRecords.clone();

    }

    public int getUnreconciledRecordsSize(){
        return unreconciledRecords.size();
    }

    public int getReconciledRecordSize(){
        return reconciledRecords.size();
    }
    public int getBankRecordsSize(){
        return bankRecords.size();
    }

    public int findUnreconciledBankRecordWithAmt(double bankAmt){

        int recordIndex = 0;

        try{
            for (Transaction record : unreconciledRecords) {
                if (record.transactionAmount == bankAmt) {
                    System.out.println("Record:"+record+" Update recon For BankAmt: "+bankAmt+"...Found in record:"+unreconciledRecords.get(recordIndex).transactionAmount+" Dated: "+unreconciledRecords.get(recordIndex).transactionDate);
                    setReconcileRecord(record);
                    break;
                }
                recordIndex++;
            }


        }catch(Exception e){
            e.printStackTrace();
        }
        return recordIndex;

    }

    public Boolean setReconcileRecord(Transaction txn){
        boolean recon = false;

        reconciledRecords.add(txn);
        recon = removeFromUnreconciledList(txn.transactionAmount);

        if(unreconciledRecords.size() == bankRecords.size()-reconciledRecords.size()){
            recon = true;
        }
        return recon;
    }

    public Boolean removeFromUnreconciledList(double amt) {
        boolean removeRecord = false;

        try {

            int index = 0;

            for (int i = 0; i < unreconciledRecords.size(); i++) {
                if (unreconciledRecords.get(i).transactionAmount == amt) {
                    index = i;
                    removeRecord=true;
                    break; //reconcile the first record
                }
            }

            unreconciledRecords.remove(index);

        } catch(Exception e){
            e.printStackTrace();
        }
        return removeRecord;

    }

    public Boolean containTransactionInReconciledRecords(Transaction txn){
        return reconciledRecords.contains(txn);
    }

    public Boolean containTransactionInUnreconciledRecords(Transaction txn){
        return unreconciledRecords.contains(txn);
    }

    private void printRecordList(ArrayList<Transaction> records, String listname){

       System.out.println("||||||||| PRINTING RECORDS ||||||||| :::"+listname+" Record Count: ("+records.size()+")");

       for (int i = 0; i < records.size(); i++) {
           System.out.println(records.get(i).toString()+" | "+records.get(i).transactionAmount);
       }
        System.out.println(records);

    }

}
