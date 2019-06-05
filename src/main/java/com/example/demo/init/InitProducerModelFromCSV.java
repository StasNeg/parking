package com.example.demo.init;

import com.example.demo.model.car.Model;
import com.example.demo.model.car.Producer;
import com.example.demo.model.enums.CarType;
import com.example.demo.repository.ModelRepository;
import com.example.demo.repository.ProducerRepository;
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Order(0)
@ConditionalOnProperty(name = "spring.jpa.hibernate.ddl-auto", havingValue = "create")
public class InitProducerModelFromCSV implements ApplicationRunner {

    private ModelRepository modelRepository;
    private ProducerRepository producerRepository;

    @Autowired
    public InitProducerModelFromCSV(ModelRepository modelRepository, ProducerRepository producerRepository) {
        this.modelRepository = modelRepository;
        this.producerRepository = producerRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        init();
    }

    public void init() throws IOException {
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
