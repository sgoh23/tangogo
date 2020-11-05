

import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class bankReconStepDefinitions {

    BankStatement testStmt = new BankStatement();
    CompanyBook coyStmt = new CompanyBook();

    @Given("User received a bank statement")
    public void user_received_a_bank_statement() {
        testStmt.initialThisMonthStatement();
        assertTrue(testStmt.hasBankRecords());
    }


    @Given("User has a bank record with an {double}")
    public void user_has_a_bank_record_with_an(Double amt) {
        assertTrue(testStmt.hasBankRecordWithAmt(amt));
        assertTrue(testStmt.reconciledRecords.size()>0);
    }

    @Given("User has company transaction with a {double}")
    public void user_has_company_transaction_with_a(Double amt) {
       assertTrue(coyStmt.hasTransactionWithAmt(amt));

    }


    @When("the company transaction amount and transaction date match the bank amount and transaction date")
    public void the_company_transaction_amount_and_transaction_date_match_the_bank_amount_and_transaction_date() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }


    @Then("the transaction is considered Yes regardless of transaction date")
    public void the_transaction_is_considered_yes_regardless_of_transaction_date() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}
