package com.example.demo.controller;

import com.example.demo.dto.TicketDto;
import com.example.demo.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class ParkingController {

    private TicketService ticketService;

    @Autowired
    public ParkingController(TicketService ticketService) {
        this.ticketService = ticketService;
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

}
