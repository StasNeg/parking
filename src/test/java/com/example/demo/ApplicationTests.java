package com.example.demo;

import com.example.demo.model.ticket.Ticket;
import com.example.demo.repository.CarRepository;
import com.example.demo.repository.CityRepository;
import com.example.demo.repository.TicketRepository;
import com.example.demo.service.TicketService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ParkingApplication.class)
//@SpringBootTest
@Transactional
//@PropertySource("classpath:test-application.properties")
public class ApplicationTests {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private WebApplicationContext context;
    //    services
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private TicketService ticketService;

    private Iterable<Ticket> tickets;

    @PostConstruct
    public void generateTestData() throws JsonProcessingException {
//        Iterable<Car> cars = carRepository.findAll();
//        Iterable<City> cities = cityRepository.findAll();
//        tickets = ticketService.getAllTicket();

    }


    private MockMvc mvc;

    @Before

    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
        tickets = ticketService.getAllTicket();
    }

    @Test

    public void indexNotEmptyIndex() {
        String forObject = this.restTemplate.getForObject("/", String.class);
        assertThat(forObject).isNotEmpty().contains("a href");
    }

    @Test
//    @WithMockUser(roles = {"USER"})
//    @Transactional
    public void carController() throws Exception {

        tickets.forEach(ticket -> {
//                    if (!Hibernate.isInitialized(ticket.getParkingPlace())) {
                        ticket.setParkingPlace(initializeAndUnproxy( ticket.getParkingPlace()));
                        ticket.getParkingPlace().setStreet(initializeAndUnproxy( ticket.getParkingPlace().getStreet()));
                        ticket.getParkingPlace().getStreet().setCity(initializeAndUnproxy( ticket.getParkingPlace().getStreet().getCity()));
                        ticket.setCar(initializeAndUnproxy( ticket.getCar()));
                        ticket.getCar().setModel(initializeAndUnproxy( ticket.getCar().getModel()));
                        ticket.getCar().getModel().setProducer(initializeAndUnproxy( ticket.getCar().getModel().getProducer()));
                        ticket.getCar().getModel().setType(initializeAndUnproxy( ticket.getCar().getModel().getType()));
//                        Hibernate.initialize(ticket.getCar().getModel().getProducer());
//                        Hibernate.initialize(ticket.getCar().getModel().getType());
//                    }
                }
        )        ;
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
//        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
//        Hibernate.isInitialized()
        System.out.println(mapper.writeValueAsString(tickets));

    }

    public <T> T initializeAndUnproxy(T entity) {
        if (entity == null) {
            throw new
                    NullPointerException("Entity passed for initialization is null");
        }

        Hibernate.initialize(entity);
        if (entity instanceof HibernateProxy) {
            entity = (T) ((HibernateProxy) entity).getHibernateLazyInitializer()
                    .getImplementation();
        }
        return entity;
    }

}
