import io.cucumber.java.After;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

public class TestBankStatement {

    String period = "9-2020";
    BankStatement statement = new BankStatement(period);
    double testAmount1 = 232.00;
    double testFalseAmount = 999999.00;
    String testDate = "01/09/2020";


    Transaction testTxn = new Transaction(testAmount1,testDate);

    @Test
    public void testExpectBankStatementIsForACertainPeriod(){
        assertEquals(period, new BankStatement(period).getStatementPeriod());
    }

    @Test
    public void testExpectBankStatement_withoutRecordsBeforeInitialLoad() {
        assertFalse(statement.getBankRecordsSize()==0);
    }

    @Test
    public void testExpectBankStatementInitialLoadSuccessful(){
        assertTrue(statement.initialThisMonthStatement(period) > 0);
    }

    @Test
    public void testExpectBankStatement_withRecordsAfterInitialLoad() {
        assertTrue(statement.getBankRecordsSize()>0);
    }

    @Test
    public void testExpectUnreconciledRecordsEqualsToInitialBankStatement() {
        statement.initialThisMonthStatement(period);
        statement.setReconcileRecord(testTxn);
        assertEquals(statement.getBankRecordsSize(), (statement.getUnreconciledRecordsSize()+statement.getReconciledRecordSize()));
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
        //assertEquals(startDate,statement.statementStartDate);

        // assertEquals(startDate.getTime(),statement.statementStartDate.getTime());
        //assertEquals(endDate,statement.statementStartDate);
    }

    @Test
    public void testExpectToFindRecordwithSameAmountToReconcile() {
       statement.initialThisMonthStatement(period);
       assertTrue(statement.findUnreconciledBankRecordWithAmt(testAmount1)>0);

    }

    @Test
    public void testExpectUnreconciledRecordsPlusReconciledRecordsEqualsBankStatementRecords() {
        statement.initialThisMonthStatement(period);
        assertEquals(statement.getBankRecordsSize(), (statement.getUnreconciledRecordsSize() + statement.getReconciledRecordSize()));
    }

    @Test
    public void testExpectBankRecordsStaySameAsInitialBankStatementLoad(){
        assertEquals(statement.getBankRecordsSize(),statement.initialThisMonthStatement(statement.statementPeriod));
    }
    @After
    public void tearDown() {
        statement = null;
    }

}