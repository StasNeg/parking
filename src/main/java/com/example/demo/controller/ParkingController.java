package com.example.demo.controller;

import com.example.demo.dto.TicketDto;
import com.example.demo.model.place.City;
import com.example.demo.model.place.ParkingPlace;
import com.example.demo.service.CarService;
import com.example.demo.service.ParkingPlaceService;
import com.example.demo.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ParkingController {

    private TicketService ticketService;
    private ParkingPlaceService parkingPlaceService;
    private CarService carService;
    @Autowired
    public ParkingController(TicketService ticketService, ParkingPlaceService parkingPlaceService, CarService carService) {
        this.ticketService = ticketService;
        this.parkingPlaceService = parkingPlaceService;
        this.carService = carService;
    }

    @RequestMapping(path = "/user/parking")
    public java.lang.String welcome(Map<String, Object> model) {
        return "tickets";
    }

    @GetMapping(path = "/user/ticket/all")
    public @ResponseBody
    Iterable<TicketDto> getAll() {
        // This returns a JSON or XML with the users
        return ticketService.getAll();
    }

    @GetMapping(path = "/user/ticket/countryAndCity")
    public @ResponseBody
    Map getCountryAndCity() {
        // This returns a JSON or XML with the users
        Map result = new HashMap();
        result.put("countryAndCity",parkingPlaceService.getAllCountryAndCity());
        result.put("cars", carService.getCars());
        return result;
    }

    @GetMapping(path = "/user/ticket/placeByCity")
    public @ResponseBody
    Iterable<ParkingPlace> getplacesByCity(@RequestParam String city) {
        // This returns a JSON or XML with the users

        return parkingPlaceService.getAllByCity(city);
    }
}
