package com.example.demo.controller;

import com.example.demo.dto.ParkingPlaceDto;
import com.example.demo.dto.TicketDto;
import com.example.demo.service.CarService;
import com.example.demo.service.CityService;
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
    private CityService cityService;

    @Autowired
    public ParkingController(TicketService ticketService, ParkingPlaceService parkingPlaceService, CarService carService, CityService cityService) {
        this.ticketService = ticketService;
        this.parkingPlaceService = parkingPlaceService;
        this.carService = carService;
        this.cityService = cityService;
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

    @GetMapping(path = "/user/ticket/cities")
    public @ResponseBody
    Map getCity() {
        // This returns a JSON or XML with the users
        Map result = new HashMap();
        result.put("city", cityService.getCities());
        result.put("cars", carService.getCars());
        return result;
    }

    @GetMapping(path = "/user/ticket/placeByCity")
    public @ResponseBody
    Iterable<ParkingPlaceDto> getplacesByCity(@RequestParam String city) {
        // This returns a JSON or XML with the users
        return parkingPlaceService.getAllByCity(city);
    }
}
