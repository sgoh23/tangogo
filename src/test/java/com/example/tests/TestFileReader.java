package com.example.tests;

import com.example.OCBCStatement;
import com.example.Transaction;
import com.opencsv.bean.CsvToBeanBuilder;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class TestFileReader {

    @Test
    public void expect_ability_to_check_whether_csv_file_exist_in_local_drive() {

        final String FILE_NAME = "e-statement.csv";
        final String MAC_FILE_LOCATION = "/Users/serenagoh/tangogo/landingzone";
        final String WIN_FILE_LOCATION = "C:/tangogo/landingzone";

        File file = new File(MAC_FILE_LOCATION+FILE_NAME);

        try {
            List<Transaction> beans = new CsvToBeanBuilder<Transaction>(new FileReader(MAC_FILE_LOCATION + FILE_NAME))
                    .withType(OCBCStatement.class)
                    .build()
                    .parse();

            beans.forEach(System.out::println);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

    }
}
