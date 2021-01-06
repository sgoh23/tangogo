package com.example;

import java.util.ArrayList;

public class Print {

    static void theseRecords(ArrayList<Transaction> records, String listname){

        final String ANSI_BG_CYAN   = "\u001B[46m";
        final String ANSI_RESET  = "\u001B[0m";
        final String ANSI_CYAN   = "\u001B[36m";

        System.out.println(ANSI_CYAN + " ||||||||| PRINTING RECORDS ||||||||| :::"+listname+
                " Record Count: ("+records.size()+")");
        System.out.println(" | Record Ref ID | Transaction Amount | Transaction Date | "+
                "Reconciled? | Reconciled with Txn Ref ID | " );

        for (Transaction record : records) {
            System.out.println(" | " + record.toString() + " | " + record.transactionAmount + " | " +
                    record.transactionDate + " | "+record.reconciled + " | "+record.reconciledTxnRefID + " | " );
        }
        System.out.println(ANSI_RESET);
    }
}
