package com.example;

import java.util.ArrayList;
import java.util.Calendar;

public class BankStatement extends Transaction{

    ArrayList<Transaction> bankRecords = new ArrayList<>();

    public Calendar statementStartDate = Calendar.getInstance();
    public Calendar statementEndDate = Calendar.getInstance();
    public String statementPeriod = null;
    public Double statementClosingBalance = null;

//   public static void main(String[] args) {
//
//        BankStatement bs = new BankStatement("09-2020");
//        bs.initialThisMonthStatement(bs.statementPeriod);
//
//        CompanyBook atanBooks = new CompanyBook();
//        System.out.println("Atan has $"+atanBooks.coyCashinBankBalance+ " in Bank");
//
//    }
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

        getBankRecordsBalance();
        return bankRecords.size();
    }

    public int getBankRecordsSize(){
        return bankRecords.size();
    }
    public ArrayList<Transaction> getBankRecords(){
       return bankRecords;
    }

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
