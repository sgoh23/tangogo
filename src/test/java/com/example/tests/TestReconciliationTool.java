package com.example.tests;

import com.example.BankStatement;
import com.example.CompanyBankAccount;
import com.example.ReconciliationTool;
import com.example.Transaction;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        reconKit.runRecon();

        reconKit.printRecordsSummary();

        reconKit.printRecords(reconKit.arr1_pendingReconRecords, "BANK STATEMENT records not found in COMPANY BOOKS");
        reconKit.printRecords(reconKit.arr2_pendingReconRecords,"COMPANY BOOKS records not found in BANK STATEMENT");


    }

    @Test
    public void expect_reconciled_records_transaction_dates_are_the_same_for_goodmatches() {

        reconKit.runRecon();

        for(Transaction txn : reconKit.array1){
            if(txn.reconciled && txn.chequeNo == null && txn.refdate == null){
                Transaction txnFound = reconKit.getTransactionFromArray2(txn.reconciledTxnRefID);
                assertNotNull(txnFound);
             //   System.out.println(txnFound + " | " + txnFound.transactionDate + " | " +txnFound.transactionAmount + " | " + txnFound.chequeNo);
                assertEquals(txn.transactionDate,txnFound.transactionDate);

            }
        }
    }

    @Test
    public void expect_to_reconcile_cheque_transaction_by_cheque_no(){

        reconKit.runRecon();

        for(Transaction txn : reconKit.arr1_pendingReconRecords){
            try{
                if(txn.chequeNo != null){
                    reconKit.reconTransactionInArr2_forCheques(txn.chequeNo,txn.transactionAmount,txn.getTransactionRefIDString());

                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Test
    public void expect_to_reconcile_LTA_transaction_by_user_reference_date(){

        reconKit.runRecon();

        for(Transaction txn : reconKit.arr1_pendingReconRecords){
            if(txn.refdate != null){
                reconKit.reconTransactionInArr2_forLTA(txn.refdate, txn.transactionAmount, txn.getTransactionRefIDString());
            }
        }
    }

    @Test
    public void expect_NETS_bank_transact_the_sum_of_company_transaction_per_day(){

        reconKit.runRecon();

        List<Transaction> arr2_pendingNets = reconKit.arr2_pendingReconRecords.stream()
                .filter(x -> "NETS".equals(x.getTransactionChannel()))
                .collect(Collectors.toList());

       // System.out.println(arr2_pendingNets.stream().);
        
        Map<String, Double> sumByDate = arr2_pendingNets.stream().collect(
                    Collectors.groupingBy(Transaction::getTransactionDate,
                            Collectors.summingDouble(Transaction::getTransactionAmount)));


        System.out.println(sumByDate);


    }



}