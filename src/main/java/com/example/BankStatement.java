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
    public Double statementClosingBalance = null;

   public static void main(String[] args) {

        BankStatement bs = new BankStatement("09-2020");
        bs.initialThisMonthStatement(bs.statementPeriod);

        CompanyBook atanBooks = new CompanyBook();
        System.out.println("Atan has $"+atanBooks.coyCashinBankBalance+ " in Bank");

    }
    public BankStatement(){

    }

    public BankStatement(String period){

        int month = Integer.parseInt(period.substring(0,period.indexOf("-"))); //to get month and convert to int;
        int year = Integer.parseInt(period.substring(period.indexOf("-")+1)); //to get year and convert to int;
        statementPeriod = setStatementPeriod(month,year);
        initialThisMonthStatement(statementPeriod);
        System.out.println("Bank Statement ("+statementPeriod+") has Closing Balance of $"+statementClosingBalance);
    }


    public String setStatementPeriod(int mth, int year){

        statementPeriod = mth + "-" + year;
        statementStartDate.set(year,mth-1,1,0,0,0);
        statementEndDate.set(year,mth-1,statementStartDate.getActualMaximum(Calendar.DATE),0,0,0);

        return statementPeriod;
    }

    public String getStatementPeriod(){
        return statementPeriod;
    }

    public int initialThisMonthStatement(String period){

        //hard upload first, implement the excel reading later
        // bankRecords.put("DD/MM/YYYY",0.00);
        bankRecords.clear();
        bankRecords.add(new Transaction(230.00,"01/09/2020"));
        bankRecords.add(new Transaction(230.00,"02/09/2020"));
        bankRecords.add(new Transaction(360.00,"02/09/2020"));
        bankRecords.add(new Transaction(400.00,"02/09/2020"));
        bankRecords.add(new Transaction(-100.00,"02/09/2020"));

     //   initialUnreconciledRecords();
        getBankRecordsBalance();

        return bankRecords.size();
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
    public ArrayList<Transaction> getBankRecords(){
       return bankRecords;
    }

    public Transaction getUnreconciledRecordWithAmt(double bankAmt){

        int recordIndex = 0;

        try{
            for (Transaction record : unreconciledRecords) {
                if (record.transactionAmount == bankAmt) {
                    return record;
                }
                recordIndex++;
            }


        }catch(Exception e){
            e.printStackTrace();
        }
        return null;

    }

    public Boolean setReconcileRecord(Transaction txn){

        boolean recon = false;

        recon = reconciledRecords.add(txn);

        System.out.println(" | Reconciliation Record:"+txn+" | BankAmt: "+txn.transactionAmount);
        System.out.println(" | Found in record:"+ unreconciledRecords.get(unreconciledRecords.indexOf(txn)).toString()+
                " | Amount: "+unreconciledRecords.get(unreconciledRecords.indexOf(txn)).transactionAmount+
                " | Dated: "+unreconciledRecords.get(unreconciledRecords.indexOf(txn)).transactionDate);

        recon = removeFromUnreconciledList(txn.transactionAmount);


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
    public Boolean containTransactionInUnreconciledRecords(Transaction txn){ return unreconciledRecords.contains(txn); }
    public Boolean containTransactionInBankRecords(Transaction txn){ return bankRecords.contains(txn); }

    public double getBankRecordsBalance(){

        statementClosingBalance = 0.00;
        try{
            for (Transaction record : bankRecords) {
                statementClosingBalance += record.transactionAmount;
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return statementClosingBalance;
    }



}
