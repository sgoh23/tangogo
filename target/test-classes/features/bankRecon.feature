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
    @AllTest
    Scenario: Exact Amount Match on Bank's transaction against Company's transaction
    Given User received a bank statement for "9-2020"
      And the bank statement has a list of transactions
      When reconciling bank record with company record
      Then the transaction is considered reconciled

    @AllTest
    Scenario: Bank transaction is not recorded in Company books
      Given User received a bank statement for "9-2020"
      When bank statement has an amount 360.00 record on "02/09/2020" that is not in Company Books
      Then transaction is unreconciled in Bank statement

    @AllTest
    Scenario: Company transaction in that period not found in Bank statement
      Given User received a bank statement for "9-2020"
      When Company Book has an amount 220.00 recorded on "01/09/2020" and not found in bank statement
      Then transaction is unreconciled in Company book

    @OCBCReconTest
    Scenario: Try loading Test data for a recon run
      Given User received a bank statement from OCBC for "10-2020"
      When  the bank statement has a list of transactions
      And user took company Bank account records
      And reconciling bank record with company record
      Then the transaction is considered reconciled

#    Scenario: Bank Statement closing balance compare with Company Book for the period
#      Given User received a bank statement for "9-2020"
#      When the closing balance of all records added together on Bank Statement
#      And compare with the balance of Company book "Cash in OCBC" account as of the period
#      Then the difference of both balances should ideally tally with the sum of pending recon amount on Company book
#      When difference does not tally
#      Then add a "Lumpsum unreconciled amount"










