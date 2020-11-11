package com.example.cucumber;

import com.example.BankStatement;
import com.example.CompanyBook;
import com.example.Transaction;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.*;

public class bankReconStepDefinitions {

    BankStatement testStmt = new BankStatement();
    CompanyBook coyStmt = new CompanyBook();
    Transaction txn = new Transaction();

    @Given("User received a bank statement for {string}")
    public void user_received_a_bank_statement(String period) {
        testStmt.initialThisMonthStatement(period);
        assertTrue(testStmt.getBankRecordsSize()>0);
    }

    @Given("User has a bank record with an {double}")
    public void user_has_a_bank_record_with_an(Double amt) {
        txn = testStmt.getUnreconciledRecordWithAmt(amt);
        assertNotNull(txn);
    }

    @Given("User has company transaction with a {double}")
    public void user_has_company_transaction_with_a(Double amt) {
        assertTrue(coyStmt.hasTransactionWithAmt(amt));
    }

    @When("there is a matching amount with this {double}")
    public void there_is_a_matching_amount(Double amt1) {
        assertTrue(testStmt.setReconcileRecord(txn));
        assertTrue(testStmt.getReconciledRecordSize()>0);
    }

    @Then("the transaction with {double} is considered true regardless of transaction date")
    public void the_transaction_with_is_considered_true_regardless_of_transaction_date(Double amt) {
        assertTrue(testStmt.containTransactionInReconciledRecords(txn));
        assertFalse(testStmt.containTransactionInUnreconciledRecords(txn));
        assertTrue(testStmt.containTransactionInBankRecords(txn));
    }
}
