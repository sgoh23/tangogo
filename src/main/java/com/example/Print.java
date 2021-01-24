package com.example;

import java.util.ArrayList;
import java.util.List;

public class Print {

    static String ANSI_BG_CYAN   = "\u001B[46m";
    static String ANSI_RESET  = "\u001B[0m";
    static String ANSI_CYAN   = "\u001B[36m";

    static void theseRecords(ArrayList<Transaction> records, String listname){

        System.out.println(ANSI_CYAN + " ||||||||| PRINTING RECORDS ||||||||| :::"+listname+
                " Record Count: ("+records.size()+")");
        System.out.println(" | CHANNEL | Record Ref ID | Transaction Amount | Transaction Date | "+
                "Reconciled? | Reconciled with Txn Ref ID | Transaction Description |" );

        for (Transaction record : records) {
            System.out.println( " | " +record.transactionChannel + " | " + record.toString() + " | " + record.transactionAmount + " | " +
                    record.transactionDate + " | "+record.reconciled + " | "+record.reconciledTxnRefID + " | " +
                    record.transactionDesc + " | " );
        }
        System.out.println(ANSI_RESET);

    }

    static void theseRecords(List<Transaction> records, String listname){

        System.out.println(ANSI_CYAN + " ||||||||| PRINTING RECORDS ||||||||| :::"+listname+
                " Record Count: ("+records.size()+")");
        System.out.println(" | CHANNEL | Record Ref ID | Transaction Amount | Transaction Date | "+
                "Reconciled? | Reconciled with Txn Ref ID | Transaction Description |" );

        for (Transaction record : records) {
            System.out.println( " | " +record.transactionChannel + " | " + record.toString() + " | " + record.transactionAmount + " | " +
                    record.transactionDate + " | "+record.reconciled + " | "+record.reconciledTxnRefID + " | " +
                    record.transactionDesc + " | ");
        }
        System.out.println(ANSI_RESET);

    }

    static void thisRecord(Transaction record, String listname){

        System.out.println(ANSI_CYAN + " ||||||||| PRINTING RECORDS ||||||||| :::"+listname);
        System.out.println(" | Record Ref ID | Transaction Amount | Transaction Date | "+
                "Reconciled? | Reconciled with Txn Ref ID | Transaction Description | CHEQUE NO | CHANNEL |" );

        System.out.println(" | " + record.toString() + " | " + record.transactionAmount + " | " +
                record.transactionDate + " | "+record.reconciled + " | "+record.reconciledTxnRefID + " | " +
                record.transactionDesc + " | " +record.chequeNo + " | " + " | " +record.transactionChannel + " | ");
        System.out.println(ANSI_RESET);

    }

    public static void summarizeRecords(ArrayList<Transaction> records, ArrayList<Transaction> baseRecords,  String listname) {

        int percentDone = 0;

        if(records != null && baseRecords != null){
            double basecount = baseRecords.size();
            percentDone = (int) ((basecount-records.size())/basecount*100);
            System.out.println();
            System.out.print(ANSI_BG_CYAN + " ||||||||| SUMMARY OF RECORDS ||||||||| :::" +listname+
                    " Count:"+records.size()+" of "+baseRecords.size() +
                    " | Reconciled: " + percentDone + "%");
            System.out.print(ANSI_RESET);
            System.out.println();
        }

    }

}
