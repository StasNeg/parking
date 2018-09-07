package com.example.demo.init;

import com.example.demo.model.car.Car;
import com.example.demo.model.car.CarDescription;
import com.example.demo.model.car.Model;
import com.example.demo.model.car.Producer;
import com.example.demo.model.enums.CarType;
import com.example.demo.model.enums.Role;
import com.example.demo.model.user.User;
import com.example.demo.repository.*;
import com.example.demo.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

@Component
public class DataBaseInit implements ApplicationRunner {

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private java.lang.String isInit;

    private UserRepository userRepository;
    private CarRepository carRepository;
    private CarDescriptionRepository carDescriptionRepository;
    private ModelRepository modelRepository;
    private ProducerRepository producerRepository;

    @Autowired
    public DataBaseInit(UserRepository userRepository, CarRepository carRepository, CarDescriptionRepository carDescriptionRepository, ModelRepository modelRepository, ProducerRepository producerRepository) {
        this.userRepository = userRepository;
        this.carRepository = carRepository;
        this.carDescriptionRepository = carDescriptionRepository;
        this.modelRepository = modelRepository;
        this.producerRepository = producerRepository;
    }




    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (!isInit.equals("none")) {
//            desc
//            CarDescription carDescriptionMazda = new CarDescription("Mazda", "CX7", CarType.CROSSOVER);
//            CarDescription carDescriptionToyota = new CarDescription("Toyota", "RAV4", CarType.CROSSOVER);
//            CarDescription carDescriptionRenault = new CarDescription("Renault", "Megan", CarType.SEDAN);
//            producer

            Producer mazda = new Producer("Mazda");
            Producer toyota = new Producer("Toyota");
            Producer renault = new Producer("Renault");

//            Model
            Model modelMazdaCX7 = new Model(CarType.CROSSOVER, mazda, "CX7");
            Model modelMazdaCX9 = new Model(CarType.CROSSOVER, mazda, "CX9");
            Model modelToyotaRAV4 = new Model(CarType.CROSSOVER, toyota, "RAV4");
            Model modelRenaultMegan = new Model(CarType.SEDAN, renault, "Megan");
            Model modelRenaultLogan = new Model(CarType.SEDAN, renault, "Logan");

//            users
            User user1 = new User("stasn@ua.fm", "Stan", "Nezhnyi", PasswordUtil.encode("12345"), true, new Date(), new HashSet(Arrays.asList(Role.ROLE_USER)));
            User user2 = new User("stasn1@ua.fm", "Stan", "Nezhnyi", PasswordUtil.encode("12345"), true, new Date(), new HashSet(Arrays.asList(Role.ROLE_ADMIN)));
//            cars
            Car car1 = new Car("AE0139BO", "first car", user1, modelMazdaCX7 );
            Car car2 = new Car("AK1234BO", "second car", user1, modelMazdaCX9);
            Car car3 = new Car("AK1234BI", "thirth car", user1, modelRenaultLogan);
            Car car4 = new Car("AK1234BE", "first car", user2, modelRenaultMegan);
            Car car5 = new Car("AK1234AA", "second car", user2, modelToyotaRAV4);

            userRepository.saveAll(Arrays.asList(user1, user2));
            producerRepository.saveAll(Arrays.asList(mazda,renault,toyota));
            modelRepository.saveAll(Arrays.asList(modelMazdaCX7,modelMazdaCX9, modelToyotaRAV4, modelRenaultLogan, modelRenaultMegan));
            carRepository.saveAll(Arrays.asList(car1, car2, car3, car4, car5));
        }
    }


}
