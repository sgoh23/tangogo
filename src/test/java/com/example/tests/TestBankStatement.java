package com.example.tests;

import com.example.BankStatement;
import io.cucumber.java.After;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

public class TestBankStatement {

    String period = "9-2020";
    BankStatement statement = new BankStatement(period);
    double testAmount1 = 230.00;
    String testDate = "01/09/2020";

    @Test
    public void test_Expect_BankStatement_is_notNull(){
        assertNotNull(statement);
    }

    @Test
    public void testExpectBankStatementIsForACertainPeriod(){
        statement = new BankStatement();
        assertEquals(period, new BankStatement(period).getStatementPeriod());
    }

    @Test
    public void testExpectBankStatement_withoutRecordsBeforeInitialLoad() {
        statement = new BankStatement(); //set bank statement without loading records
        assertEquals(0,statement.getBankRecordsSize());
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

    @After
    public void tearDown() {
        statement = null;
    }

}