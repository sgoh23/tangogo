package com.example;

import com.opencsv.bean.CsvBindByName;

public class OCBCStatement extends Transaction{

    @CsvBindByName(column = "Supplementary Details")
    private String supplementary_details;

}
