import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestCompanyBook {
    CompanyBook cashInBank = new CompanyBook();
    double testAmount = 232.00;

    @Test
    public void ExpectToFindThisTransactionAmountInBooks() {
        assertTrue(cashInBank.hasTransactionWithAmt(testAmount));

    }
    @Test
    public void ExpectCompanyBookHasRecords(){
        cashInBank.setBeginningBalance();
        assertNotNull(cashInBank.PaymentRecords);
        assertTrue(cashInBank.PaymentRecords.size()>0);
    }



    }
