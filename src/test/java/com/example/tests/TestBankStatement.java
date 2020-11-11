package com.example.tests;

import com.example.BankStatement;
import com.example.Transaction;
import io.cucumber.java.After;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

public class TestBankStatement {

    String period = "9-2020";
    BankStatement statement = new BankStatement(period);
    double testAmount1 = 232.00;
    String testDate = "01/09/2020";


    Transaction testTxn = new Transaction(testAmount1,testDate);

    @Test
    public void testExpectBankStatementIsForACertainPeriod(){
        statement = new BankStatement();
        Assertions.assertEquals(period, new BankStatement(period).getStatementPeriod());
    }

    @Test
    public void testExpectBankStatement_withoutRecordsBeforeInitialLoad() {
        statement = new BankStatement(); //set bank statement without loading records
        Assertions.assertEquals(0,statement.getBankRecordsSize());
    }

    @Test
    public void testExpectBankStatementInitialLoadSuccessful(){
        assertTrue(statement.initialThisMonthStatement(period) > 0);
    }

    @Test
    public void testExpectBankStatement_withRecordsAfterInitialLoad() {
        statement.initialThisMonthStatement(period);
        assertTrue(statement.getBankRecordsSize()>0);
    }

    @Test
    public void testExpectBothReconAndUnReconRecordsEqualsToInitialBankStatement() {
        statement.setReconcileRecord(testTxn);
        Assertions.assertEquals(statement.getBankRecordsSize(), (statement.getUnreconciledRecordsSize()+statement.getReconciledRecordSize()));
    }

    @Test
    public void testExpectReconciledRecordsAreNotFoundInUnreconciledRecords(){
        statement.setReconcileRecord(testTxn);
        Assertions.assertTrue(statement.containTransactionInReconciledRecords(testTxn));
        Assertions.assertFalse(statement.containTransactionInUnreconciledRecords(testTxn));
    }

    @Test
    public void testExpectBankRecordsStaySameAsInitialBankStatementLoad(){
       // statement.initialThisMonthStatement(period);
        statement.setReconcileRecord(testTxn);
        Assertions.assertEquals(statement.getBankRecordsSize(),statement.initialThisMonthStatement(statement.statementPeriod));
    }

    @Test
    public void testExpectBankStatement_isForTheWholeMonth() {

        //As September 2020
        Calendar startDate = Calendar.getInstance();
        startDate.set(2020, Calendar.SEPTEMBER, 1, 0, 0, 0);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2020, Calendar.SEPTEMBER, 30, 0, 0, 0);

        assertEquals("9-2020", statement.setStatementPeriod(9, 2020));
        assertNotNull(statement.statementStartDate);
        assertNotNull(statement.statementEndDate);
    }

    @Test
    public void testExpectToFindInRecordSameAmountToReconcile() {
      // statement.initialThisMonthStatement(period);
       assertTrue(statement.findUnreconciledBankRecordWithAmt(testAmount1)>0);

    }

    @Test
    public void testExpectUnreconciledRecordsPlusReconciledRecordsEqualsBankStatementRecords() {
     //   statement.initialThisMonthStatement(period);
        Assertions.assertEquals(statement.getBankRecordsSize(), (statement.getUnreconciledRecordsSize() + statement.getReconciledRecordSize()));
    }

    @After
    public void tearDown() {
        statement = null;
    }

}