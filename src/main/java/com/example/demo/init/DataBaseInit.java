package com.example.demo.init;

import com.example.demo.model.car.Car;
import com.example.demo.model.car.Model;
import com.example.demo.model.enums.Language;
import com.example.demo.model.enums.Role;
import com.example.demo.model.place.City;
import com.example.demo.model.place.ParkingPlace;
import com.example.demo.model.place.Street;
import com.example.demo.model.place.i18n.CityI18n;
import com.example.demo.model.place.i18n.StreetI18n;
import com.example.demo.model.ticket.Ticket;
import com.example.demo.model.user.User;
import com.example.demo.repository.*;
import com.example.demo.repository.i18n.City18iRepository;
import com.example.demo.repository.i18n.Street18iRepository;
import com.example.demo.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private TicketRepository ticketRepository;
    private CityRepository cityRepository;
    private StreetRepository streetRepository;
    private City18iRepository city18iRepository;
    private Street18iRepository street18iRepository;

    @Autowired
    public DataBaseInit(UserRepository userRepository, CarRepository carRepository, ModelRepository modelRepository,
                        ParkingPlaceRepository parkingPlaceRepository, TicketRepository ticketRepository,
                        CityRepository cityRepository, StreetRepository streetRepository,
                        City18iRepository city18iRepository, Street18iRepository street18iRepository) {
        this.userRepository = userRepository;
        this.carRepository = carRepository;
        this.modelRepository = modelRepository;
        this.parkingPlaceRepository = parkingPlaceRepository;
        this.ticketRepository = ticketRepository;
        this.cityRepository = cityRepository;
        this.streetRepository = streetRepository;
        this.city18iRepository = city18iRepository;
        this.street18iRepository = street18iRepository;
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

// city
        City dnepr = new City(48.4500000, 34.9833300);
        City kiev = new City(50.45466, 30.5238);
        City lvov = new City(49.83826, 24.02324);

// city18i
        CityI18n dnepri18nRu = new CityI18n(Language.RU, dnepr, "Днепр");
        CityI18n dnepri18nEn = new CityI18n(Language.EN, dnepr, "Dnepr");
        CityI18n dnepri18nUa = new CityI18n(Language.UA, dnepr, "Дніпро");

        CityI18n kievi18nRu = new CityI18n(Language.RU, kiev, "Киев");
        CityI18n kievri18nEn = new CityI18n(Language.EN, kiev, "Kiev");
        CityI18n kievi18nUa = new CityI18n(Language.UA, kiev, "Київ");

        CityI18n lvovi18nRu = new CityI18n(Language.RU, lvov, "Львов");
        CityI18n lvovri18nEn = new CityI18n(Language.EN, lvov, "Lvov");
        CityI18n lvovi18nUa = new CityI18n(Language.UA, lvov, "Львів");

//        street dnipro
        Street yvorn = new Street(dnepr, "avenue");
        Street knzyar = new Street(dnepr, "street");
        Street polya = new Street(dnepr, "avenue");
        Street sichNab = new Street(dnepr, "street");
//        street kiev
        Street basseyn = new Street(kiev, "street");
        Street krech = new Street(kiev, "street");
        Street lUkrain = new Street(kiev, "street");
        // Khreschatyk St, 52
//        вулиця Хрещатик, 52
//        50.4415244,30.522864


//        streeti18n
        StreetI18n dmitraYvornRu = new StreetI18n(Language.RU, yvorn, "Дмитрия Яворницког, площадь");
        StreetI18n dmitraYvornUA = new StreetI18n(Language.UA, yvorn, "Дмитра Яворницького, площа");
        StreetI18n dmitraYvornEN = new StreetI18n(Language.EN, yvorn, "Dmytra Yavornytskoho, Avenue");

        StreetI18n knzMdrRu = new StreetI18n(Language.RU, knzyar, "Князя Ярослава Мудрого, улица");
        StreetI18n knzMdrUA = new StreetI18n(Language.UA, knzyar, "Князя Ярослава Мудрого, вулица");
        StreetI18n knzMdrEN = new StreetI18n(Language.EN, knzyar, "Kniazia Yaroslava Mudroho, street");

        StreetI18n polyaRu = new StreetI18n(Language.RU, polya, "Александра Поля, площадь");
        StreetI18n polyaUA = new StreetI18n(Language.UA, polya, "Олександра Поля, площа");
        StreetI18n polyaEN = new StreetI18n(Language.EN, polya, "Oleksandra Polia Ave, avenue");

        StreetI18n sichNabRu = new StreetI18n(Language.RU, sichNab, "Сичеславская набережная, улица");
        StreetI18n sichNabUA = new StreetI18n(Language.UA, sichNab, "Січеславська набережна, вулица");
        StreetI18n sichNabEN = new StreetI18n(Language.EN, sichNab, "Sitcheslavska naberezhnaya, street");

        StreetI18n chrechEn = new StreetI18n(Language.EN, krech, "Khreschatyk Street");
        StreetI18n chrechUa = new StreetI18n(Language.UA, krech, "Хрещатик, вулиця");
        StreetI18n chrechRu = new StreetI18n(Language.RU, krech, "Хрещатик, улиця");

        StreetI18n lukrEn = new StreetI18n(Language.EN, lUkrain, "Lesi Ukrainky, Street");
        StreetI18n lukrUa = new StreetI18n(Language.UA, lUkrain, "Лесі Українки, вулиця");
        StreetI18n lukrRu = new StreetI18n(Language.RU, lUkrain, "Леси Украинки, улица");

        StreetI18n bassEn = new StreetI18n(Language.EN, basseyn, "Baseina St");
        StreetI18n bassUa = new StreetI18n(Language.UA, basseyn, "Басейна вул.");
        StreetI18n bassRu = new StreetI18n(Language.RU, basseyn, "Басейная ул.");
//

        //          place
        ParkingPlace placeCenter1 = new ParkingPlace(yvorn, "19", 48.474591, 35.018446);
        ParkingPlace placeCenter2 = new ParkingPlace(knzyar, "37", 48.476205, 35.029969);
        ParkingPlace placeCenter3 = new ParkingPlace(knzyar, "62", 48.477391, 35.027263);
        ParkingPlace placeCenter4 = new ParkingPlace(polya, "23", 48.461505, 35.027324);
        ParkingPlace placeCenter5 = new ParkingPlace(sichNab, "19А", 48.472079, 35.043490);
        ParkingPlace placek1 = new ParkingPlace(basseyn, "11", 50.4382439,30.5285447);
        ParkingPlace placek2 = new ParkingPlace(lUkrain, "5", 50.434322, 30.5276957);
        ParkingPlace placek3 = new ParkingPlace(krech, "52", 50.4415244,30.522864);

//          tickets
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Ticket ticket1 = new Ticket(car1, placeCenter1, LocalDateTime.parse("2018-09-17 11:30", formatter), LocalDateTime.parse("2018-09-17 12:30", formatter), 12);
        Ticket ticket2 = new Ticket(car2, placeCenter2, LocalDateTime.parse("2018-09-17 10:08", formatter), LocalDateTime.parse("2018-09-17 12:30", formatter), 12);
        Ticket ticket3 = new Ticket(car3, placeCenter3, LocalDateTime.parse("2018-09-18 22:00", formatter), LocalDateTime.parse("2018-09-24 23:30", formatter), 12);
        Ticket ticket4 = new Ticket(car4, placeCenter3, LocalDateTime.parse("2018-09-18 22:00", formatter), LocalDateTime.parse("2018-09-24 23:30", formatter), 12);
        Ticket ticket5 = new Ticket(car2, placeCenter5, LocalDateTime.parse("2018-09-17 11:30", formatter), LocalDateTime.parse("2018-09-17 12:30", formatter), 12);

        userRepository.saveAll(Arrays.asList(user1, user2));
        carRepository.saveAll(Arrays.asList(car1, car2, car3, car4, car5));
        cityRepository.saveAll(Arrays.asList(dnepr, kiev, lvov));
        city18iRepository.saveAll(Arrays.asList(dnepri18nRu, dnepri18nEn, dnepri18nUa, kievi18nRu, kievi18nUa, kievri18nEn, lvovi18nRu, lvovi18nUa, lvovri18nEn));
        streetRepository.saveAll(Arrays.asList(yvorn, knzyar, polya, sichNab,basseyn,krech,lUkrain));
        street18iRepository.saveAll(Arrays.asList(dmitraYvornEN, dmitraYvornRu, dmitraYvornUA,
                polyaEN, polyaRu, polyaUA,
                knzMdrEN, knzMdrRu, knzMdrUA,
                sichNabRu, sichNabUA, sichNabEN,
                chrechEn,chrechRu,chrechUa,
                lukrEn,lukrRu,lukrUa,
                bassEn,bassRu,bassUa));
        parkingPlaceRepository.saveAll(Arrays.asList(placeCenter1, placeCenter2, placeCenter3, placeCenter4, placeCenter5,
                placek1,placek2,placek3));
        ticketRepository.saveAll(Arrays.asList(ticket1, ticket2, ticket3, ticket4, ticket5));
    }


}
