# Background:
#  Whenever Kenneth (User) reconcile the bank statement transactions
#  With the transactions in the company, he has to manually read the statement and
#  Create the transactions into the company's accounting system

#  In order to reduce user's (I) manual time to eye-ball bank statement
#  and match the accounting system's transactions,
#  I want to match transactions by the transaction by amount
#  and be prompted with intelligence
#  for the remaining transactions are for certain purposes

  Feature: Auto bank reconciliation

    Scenario Outline: Exact Amount Match on Bank's transaction against Company's transaction
    Given User has a bank transaction with a <Bank Amount> and <Statement Date>
      And User has company transaction with a <Transaction Amount> and <Transaction Date>
      When the company transaction amount and transaction date match the bank amount and transaction date
      Then the <Recon Status> is set

      Examples:
      | Bank Amount | Statement Date | Transaction Amount | Transaction Date | Recon Status |
      | 232.00 | "01/09/2020" | 232.00 | "01/09/2020" | "Matched" |
      #| 232.00 | "01/09/2020" | 300.00 | "01/09/2020" | Pending a match |
      #| 232.00 | "01/09/2020" | 232.00 | "02/09/2020" | Pending a match |






