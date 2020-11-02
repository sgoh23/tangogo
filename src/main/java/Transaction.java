import java.util.Arrays;

public class Transaction {

    double transactionAmount = 0.00;
    String transactionDate = "";
    String transactionType = "";
    String transactionID = "";

    Transaction(){

    }

    Transaction(double txnAmt, String txnDate){

        transactionAmount = txnAmt;
        transactionDate = txnDate;
    }

    Boolean checkIfAmountReportedInBankStatement (double bankAmt){

        try
        {
            BankStatement thisMonthStatement = new BankStatement();
            thisMonthStatement.initialThisMonthStatement();

            return thisMonthStatement.containsThisTxnAmount(bankAmt);

        } catch (Exception e){

            System.out.println("Tangogo Opps! something went wrong"+ Arrays.toString(e.getStackTrace()));
            return false;
        }

    }



}
