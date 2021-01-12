package com.example.tests;

import com.example.BankStatement;
import com.example.CompanyBankAccount;
import com.example.ReconciliationTool;
import com.example.Transaction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestReconciliationTool {

    BankStatement statement = new BankStatement("09-2020");
    CompanyBankAccount atan = new CompanyBankAccount();
    ReconciliationTool reconKit = new ReconciliationTool(statement.getBankRecords(),atan.getCoyCashinBankRecords());

    @Test
    public void expect_reconciliation_is_for_two_set_of_records(){
        assertNotNull(reconKit.array1);
        assertNotEquals(0,reconKit.array1.size());
        assertNotNull(reconKit.array2);
        assertNotEquals(0,reconKit.array2.size());

    }


    @Test
    public void expect_able_to_pass_in_bankrecords_and_companyrecords(){
        reconKit = new ReconciliationTool(statement.getBankRecords(), atan.getCoyCashinBankRecords());
        assertNotNull(reconKit);
    }

    @Test
    public void expect_able_to_compare_balance_of_two_set_of_records() {
        double balanceOfArr1 = reconKit.getArray1Balance();
        double balanceOfArr2 = reconKit.getArray2Balance();
        double diffOfBalances = balanceOfArr1 - balanceOfArr2;

        assertNotEquals(0.00,diffOfBalances,"Reconciliation difference = "+diffOfBalances);
    }

    @Test
    public void expect_able_to_tell_which_records_on_array1_unable_to_reconcile() {
        reconKit.runReconBasedOnArr1();

        reconKit.printRecordsSummary();

        reconKit.printRecords(reconKit.arr1_pendingReconRecords, "BANK STATEMENT records not found in COMPANY BOOKS");
        reconKit.printRecords(reconKit.arr2_pendingReconRecords,"COMPANY BOOKS records not found in BANK STATEMENT");


    }

    @Test
    public void expect_reconciled_records_transaction_dates_are_the_same() {

        reconKit.runReconBasedOnArr1();

        for(Transaction txn : reconKit.array1){
            if(txn.reconciled){
                Transaction txnFound = reconKit.getTransactionFromArray2(txn.reconciledTxnRefID);
                assertNotNull(txnFound);
                assertEquals(txn.transactionDate,txnFound.transactionDate);
               // System.out.println(txnFound + " | " + txnFound.transactionDate + " | " +txnFound.transactionAmount);
            }
        }
    }

}