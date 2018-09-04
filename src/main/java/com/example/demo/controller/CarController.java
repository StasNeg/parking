package com.example.demo.controller;

import com.example.demo.service.CarServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class CarController {
    private CarServise carServise;

    @Autowired
    public CarController(CarServise carServise) {
        this.carServise = carServise;
    }

    @RequestMapping(path = "/user/car")
    public String welcome(Map<String, Object> model) {
        model.put("cars",carServise.getCars());
        return "cars";
    }
}
