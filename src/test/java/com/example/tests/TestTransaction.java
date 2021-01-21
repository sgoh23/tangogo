package com.example.tests;

import com.example.Transaction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestTransaction {

    Transaction testtxn = new Transaction();
    final static String CHEQUE = "CHEQUE";

    @Test
    public void expect_chequeno_to_contain_something_if_transaction_is_a_cheque_transaction(){

        if(!testtxn.notes_supplementaryDetails.isEmpty() && testtxn.notes_supplementaryDetails.equals(CHEQUE)){
            assertNotNull(testtxn.chequeNo);
        }


    }
}
