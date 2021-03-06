package com.example.tests;


import com.example.BankStatement;
import com.example.CompanyBankAccount;
import com.example.Transaction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestCompanyBankAccount {
    CompanyBankAccount coyCashInBank = new CompanyBankAccount();
    BankStatement bankStatement = new BankStatement();
    Transaction bankTxn = new Transaction();
    Transaction coytxn = new Transaction();
    double testValidAmount = 232.00;
    double testAbsentAmount1 = 300.00;

    @Test
    public void Expect_Company_CashInBank_isNotNull(){
        assertNotNull(coyCashInBank);
    }

    @Test
    public void ExpectCompanyBookHasRecords(){
        coyCashInBank.initiateCoyCashinBankRecords();
        assertTrue(coyCashInBank.getRecordSize()>0);
    }

    @Test
    void ExpectCompanyBook_is_not_null(){
        assertNotNull(coyCashInBank.getCoyCashinBankRecords());
    }



    }
