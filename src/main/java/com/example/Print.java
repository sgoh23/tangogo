package com.example;

import java.util.ArrayList;
import java.util.List;

public class Print {

    static String ANSI_BG_CYAN   = "\u001B[46m";
    static String ANSI_RESET  = "\u001B[0m";
    static String ANSI_CYAN   = "\u001B[36m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_WHITE = "\u001B[37m";

    static void theseRecords(ArrayList<Transaction> records, String listname){

        System.out.println(ANSI_CYAN + listname+ " ||||||||| PRINTING RECORDS ||||||||| :::"+
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

        System.out.println(ANSI_CYAN +listname+ " ||||||||| PRINTING RECORDS ||||||||| :::"+
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

        System.out.println(ANSI_CYAN +listname+ " ||||||||| PRINTING RECORDS ||||||||| :::");
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
            System.out.print(ANSI_BG_CYAN + listname + " ||||||||| SUMMARY OF RECORDS ||||||||| :::" +
                    " Count:"+records.size()+" of "+baseRecords.size() +
                    " | Reconciled: " + percentDone + "%");
            System.out.print(ANSI_RESET);
            System.out.println();
        }

    }

}
