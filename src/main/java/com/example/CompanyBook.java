package com.example;
import java.util.ArrayList;

public class CompanyBook {

    ArrayList<Transaction> paymentRecords = new ArrayList<>();
    ArrayList<Transaction> reconciledRecords = new ArrayList<>();
    ArrayList<Transaction> unreconciledRecords = new ArrayList<>();

    String chartOfAccount = null;

    public CompanyBook() {
        chartOfAccount = "Cash in OCBC";
        setBeginningBalance();
    }

    public void setBeginningBalance(){
        paymentRecords.clear();
        paymentRecords.add(new Transaction(232.00,"01/09/2020"));
     //   paymentRecords.add(new Transaction(300.00,"01/09/2020"));
      //  paymentRecords.add(new Transaction(300.00,"02/09/2020"));

        initialUnreconciledRecords();
    }

    public void initialUnreconciledRecords() throws ClassCastException{
        unreconciledRecords =  (ArrayList<Transaction>) paymentRecords.clone();
    }

    public ArrayList<Transaction> getPaymentRecords(){
        return paymentRecords;
    }

    public int getPaymentRecordSize(){
        return paymentRecords.size();
    }

    public Transaction getUnreconciledRecordWithAmt(double bankAmt){

        int recordIndex = 0;

        try{
            for (Transaction record : unreconciledRecords) {
                if (record.transactionAmount == bankAmt) {
                    //System.out.println("Record:"+record+" Update recon For BankAmt: "+bankAmt+"...Found in record:"+unreconciledRecords.get(recordIndex).transactionAmount+" Dated: "+unreconciledRecords.get(recordIndex).transactionDate);
                    //setReconcileRecord(record);
                    return record;
                    // break;
                }
                recordIndex++;
            }


        }catch(Exception e){
            e.printStackTrace();
        }

        unreconciledRecords.add(new Transaction(bankAmt)); //add unrecon record if cannot find in COY acc
        return unreconciledRecords.get(unreconciledRecords.size()-1);

    }

    public Boolean containTransactionInUnreconciledRecords(Transaction txn){ return unreconciledRecords.contains(txn); }



}
