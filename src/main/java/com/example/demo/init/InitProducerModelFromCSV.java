package com.example.demo.init;

import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.Arrays;

@Component
public class InitProducerModelFromCSV implements ApplicationRunner {
    @Value("${init.data.csv}")
    private String isInit;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        File file = new ClassPathResource("cars_csv.csv").getFile();
        try (
                Reader reader = new BufferedReader(new FileReader(file));
                CSVReader csvReader = new CSVReader(reader,';');
        ) {
            // Reading Records One by One in a String array
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                System.out.println(Arrays.toString(nextRecord));
//                System.out.println("Email : " + nextRecord[1]);
//                System.out.println("Phone : " + nextRecord[2]);
//                System.out.println("Country : " + nextRecord[3]);
                System.out.println("==========================");
            }
        }
    }
}
