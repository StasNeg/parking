package com.example.demo.init;

import com.example.demo.model.car.Model;
import com.example.demo.model.car.Producer;
import com.example.demo.model.enums.CarType;
import com.example.demo.repository.ModelRepository;
import com.example.demo.repository.ProducerRepository;
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.*;

@Component
@Order(0)
public class InitProducerModelFromCSV implements ApplicationRunner {
    @Value("${init.data.csv}")
    private String isInit;

    private ModelRepository modelRepository;
    private ProducerRepository producerRepository;

    @Autowired
    public InitProducerModelFromCSV(ModelRepository modelRepository, ProducerRepository producerRepository) {
        this.modelRepository = modelRepository;
        this.producerRepository = producerRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (!isInit.equals("yes")) return;
        File file = new ClassPathResource("cars_csv.csv").getFile();
        try (
                Reader reader = new BufferedReader(new FileReader(file));
                CSVReader csvReader = new CSVReader(reader, ';');
        ) {
            // Reading Records One by One in a String array
            String[] nextRecord;
            Map<String, List<String>> carModels = new HashMap<>();
            while ((nextRecord = csvReader.readNext()) != null) {
                List<String> temp = carModels.getOrDefault(nextRecord[0], new ArrayList<>());
                temp.add(nextRecord[1]);
                if (temp.size() == 1) carModels.put(nextRecord[0], temp);
            }
            for (String producerName : carModels.keySet()) {
                Producer producer = new Producer(producerName);
                List<Model> models = new ArrayList<>();
                for (String model : carModels.get(producerName)) {
                    models.add(new Model(CarType.SEDAN, producer, model));
                }
                producerRepository.save(producer);
                modelRepository.saveAll(models);
            }
        }
    }
}
