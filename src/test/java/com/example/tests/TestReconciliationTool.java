package com.example.tests;

import com.example.BankStatement;
import com.example.CompanyBook;
import com.example.ReconciliationTool;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestReconciliationTool {

    BankStatement statement = new BankStatement("09-2020");
    CompanyBook atan = new CompanyBook();
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
        reconKit.initiateArr1CheckList();
        reconKit.runReconBasedOnArr1();
        reconKit.printRecords(reconKit.arr1_pendingReconRecords, "BANK STATEMENT records not found in COMPANY BOOKS");
        reconKit.printRecords(reconKit.arr2_pendingReconRecords,"COMPANY BOOKS records not found in BANK STATEMENT");
    }


}