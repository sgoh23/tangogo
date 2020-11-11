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
    Given User received a bank statement for <Statement Period>
      And User has a bank record with an <Bank Credit Amount>
      And User has company transaction with a <Company Credit Amount>
      When there <is matching> amount with this <Company Credit Amount>
      Then the transaction with <Company Credit Amount> is considered <Reconciled> regardless of transaction date

      Examples:
      | Statement Period   | Bank Credit Amount | Company Credit Amount | is matching | Reconciled  |
      | "9-2020"           | 232.00             | 232.00                | true        | true        |
      | "9-2020"           | 232.00             | 300.00                | false       | false       |






