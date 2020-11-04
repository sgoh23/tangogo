import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestBankStatement {
    BankStatement statement = new BankStatement();

    @Test
    public void testExpectBankStatement_withoutRecordsBeforeInitialLoad() {
        assertEquals(0, statement.bankRecords.size());
    }

    @Test
    public void testExpectBankStatement_withRecordsAfterInitialLoad() {
        statement.initialThisMonthStatement();
        assertTrue(statement.bankRecords.size()>0);
    }

    @Test
    public void testExpectBankStatement_isForTheWholeMonth() {

        //As September 2020
        Calendar startDate = Calendar.getInstance();
        startDate.set(2020,Calendar.SEPTEMBER,1,0,0,0);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2020, Calendar.SEPTEMBER,30,0,0,0);

        assertEquals("9-2020", statement.setStatementPeriod(9,2020));
        assertNotNull(statement.statementStartDate);
        assertNotNull(statement.statementEndDate);
        //assertEquals(startDate,statement.statementStartDate);

       // assertEquals(startDate.getTime(),statement.statementStartDate.getTime());
        //assertEquals(endDate,statement.statementStartDate);
    }



}
