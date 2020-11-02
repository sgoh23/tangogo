import io.cucumber.java8.*;

public class bankReconStepDefinitions implements En {

    public bankReconStepDefinitions() {

        Given("^User has a bank transaction with a <Bank Amount> and <Statement Date>$",
                (Double bankAmt, String bankDate ) -> {

            try {

                Transaction bankTxn1 = new Transaction();
                assert bankTxn1.checkIfAmountReportedInBankStatement(bankAmt) : "amount not found in bank statement";
               // assert bankTxn1.matchesTxnDateInBankStatement(bankAmt,bankDate) : "Bank Date do not match";

            }catch (Exception e) {

                System.out.println("Something went wrong.");
            }

            throw new io.cucumber.java8.PendingException();

        });

    }
}
