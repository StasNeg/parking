package com.example.demo.unit;

import com.example.demo.controller.ParkingController;
import com.example.demo.dto.CarDto;
import com.example.demo.dto.CityDto;
import com.example.demo.model.enums.CarType;
import com.example.demo.service.CarService;
import com.example.demo.service.CityService;
import com.example.demo.service.ParkingPlaceService;
import com.example.demo.service.TicketService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.StaticLoggerBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultHandler;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ParkingController.class)
public class UnitTestsExample {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private TicketService ticketService;
    @MockBean
    private ParkingPlaceService parkingPlaceService;
    @MockBean
    private CarService carService;
    @MockBean
    private CityService cityService;
    Logger log = LoggerFactory.getLogger(getClass());

    private CityDto dneprDto = new CityDto(10001L, 134.33,135.12, "Dnepr");
    private CityDto kievDto = new CityDto(10002L, 133.33,131.12, "Kiev");
    private CityDto kharkovDto = new CityDto(10003L, 123.12,141.56, "Kharkov");

    //    long id, String number, String model, CarType carType, String producer, String description
    private CarDto car1 = new CarDto(10012L, "BO1248AE", "6", CarType.SEDAN, "Mazda", "New test one");
    CarDto car2 = new CarDto(10013L, "BO1594AE", "Megane", CarType.SEDAN, "Renault", "New test two");

    @Before
    public void setup() {


    }
    private ResultHandler resultHandler = result -> log.warn(result.getResponse().getContentAsString());
    @Test
    @WithMockUser("USER")
    public void mockTest() throws Exception {

        when(cityService.getCities()).thenReturn(Arrays.asList(dneprDto,kievDto, kharkovDto));
        when(carService.getCars()).thenReturn(Arrays.asList(car1, car2));
        mvc.perform(get("/user/ticket/cities"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json("{\"cars\":[{\"id\":10012,\"number\":\"BO1248AE\",\"model\":\"6\",\"carType\":\"SEDAN\",\"producer\":\"Mazda\",\"description\":\"New test one\"},{\"id\":10013,\"number\":\"BO1594AE\",\"model\":\"Megane\",\"carType\":\"SEDAN\",\"producer\":\"Renault\",\"description\":\"New test two\"}],\"city\":[{\"id\":10001,\"lat\":134.33,\"lng\":135.12,\"name\":\"Dnepr\"},{\"id\":10002,\"lat\":133.33,\"lng\":131.12,\"name\":\"Kiev\"},{\"id\":10003,\"lat\":123.12,\"lng\":141.56,\"name\":\"Kharkov\"}]}"))
                .andDo(print())
                .andDo(resultHandler);
    }

}
