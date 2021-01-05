package com.example.cucumber;

import com.example.BankStatement;
import com.example.CompanyBook;
import com.example.ReconciliationTool;
import com.example.Transaction;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class bankReconStepDefinitions {

    BankStatement testStmt = new BankStatement();
    CompanyBook atan = new CompanyBook();
    ReconciliationTool reconKit = new ReconciliationTool(testStmt.getBankRecords(),atan.getCoyCashinBankRecords());
    Transaction stmtRecord = new Transaction();
    Transaction companyRecord = new Transaction();

    @Given("User received a bank statement for {string}")
    public void user_received_a_bank_statement(String period) {
        testStmt.initialThisMonthStatement(period);
        assertTrue(testStmt.getBankRecordsSize()>0);
    }

    @Given("the bank statement has a list of transactions")
    public void the_bank_statement_has_a_list_of_transactions() {
        ArrayList<Transaction> testStmtBankRecords = testStmt.getBankRecords();
        assertNotEquals(0,testStmtBankRecords.size());
    }

    @When("reconciling bank record with company record")
    public void reconciling_bank_record_with_company_record() {
        reconKit.runReconBasedOnArr1();
    }

    @Then("the transaction is considered reconciled")
    public void the_transaction_is_considered_reconciled() {
        assertNotEquals(reconKit.array1.size(), reconKit.arr1_pendingReconRecords.size());
    }

    @When("bank statement has an amount {double} record that is not in Company Books")
    public void bank_statement_has_an_amount_record_that_is_not_in_company_books(Double double1) {

        reconKit.initiateCheckList();
        stmtRecord = reconKit.getArr1PendingReconTxn(double1);
        assertNotNull(stmtRecord,"Bank statement has this amount: " + double1);

        Transaction txn = reconKit.reconTransactionInArr2(double1,stmtRecord.toString());
        assertNull(txn, "Company Books does not have this record amount nor transaction");
    }

    @Then("transaction is unreconciled in Bank statement")
    public void transaction_is_unreconciled_in_bank_statement() {
        assertFalse(stmtRecord.reconciled,"Transaction is marked as not reconciled");
    }

    @When("Company Book has an amount {double} recorded and not found in bank statement")
    public void company_book_has_an_amount_recorded_and_not_found_in_bank_statement(Double double1) {
        Transaction companyRecord = reconKit.reconTransactionInArr2(double1,"Test@Transaction");
        assertNotNull(companyRecord,"Company has a record amount: "+double1);

       Transaction txn = reconKit.getArr1PendingReconTxn(double1);
       assertNull(txn, "Bank Statement does not have this record");
    }

    @Then("transaction is unreconciled in Company book")
    public void transaction_is_unreconciled_in_company_book() {
        assertFalse(companyRecord.reconciled,"Transaction is marked as not reconciled in company books");
    }



}
