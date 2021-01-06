# Background:
#  Whenever Kenneth (User) reconcile the bank statement transactions
#  With the transactions in the company, he has to manually read the statement and
#  Create the transactions into the company's accounting system

#  In order to reduce user's (I) manual time to eye-ball bank statement
#  and match the accounting system's transactions,
#  I want to match transactions by the transaction by amount
#  and be prompted with intelligence
#  for the remaining transactions are for certain purposes

  Feature: Bank account reconciliation

    Scenario: Exact Amount Match on Bank's transaction against Company's transaction
    Given User received a bank statement for "9-2020"
      And the bank statement has a list of transactions
      When reconciling bank record with company record
      Then the transaction is considered reconciled

    Scenario: Bank transaction is not recorded in Company books
      Given User received a bank statement for "9-2020"
      When bank statement has an amount 360.00 record that is not in Company Books
      Then transaction is unreconciled in Bank statement

    Scenario: Company transaction in that period not found in Bank statement
      Given User received a bank statement for "9-2020"
      When Company Book has an amount 220.00 recorded and not found in bank statement
      Then transaction is unreconciled in Company book








