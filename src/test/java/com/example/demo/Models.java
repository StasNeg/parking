package com.example.demo;

import com.example.demo.init.InitProducerModelFromCSV;
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
import com.example.demo.repository.ModelRepository;
import com.example.demo.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

@Component
public class Models {

//    private ModelRepository modelRepository;
//    private InitProducerModelFromCSV initProducerModelFromCSV;
//
//    @Autowired
//    public Models(ModelRepository modelRepository, InitProducerModelFromCSV initProducerModelFromCSV) {
//        this.modelRepository = modelRepository;
//        this.initProducerModelFromCSV = initProducerModelFromCSV;
//    }
//
//    @PostConstruct
//    public void init() throws IOException {
//        initProducerModelFromCSV.init();
//        user1 = new User("stasn@ua.fm", "Stan", "Nezhnyi", PasswordUtil.encode("12345"), true, new Date(), new HashSet(Arrays.asList(Role.ROLE_USER)));
//        user2 = new User("stasn1@ua.fm", "Stan", "Nezhnyi", PasswordUtil.encode("12345"), true, new Date(), new HashSet(Arrays.asList(Role.ROLE_ADMIN)));
//        //            cars
//        car1 = new Car("AE0139BO", "first car", user1, getModelMazdaCX7());
//        car2 = new Car("AK1234BO", "second car", user1, getModelMazdaCX9());
//        car3 = new Car("AK1234BI", "thirth car", user1, getModelToyotaRAV4());
//        car4 = new Car("AK1234BE", "first car", user2, getModelRenaultMegan());
//        car5 = new Car("AK1234AA", "second car", user2, getModelRenaultLogan());
//
//        // city
//        dnepr = new City(48.4500000, 34.9833300);
//        kiev = new City(50.45466, 30.5238);
//        lvov = new City(49.83826, 24.02324);
//
//        // city18i
//        dnepri18nRu = new CityI18n(Language.RU, dnepr, "Днепр");
//        dnepri18nEn = new CityI18n(Language.EN, dnepr, "Dnepr");
//        dnepri18nUa = new CityI18n(Language.UK, dnepr, "Дніпро");
//
//        kievi18nRu = new CityI18n(Language.RU, kiev, "Киев");
//        kievri18nEn = new CityI18n(Language.EN, kiev, "Kiev");
//        kievi18nUa = new CityI18n(Language.UK, kiev, "Київ");
//
//        lvovi18nRu = new CityI18n(Language.RU, lvov, "Львов");
//        lvovri18nEn = new CityI18n(Language.EN, lvov, "Lvov");
//        lvovi18nUa = new CityI18n(Language.UK, lvov, "Львів");
//
//        //        street dnipro
//        yvorn = new Street(dnepr, "avenue");
//        knzyar = new Street(dnepr, "street");
//        polya = new Street(dnepr, "avenue");
//        sichNab = new Street(dnepr, "street");
//        //        street kiev
//        basseyn = new Street(kiev, "street");
//        krech = new Street(kiev, "street");
//        lUkrain = new Street(kiev, "street");
//        // Khreschatyk St, 52
////        вулиця Хрещатик, 52
////        50.4415244,30.522864
//
//
//        //        streeti18n
//        dmitraYvornRu = new StreetI18n(Language.RU, yvorn, "Дмитрия Яворницког, площадь");
//        dmitraYvornUA = new StreetI18n(Language.UK, yvorn, "Дмитра Яворницького, площа");
//        dmitraYvornEN = new StreetI18n(Language.EN, yvorn, "Dmytra Yavornytskoho, Avenue");
//
//        knzMdrRu = new StreetI18n(Language.RU, knzyar, "Князя Ярослава Мудрого, улица");
//        knzMdrUA = new StreetI18n(Language.UK, knzyar, "Князя Ярослава Мудрого, вулица");
//        knzMdrEN = new StreetI18n(Language.EN, knzyar, "Kniazia Yaroslava Mudroho, street");
//
//        polyaRu = new StreetI18n(Language.RU, polya, "Александра Поля, площадь");
//        polyaUA = new StreetI18n(Language.UK, polya, "Олександра Поля, площа");
//        polyaEN = new StreetI18n(Language.EN, polya, "Oleksandra Polia Ave, avenue");
//
//        sichNabRu = new StreetI18n(Language.RU, sichNab, "Сичеславская набережная, улица");
//        sichNabUA = new StreetI18n(Language.UK, sichNab, "Січеславська набережна, вулица");
//        sichNabEN = new StreetI18n(Language.EN, sichNab, "Sitcheslavska naberezhnaya, street");
//
//        chrechEn = new StreetI18n(Language.EN, krech, "Khreschatyk Street");
//        chrechUa = new StreetI18n(Language.UK, krech, "Хрещатик, вулиця");
//        chrechRu = new StreetI18n(Language.RU, krech, "Хрещатик, улиця");
//
//        lukrEn = new StreetI18n(Language.EN, lUkrain, "Lesi Ukrainky, Street");
//        lukrUa = new StreetI18n(Language.UK, lUkrain, "Лесі Українки, вулиця");
//        lukrRu = new StreetI18n(Language.RU, lUkrain, "Леси Украинки, улица");
//
//        bassEn = new StreetI18n(Language.EN, basseyn, "Baseina St");
//        bassUa = new StreetI18n(Language.UK, basseyn, "Басейна вул.");
//        bassRu = new StreetI18n(Language.RU, basseyn, "Басейная ул.");
////
//
//        //          place
//        placeCenter1 = new ParkingPlace(yvorn, "19", 48.474591, 35.018446);
//        placeCenter2 = new ParkingPlace(knzyar, "37", 48.476205, 35.029969);
//        placeCenter3 = new ParkingPlace(knzyar, "62", 48.477391, 35.027263);
//        placeCenter4 = new ParkingPlace(polya, "23", 48.461505, 35.027324);
//        placeCenter5 = new ParkingPlace(sichNab, "19А", 48.472079, 35.043490);
//        placek1 = new ParkingPlace(basseyn, "11", 50.4382439, 30.5285447);
//        placek2 = new ParkingPlace(lUkrain, "5", 50.434322, 30.5276957);
//        placek3 = new ParkingPlace(krech, "52", 50.4415244, 30.522864);
//
//        //          tickets
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//        ticket1 = new Ticket(car1, placeCenter1, LocalDateTime.parse("2018-09-17 11:30", formatter), LocalDateTime.parse("2018-09-17 12:30", formatter), 12);
//        ticket2 = new Ticket(car2, placeCenter2, LocalDateTime.parse("2018-09-17 10:08", formatter), LocalDateTime.parse("2018-09-17 12:30", formatter), 12);
//        ticket3 = new Ticket(car3, placeCenter3, LocalDateTime.parse("2018-09-18 22:00", formatter), LocalDateTime.parse("2018-09-24 23:30", formatter), 12);
//        ticket4 = new Ticket(car4, placeCenter3, LocalDateTime.parse("2018-09-18 22:00", formatter), LocalDateTime.parse("2018-09-24 23:30", formatter), 12);
//        ticket5 = new Ticket(car2, placeCenter5, LocalDateTime.parse("2018-09-17 11:30", formatter), LocalDateTime.parse("2018-09-17 12:30", formatter), 12);
//
//    }
//
//    //            Model
//    Model modelMazdaCX7;
//    Model modelMazdaCX9;
//    Model modelToyotaRAV4;
//    Model modelRenaultMegan;
//    Model modelRenaultLogan;
//
//    //            users
//
//    User user1;
//    User user2;
//    //            cars
//    Car car1;
//    Car car2;
//    Car car3;
//    Car car4;
//    Car car5;
//
//    // city
//    City dnepr;
//    City kiev;
//    City lvov;
//
//    // city18i
//    CityI18n dnepri18nRu;
//    CityI18n dnepri18nEn;
//    CityI18n dnepri18nUa;
//
//    CityI18n kievi18nRu;
//    CityI18n kievri18nEn;
//    CityI18n kievi18nUa;
//
//    CityI18n lvovi18nRu;
//    CityI18n lvovri18nEn;
//    CityI18n lvovi18nUa;
//
//    //        street dnipro
//    Street yvorn;
//    Street knzyar;
//    Street polya;
//    Street sichNab;
//    //        street kiev
//    Street basseyn;
//    Street krech;
//    Street lUkrain;
//    // Khreschatyk St, 52
////        вулиця Хрещатик, 52
////        50.4415244,30.522864
//
//
//    //        streeti18n
//    StreetI18n dmitraYvornRu;
//    StreetI18n dmitraYvornUA;
//    StreetI18n dmitraYvornEN = new StreetI18n(Language.EN, yvorn, "Dmytra Yavornytskoho, Avenue");
//
//    StreetI18n knzMdrRu;
//    StreetI18n knzMdrUA;
//    StreetI18n knzMdrEN;
//
//    StreetI18n polyaRu;
//    StreetI18n polyaUA;
//    StreetI18n polyaEN;
//
//    StreetI18n sichNabRu;
//    StreetI18n sichNabUA;
//    StreetI18n sichNabEN;
//
//    StreetI18n chrechEn;
//    StreetI18n chrechUa;
//    StreetI18n chrechRu;
//
//    StreetI18n lukrEn;
//    StreetI18n lukrUa;
//    StreetI18n lukrRu;
//
//    StreetI18n bassEn;
//    StreetI18n bassUa;
//    StreetI18n bassRu;
////
//
//    //          place
//    ParkingPlace placeCenter1;
//    ParkingPlace placeCenter2;
//    ParkingPlace placeCenter3;
//    ParkingPlace placeCenter4;
//    ParkingPlace placeCenter5;
//    ParkingPlace placek1;
//    ParkingPlace placek2;
//    ParkingPlace placek3;
//
//    //          tickets
//
//    Ticket ticket1;
//    Ticket ticket2;
//    Ticket ticket3;
//    Ticket ticket4;
//    Ticket ticket5;
//
//    public Model getModelMazdaCX7() {
//        return modelRepository.findByModelName("CX-7").get();
//    }
//
//    public Model getModelMazdaCX9() {
//        return modelRepository.findByModelName("CX-9").get();
//    }
//
//    public Model getModelToyotaRAV4() {
//        return modelRepository.findByModelName("RAV4").get();
//    }
//
//    public Model getModelRenaultMegan() {
//        return modelRepository.findByModelName("Megane").get();
//    }
//
//    public Model getModelRenaultLogan() {
//        return modelRepository.findByModelName("Logan").get();
//    }
//
//    public User getUser1() {
//        return user1;
//    }
//
//    public User getUser2() {
//        return user2;
//    }
//
//    public Car getCar1() {
//        return car1;
//    }
//
//    public Car getCar2() {
//        return car2;
//    }
//
//    public Car getCar3() {
//        return car3;
//    }
//
//    public Car getCar4() {
//        return car4;
//    }
//
//    public Car getCar5() {
//        return car5;
//    }
//
//    public City getDnepr() {
//        return dnepr;
//    }
//
//    public City getKiev() {
//        return kiev;
//    }
//
//    public City getLvov() {
//        return lvov;
//    }
//
//    public CityI18n getDnepri18nRu() {
//        return dnepri18nRu;
//    }
//
//    public CityI18n getDnepri18nEn() {
//        return dnepri18nEn;
//    }
//
//    public CityI18n getDnepri18nUa() {
//        return dnepri18nUa;
//    }
//
//    public CityI18n getKievi18nRu() {
//        return kievi18nRu;
//    }
//
//    public CityI18n getKievri18nEn() {
//        return kievri18nEn;
//    }
//
//    public CityI18n getKievi18nUa() {
//        return kievi18nUa;
//    }
//
//    public CityI18n getLvovi18nRu() {
//        return lvovi18nRu;
//    }
//
//    public CityI18n getLvovri18nEn() {
//        return lvovri18nEn;
//    }
//
//    public CityI18n getLvovi18nUa() {
//        return lvovi18nUa;
//    }
//
//    public Street getYvorn() {
//        return yvorn;
//    }
//
//    public Street getKnzyar() {
//        return knzyar;
//    }
//
//    public Street getPolya() {
//        return polya;
//    }
//
//    public Street getSichNab() {
//        return sichNab;
//    }
//
//    public Street getBasseyn() {
//        return basseyn;
//    }
//
//    public Street getKrech() {
//        return krech;
//    }
//
//    public Street getlUkrain() {
//        return lUkrain;
//    }
//
//    public StreetI18n getDmitraYvornRu() {
//        return dmitraYvornRu;
//    }
//
//    public StreetI18n getDmitraYvornUA() {
//        return dmitraYvornUA;
//    }
//
//    public StreetI18n getDmitraYvornEN() {
//        return dmitraYvornEN;
//    }
//
//    public StreetI18n getKnzMdrRu() {
//        return knzMdrRu;
//    }
//
//    public StreetI18n getKnzMdrUA() {
//        return knzMdrUA;
//    }
//
//    public StreetI18n getKnzMdrEN() {
//        return knzMdrEN;
//    }
//
//    public StreetI18n getPolyaRu() {
//        return polyaRu;
//    }
//
//    public StreetI18n getPolyaUA() {
//        return polyaUA;
//    }
//
//    public StreetI18n getPolyaEN() {
//        return polyaEN;
//    }
//
//    public StreetI18n getSichNabRu() {
//        return sichNabRu;
//    }
//
//    public StreetI18n getSichNabUA() {
//        return sichNabUA;
//    }
//
//    public StreetI18n getSichNabEN() {
//        return sichNabEN;
//    }
//
//    public StreetI18n getChrechEn() {
//        return chrechEn;
//    }
//
//    public StreetI18n getChrechUa() {
//        return chrechUa;
//    }
//
//    public StreetI18n getChrechRu() {
//        return chrechRu;
//    }
//
//    public StreetI18n getLukrEn() {
//        return lukrEn;
//    }
//
//    public StreetI18n getLukrUa() {
//        return lukrUa;
//    }
//
//    public StreetI18n getLukrRu() {
//        return lukrRu;
//    }
//
//    public StreetI18n getBassEn() {
//        return bassEn;
//    }
//
//    public StreetI18n getBassUa() {
//        return bassUa;
//    }
//
//    public StreetI18n getBassRu() {
//        return bassRu;
//    }
//
//    public ParkingPlace getPlaceCenter1() {
//        return placeCenter1;
//    }
//
//    public ParkingPlace getPlaceCenter2() {
//        return placeCenter2;
//    }
//
//    public ParkingPlace getPlaceCenter3() {
//        return placeCenter3;
//    }
//
//    public ParkingPlace getPlaceCenter4() {
//        return placeCenter4;
//    }
//
//    public ParkingPlace getPlaceCenter5() {
//        return placeCenter5;
//    }
//
//    public ParkingPlace getPlacek1() {
//        return placek1;
//    }
//
//    public ParkingPlace getPlacek2() {
//        return placek2;
//    }
//
//    public ParkingPlace getPlacek3() {
//        return placek3;
//    }
//
//
//    public Ticket getTicket1() {
//        return ticket1;
//    }
//
//    public Ticket getTicket2() {
//        return ticket2;
//    }
//
//    public Ticket getTicket3() {
//        return ticket3;
//    }
//
//    public Ticket getTicket4() {
//        return ticket4;
//    }
//
//    public Ticket getTicket5() {
//        return ticket5;
//    }
}
