package com.example;
import java.util.ArrayList;


public class CompanyBook {

    ArrayList<Transaction> coyCashinBankRecords = new ArrayList<>();
    ArrayList<Transaction> reconciledRecords = new ArrayList<>();
    ArrayList<Transaction> unreconciledRecords = new ArrayList<>();

    double coyCashinBankBalance;

    public CompanyBook() {
        initiateCoyCashinBankRecords();
    }

  //  private double getAccAsOf(){

   // }

    public void initiateCoyCashinBankRecords(){
        coyCashinBankRecords.clear();
        coyCashinBankRecords.add(new Transaction(220.00,"01/09/2020"));
        coyCashinBankRecords.add(new Transaction(230.00,"01/09/2020"));
        coyCashinBankRecords.add(new Transaction(230.00,"02/09/2020"));
        coyCashinBankRecords.add(new Transaction(400.00,"02/09/2020"));
        coyCashinBankRecords.add(new Transaction(-100.00,"02/09/2020"));

        initiateCoyRecords();
        getCoyRecordsTally();

    }

    public void initiateCoyRecords() throws ClassCastException{
        unreconciledRecords =  (ArrayList<Transaction>) coyCashinBankRecords.clone();
    }

    public ArrayList<Transaction> getCoyCashinBankRecords(){
        return coyCashinBankRecords;
    }

    public int getPaymentRecordSize(){
        return coyCashinBankRecords.size();
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

        Transaction unrecordedTxn = new Transaction(bankAmt);
        unreconciledRecords.add(unrecordedTxn); //add unrecon record if cannot find in COY acc
        return unrecordedTxn;

    }

    public Boolean containTransactionInUnreconciledRecords(Transaction txn){ return unreconciledRecords.contains(txn); }
    public void printRecords(){
        Print.theseRecords(unreconciledRecords,"Unreconciled records");
    }

    public double getCoyRecordsTally(){

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
