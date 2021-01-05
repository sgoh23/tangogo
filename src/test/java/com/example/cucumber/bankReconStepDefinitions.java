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
    Transaction coytxn = new Transaction();

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
        coytxn = coyStmt.getUnreconciledRecordWithAmt(amt);
        assertNotNull(coytxn);
    }

    @When("there true amount with this {double}")
    public void there_true_amount_with_this(Double double1) {
        txn = testStmt.getUnreconciledRecordWithAmt(double1);
        assertNotNull(txn);
        assertTrue(testStmt.setReconcileRecord(txn));
        assertTrue(testStmt.getReconciledRecordSize() > 0);

    }

    @When("there false amount with this {double}")
    public void there_false_amount_with_this(Double double1) {
        txn = testStmt.getUnreconciledRecordWithAmt(double1);
        coytxn = coyStmt.getUnreconciledRecordWithAmt(double1);
        assertNull(txn);
    }

    @Then("the transaction with {double} is considered true regardless of transaction date")
    public void the_transaction_with_is_considered_true_regardless_of_transaction_date(Double amt) {
        assertTrue(testStmt.containTransactionInReconciledRecords(txn));
        assertFalse(testStmt.containTransactionInUnreconciledRecords(txn));
        assertTrue(testStmt.containTransactionInBankRecords(txn));
    }

    @Then("the transaction with {double} is considered false regardless of transaction date")
    public void the_transaction_with_is_considered_false_regardless_of_transaction_date(Double double1) {
        assertFalse(testStmt.containTransactionInReconciledRecords(txn));
        assertFalse(testStmt.containTransactionInUnreconciledRecords(txn));
        assertFalse(testStmt.containTransactionInBankRecords(txn));
        assertTrue(coyStmt.containTransactionInUnreconciledRecords(coytxn));
    }

    @When("User has no record of {double} in Company books")
    public void user_has_no_records_it_in_company_books(Double amt) {
        coytxn = coyStmt.getUnreconciledRecordWithAmt(amt);
        assertNotNull(coytxn);
    }

    @Then("Transaction is logged for User to record as unreconciled in Company books")
    public void transaction_is_logged_for_user_to_record_as_unreconciled_in_company_books() {
        assertTrue(coyStmt.containTransactionInUnreconciledRecords(coytxn));
    }
}
