package com.example.tests;

import com.example.BankStatement;
import com.example.CompanyBankAccount;
import com.example.ReconciliationTool;
import com.example.Transaction;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class TestReconciliationTool {

    String period = "09-2020";
    BankStatement statement = new BankStatement(period);
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
    public void expect_reconciled_records_transaction_dates_are_the_same_for_goodmatches() {

        reconKit.runRecon();

        Transaction txnFound = null;
        List<Transaction> reconciledList = reconKit.array1.stream()
                .filter(transaction -> transaction.reconciled &&
                        transaction.transactionChannel.isEmpty())
                .collect(Collectors.toList());

        if(reconKit.array1.size()>0) {
            assertNotEquals(0, reconciledList.size(),
                    "Supposed to have some matches for the good matches for "+
                    reconKit.arr1_pendingReconRecords.size() + " records in statement");
        }

        for(Transaction txn : reconciledList){
          // System.out.println(txn.transactionDesc + ":"+ txn.transactionDate);

            Transaction reconWithTxn = reconKit.array2.stream()
                    .filter(x -> x.getTransactionRefIDString().equals(txn.reconciledTxnRefID))
                    .findAny()
                    .orElse(null);

            if(reconWithTxn != null){

                assertTrue(reconWithTxn.reconciled,"Supposed to have reconciled flag marked as true");
                assertEquals(reconWithTxn.getTransactionRefIDString(),txn.reconciledTxnRefID,
                        "Supposed to have updated the reconciled transaction "+ txn + "|" +
                                txn.transactionDesc + "|" + txn.transactionAmount );

                assertEquals(reconWithTxn.transactionAmount,txn.transactionAmount,
                        "Supposed to have transaction amount match");

                assertEquals(reconWithTxn.transactionDate
                        ,txn.transactionDate,"Supposed to have transaction date match" + txn +
                                "|" + txn.transactionDesc + "|VS|" + reconWithTxn.transactionDesc + "|" + txn.transactionAmount );

                assertEquals("", reconWithTxn.transactionChannel,
                        txn + "|" + txn.transactionDate+ "|" +txn.transactionAmount+
                                "Supposed to belong to a normal transaction case, but transaction channel = "+
                                reconWithTxn.transactionChannel + ": "+
                                reconWithTxn.transactionDate +"|"+
                                reconWithTxn.transactionAmount +"|");
            }else{

                assertFalse(txn.reconciled);
                assertTrue(txn.reconciledTxnRefID.isBlank());
                assertFalse(txn.transactionChannel.isBlank());
            }

        }

    }

    @Test
    public void expect_to_reconcile_cheque_transaction_by_cheque_no(){

        reconKit.runRecon();

        List<Transaction> reconciledCheques = reconKit.array1.stream()
                .filter(transaction -> !transaction.chequeNo.isEmpty() &&
                        transaction.reconciled)
                .collect(Collectors.toList());


        //reconKit.printRecords(reconciledCheques,"Reconciled CHEQUES AND GIRO PAYMENT");

        assertTrue(reconciledCheques.size()>0,"Supposed to have some cheques reconciled");

        for(Transaction txn : reconciledCheques){
            if(txn.chequeNo != null){
                    Transaction recon = reconKit.array2.stream()
                            .filter(t -> txn.reconciledTxnRefID.equals(t.getTransactionRefIDString()) &&
                                t.chequeNo.equals(txn.chequeNo) &&
                                t.transactionAmount == txn.transactionAmount)
                            .findAny()
                            .orElse(null);

                    if(recon != null) {
                        assertEquals(txn.chequeNo, recon.chequeNo,"Cheque numbers should match" + txn.reconciledTxnRefID);
                        assertTrue(txn.reconciled, "Supposed to have a reconciled record" + txn.reconciledTxnRefID);
                        assertTrue(recon.reconciled, "Supposed to have a reconciled record" + recon.reconciledTxnRefID);
                    }
                    else {
                        assertFalse(txn.reconciled, "");
                        assertEquals("", txn.reconciledTxnRefID, "Supposed not to have a reconciled record " + txn.transactionDesc);
                    }
            }
        }

        //reconKit.printRecords(pendingCheques,"Pending Cheques - Bank");
        //reconKit.printRecords(reconKit.array2,"COY");

    }

    @Test
    public void expect_to_reconcile_LTA_transaction_by_user_reference_date(){

        reconKit.runRecon();

        List<Transaction> reconciledList = reconKit.array1.stream()
                .filter(transaction -> transaction.reconciled &&
                        transaction.transactionChannel.equals(transaction.LTA) &&
                        !transaction.refdate.isEmpty() &&
                        transaction.isValidDate(transaction.refdate))
                .collect(Collectors.toList());

        List<Transaction> LTARecords = reconKit.array1.stream()
                .filter(transaction -> transaction.transactionChannel.equals(transaction.LTA) &&
                        !transaction.refdate.isEmpty() &&
                        transaction.isValidDate(transaction.refdate))
                .collect(Collectors.toList());

        reconKit.printRecords(LTARecords,"LTA List");

        if(LTARecords.size()>0){
            assertNotEquals(0,reconciledList.size(),"Expect to have some matches since there are "+
                    LTARecords.size()+" records for LTA");
        }

        for(Transaction txn : LTARecords){

                if(txn.isValidDate(txn.refdate)){
                        Transaction recon = reconKit.array2.stream()
                                .filter(t -> t.transactionChannel.equals(t.LTA) &&
                                        t.transactionDate.equals(txn.refdate) &&
                                        t.transactionAmount == txn.transactionAmount &&
                                        t.reconciledTxnRefID.equals(txn.getTransactionRefIDString()))
                                .findAny()
                                .orElse(null);
                        if(recon != null){
                            assertEquals(txn.refdate, recon.transactionDate,"Supposed to tally date");
                            assertEquals(txn.transactionChannel,txn.LTA, "Supposed to reconcile LTA record here");
                            assertTrue(txn.reconciled,"Supposed to have a reconciled record" + txn.reconciledTxnRefID);
                            assertTrue(recon.reconciled,"Supposed to have a reconciled record" + recon.reconciledTxnRefID);
                        }
                        else{
                            assertFalse(txn.reconciled,"Suppose not to flag this as reconciled");
                            assertEquals("",txn.reconciledTxnRefID,"Supposed to have nothing in reconciled ref");
                        }
                }else{
                    assertTrue(txn.isValidDate(txn.refdate),"Supposed to have a valid date" + txn.refdate);
                }
            }

    }

    @Test
    public void expect_NETS_bank_transaction_tallywith_the_sum_of_company_NETS_transaction_per_day(){

        Map<String, Double> netsSummaryByDate = atan.getNETSSummary();
        assertNotNull(netsSummaryByDate,"Returning sum by date for all NETS transactions");

        reconKit.runReconForNETSTransactions(netsSummaryByDate);


        List<Transaction> reconciledList = reconKit.array1.stream()
                .filter(t -> t.reconciled &&
                        t.transactionChannel.equals(t.NETS))
                .collect(Collectors.toList());

        reconKit.printRecords(reconciledList,"NETS reconciled list");

        for(Transaction txn : reconciledList){

            double result = netsSummaryByDate.get(txn.refdate);

            BigDecimal bd = new BigDecimal(result).setScale(2, RoundingMode.HALF_UP);
            result = bd.doubleValue();

            assertEquals(result, txn.transactionAmount,
                    "Supposed to have NETS daily sum equals to recon amount on "+txn.refdate);
        }
    }


    @Test
    public void expect_AfterReconRun_suggests_remaining_pending_may_match_amount(){

        reconKit.runRecon();
        reconKit.runReconForNETSTransactions(atan.getNETSSummary());

        List<Transaction> mayMatches = reconKit.mayMatchRecords.stream()
                .filter(t -> t.reconciled &&
                        t.transactionChannel.equals("MAY-MATCH"))
                .collect(Collectors.toList());


        if(mayMatches.size()==0){
            assertNotEquals(0,mayMatches.size(),"Supposed to have some suggested may-matches");
        }

        for(Transaction txn : mayMatches){

            Transaction reconWithTxn = reconKit.array2.stream()
                    .filter(x -> x.getTransactionRefIDString().equals(txn.reconciledTxnRefID))
                    .findAny()
                    .orElse(null);

            if(reconWithTxn != null){

                assertTrue(reconWithTxn.reconciled,"Suppose to flag as reconciled on company");
                assertEquals(reconWithTxn.getTransactionRefIDString(),txn.reconciledTxnRefID,"Supposed to return the right transaction reference");
                assertEquals(txn.transactionAmount,reconWithTxn.transactionAmount,"Supposed to match amount");
            }else{
                assertFalse(txn.reconciled);
                assertEquals("",txn.reconciledTxnRefID);
            }

            System.out.println(reconWithTxn+"|"+reconWithTxn.transactionDesc);
            reconKit.printRecords(txn,"Reconciled May-matches");

        }


    }

    @Test
    public void expect_AfterReconRun_remove_GIRO_CHARGES_and_Sumup_forUser(){
        String categoryname = "GIRO CHARGES";

        Map<String, Double> summaryByDate = statement.getChargesSumByDate(categoryname);

        reconKit.reconTransaction_forLumpsumCharges(summaryByDate,categoryname);

        List<Transaction> reconciledlist = reconKit.array1.stream()
                .filter(t -> t.transactionChannel.equals(t.GIRO_CHARGES) &&
                        t.reconciled)
                .collect(Collectors.toList());


        assertNotEquals(0,reconciledlist.size(),"Supposed to have some Giro charges to recon");


        reconKit.printRecords(reconciledlist,"RECONCILED GIRO CHARGES");

        for (Map.Entry<String, Double> entry : summaryByDate.entrySet()) {

            String dateKey = entry.getKey();
            Double amtValue = entry.getValue();

            double sum = reconciledlist.stream()
                    .filter(transaction -> transaction.transactionDate.equals(dateKey) &&
                            transaction.reconciled)
                    .mapToDouble(Transaction::getTransactionAmount).sum();

            assertEquals(amtValue,sum,
             "Supposed to reconciled all giro charges record and tally with summary");

        }

    }

    @Test
    public void expect_AfterReconRun_remove_CASH_REBATE_and_Sumup_forUser(){

        String category = "CASH REBATE";
        Map<String, Double> summaryByDate = statement.getChargesSumByDate(category);

        reconKit.reconTransaction_forLumpsumCharges(summaryByDate,category);

        List<Transaction> reconciledlist = reconKit.array1.stream()
                .filter(t -> t.transactionChannel.equals(category) &&
                        t.reconciled)
                .collect(Collectors.toList());


        if(reconciledlist.size()==0){
            assertNotEquals(0,reconciledlist.size(),"Supposed to have some cash rebate to recon");
        }

        reconKit.printRecords(reconciledlist,"RECONCILED CASH REBATE");

        for (Map.Entry<String, Double> entry : summaryByDate.entrySet()) {

            String dateKey = entry.getKey();
            Double amtValue = entry.getValue();

            double sum = reconciledlist.stream()
                    .filter(transaction -> transaction.transactionDate.equals(dateKey) &&
                            transaction.reconciled)
                    .mapToDouble(Transaction::getTransactionAmount).sum();

            assertEquals(amtValue,sum,
                    "Supposed to reconciled all record and tally with summary");

        }

    }

    @Test
    public void expect_able_to_tell_which_records_on_array1_unable_to_reconcile(){

        reconKit.runRecon();
        reconKit.runReconForNETSTransactions(atan.getNETSSummary());
        reconKit.reconTransaction_forLumpsumCharges(statement.getChargesSumByDate("GIRO CHARGES"),"GIRO CHARGES");
        reconKit.reconTransaction_forLumpsumCharges(statement.getChargesSumByDate("CASH REBATE"), "CASH REBATE");

        reconKit.printRecordsSummary(reconKit.arr1_pendingReconRecords, reconKit.array1,"Bank Statement Pending Recon");
        reconKit.printRecords(reconKit.arr1_pendingReconRecords, "BANK STATEMENT records not found in COMPANY BOOKS");
        //reconKit.printRecords(reconKit.array1,"BANK RECORDS");


        reconKit.printRecordsSummary(reconKit.arr2_pendingReconRecords, reconKit.array2,"Company unrecorded pending ");
        reconKit.printRecords(reconKit.arr2_pendingReconRecords,"COMPANY BOOKS records not found in BANK STATEMENT");
        //reconKit.printRecords(reconKit.array2,"COMPANY BOOKS");

    }

}