package com.example.tests;


import com.example.CompanyBook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestCompanyBook {
    CompanyBook cashInBank = new CompanyBook();
    double testAmount = 232.00;

    @Test
    public void ExpectToFindThisTransactionAmountInBooks() {
        Assertions.assertTrue(cashInBank.hasTransactionWithAmt(testAmount));

    }
    @Test
    public void ExpectCompanyBookHasRecords(){
        cashInBank.setBeginningBalance();
        assertTrue(cashInBank.getPaymentRecordSize()>0);
    }

    @Test
    void ExpectCompanyBook_is_not_null(){
        assertNotNull(cashInBank.getPaymentRecords());
    }



    }
