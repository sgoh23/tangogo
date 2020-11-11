package com.example.tests;


import com.example.CompanyBook;
import com.example.Transaction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCompanyBook {
    CompanyBook coyCashInBank = new CompanyBook();
    Transaction coytxn = null;
    double testAmount = 232.00;
    double testAmount1 = 300.00;

    @Test
    public void ExpectToFindThisTransactionAmountInBooks() {
        coytxn = coyCashInBank.getUnreconciledRecordWithAmt(testAmount);
        assertNotNull(coytxn);
    }
    @Test
    public void ExpectToFindThisTransactionAsUnreconciledIfNotFoundinCompanyBook(){
        coytxn = coyCashInBank.getUnreconciledRecordWithAmt(testAmount1);
        assertNotNull(coytxn);
    }

    @Test
    public void ExpectCompanyBookHasRecords(){
        coyCashInBank.setBeginningBalance();
        assertTrue(coyCashInBank.getPaymentRecordSize()>0);
    }

    @Test
    void ExpectCompanyBook_is_not_null(){
        assertNotNull(coyCashInBank.getPaymentRecords());
    }



    }
