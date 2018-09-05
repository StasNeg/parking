package com.example.demo.init;

import com.example.demo.model.car.Car;
import com.example.demo.model.car.CarDescription;
import com.example.demo.model.enums.CarProducer;
import com.example.demo.model.enums.CarType;
import com.example.demo.model.enums.Role;
import com.example.demo.model.user.User;
import com.example.demo.repository.CarRepository;
import com.example.demo.repository.CarDescriptionRepository;
import com.example.demo.repository.UserRepository;
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
    private String isInit;

    private UserRepository userRepository;
    private CarRepository carRepository;
    private CarDescriptionRepository carDescriptionRepository;

    @Autowired
    public DataBaseInit(UserRepository userRepository, CarRepository carRepository, CarDescriptionRepository carDescriptionRepository) {
        this.userRepository = userRepository;
        this.carRepository = carRepository;
        this.carDescriptionRepository = carDescriptionRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (!isInit.equals("none")) {
//            desc
            CarDescription carDescriptionMazda = new CarDescription(CarProducer.Mazda, "CX7", CarType.CROSSOVER);
            CarDescription carDescriptionToyota = new CarDescription(CarProducer.Toyota, "RAV4", CarType.CROSSOVER);
            CarDescription carDescriptionRenault = new CarDescription(CarProducer.Renault, "Megan", CarType.SEDAN);
//            users
            User user1 = new User("stasn@ua.fm", "Stan", "Nezhnyi", PasswordUtil.encode("12345"), true, new Date(), new HashSet(Arrays.asList(Role.ROLE_USER)));
            User user2 = new User("stasn1@ua.fm", "Stan", "Nezhnyi", PasswordUtil.encode("12345"), true, new Date(), new HashSet(Arrays.asList(Role.ROLE_ADMIN)));
//            cars
            Car car1 = new Car("AE0139BO", "first car", user1, carDescriptionMazda);
            Car car2 = new Car("AK1234BO", "second car", user1, carDescriptionToyota);
            Car car3 = new Car("AK1234BI", "thirth car", user1, carDescriptionRenault);
            Car car4 = new Car("AK1234BE", "first car", user2, carDescriptionRenault);
            Car car5 = new Car("AK1234AA", "second car", user2, carDescriptionMazda);

            userRepository.saveAll(Arrays.asList(user1, user2));
            carDescriptionRepository.saveAll(Arrays.asList(carDescriptionMazda, carDescriptionRenault, carDescriptionToyota));
            carRepository.saveAll(Arrays.asList(car1, car2, car3, car4, car5));
        }
    }


}
