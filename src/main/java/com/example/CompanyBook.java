package com.example;
import java.util.ArrayList;


public class CompanyBook {

    ArrayList<Transaction> coyCashinBankRecords = new ArrayList<>();

    double coyCashinBankBalance;

    public CompanyBook() {
        initiateCoyCashinBankRecords();
    }

    public void initiateCoyCashinBankRecords(){

        coyCashinBankRecords.clear();
        coyCashinBankRecords.add(new Transaction(220.00,"01/09/2020"));
        coyCashinBankRecords.add(new Transaction(230.00,"01/09/2020"));
        coyCashinBankRecords.add(new Transaction(230.00,"02/09/2020"));
        coyCashinBankRecords.add(new Transaction(400.00,"02/09/2020"));
        coyCashinBankRecords.add(new Transaction(-100.00,"02/09/2020"));


    }

    public ArrayList<Transaction> getCoyCashinBankRecords(){
        return coyCashinBankRecords;
    }

    public int getPaymentRecordSize(){
        return coyCashinBankRecords.size();
    }


    public double getCoyCashinBankBalance(){

        coyCashinBankBalance = 0.00;
        try{
            for (Transaction record : coyCashinBankRecords) {
                coyCashinBankBalance += record.transactionAmount;
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return coyCashinBankBalance;
    }


}
