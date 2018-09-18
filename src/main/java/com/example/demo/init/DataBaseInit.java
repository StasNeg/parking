package com.example.demo.init;

import com.example.demo.model.car.Car;
import com.example.demo.model.car.Model;
import com.example.demo.model.car.Producer;
import com.example.demo.model.enums.Role;
import com.example.demo.model.place.ParkingPlace;
import com.example.demo.model.user.User;
import com.example.demo.repository.CarRepository;
import com.example.demo.repository.ModelRepository;
import com.example.demo.repository.ParkingPlaceRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

@Component
@Order(1)
@ConditionalOnProperty(name = "spring.jpa.hibernate.ddl-auto", havingValue = "create-drop")
public class DataBaseInit implements ApplicationRunner {


    private UserRepository userRepository;
    private CarRepository carRepository;
    private ModelRepository modelRepository;
    private ParkingPlaceRepository parkingPlaceRepository;

    @Autowired
    public DataBaseInit(UserRepository userRepository, CarRepository carRepository, ModelRepository modelRepository, ParkingPlaceRepository parkingPlaceRepository) {
        this.userRepository = userRepository;
        this.carRepository = carRepository;
        this.modelRepository = modelRepository;
        this.parkingPlaceRepository = parkingPlaceRepository;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {

//            Model
        Model modelMazdaCX7 = modelRepository.findByModelName("CX-7").get();
        Model modelMazdaCX9 = modelRepository.findByModelName("CX-9").get();
        Model modelToyotaRAV4 = modelRepository.findByModelName("RAV4").get();
        Model modelRenaultMegan = modelRepository.findByModelName("Megane").get();
        Model modelRenaultLogan = modelRepository.findByModelName("Logan").get();

//            users
        User user1 = new User("stasn@ua.fm", "Stan", "Nezhnyi", PasswordUtil.encode("12345"), true, new Date(), new HashSet(Arrays.asList(Role.ROLE_USER)));
        User user2 = new User("stasn1@ua.fm", "Stan", "Nezhnyi", PasswordUtil.encode("12345"), true, new Date(), new HashSet(Arrays.asList(Role.ROLE_ADMIN)));
//            cars
        Car car1 = new Car("AE0139BO", "first car", user1, modelMazdaCX7);
        Car car2 = new Car("AK1234BO", "second car", user1, modelMazdaCX9);
        Car car3 = new Car("AK1234BI", "thirth car", user1, modelRenaultLogan);
        Car car4 = new Car("AK1234BE", "first car", user2, modelRenaultMegan);
        Car car5 = new Car("AK1234AA", "second car", user2, modelToyotaRAV4);
//          place
        ParkingPlace placeCenter1 = new ParkingPlace("Dmytra Yavornytskoho Avenue", "Dnipro", "19",48.474591,35.018446,"Ukraine");
        ParkingPlace placeCenter2 = new ParkingPlace("Kniazia Yaroslava Mudroho Street", "Dnipro", "37",48.476205,35.029969,"Ukraine");
        ParkingPlace placeCenter3 = new ParkingPlace("Kniazia Yaroslava Mudroho Street", "Dnipro", "62",48.477391,35.027263,"Ukraine");
        ParkingPlace placeCenter4 = new ParkingPlace("Oleksandra Polia Ave", "Dnipro", "23",48.461505,35.027324,"Ukraine");
        ParkingPlace placeCenter5 = new ParkingPlace("Sicheslavska Naberezhna St", "Dnipro", "19А",48.472079,35.043490,"Ukraine");
//          tickets

        userRepository.saveAll(Arrays.asList(user1, user2));
        carRepository.saveAll(Arrays.asList(car1, car2, car3, car4, car5));
        parkingPlaceRepository.saveAll(Arrays.asList(placeCenter1,placeCenter2,placeCenter3,placeCenter4,placeCenter5));
    }


}
