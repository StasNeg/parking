package com.example.demo.controller;

import com.example.demo.dto.CarDto;
import com.example.demo.model.car.Car;
import com.example.demo.model.enums.CarType;
import com.example.demo.service.CarServise;
import com.example.demo.service.ModelService;
import com.example.demo.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class CarController {
    private CarServise carServise;
    private ProducerService producerService;
    private ModelService modelService;

    @Autowired
    public CarController(CarServise carServise, ProducerService producerService, ModelService modelService) {
        this.carServise = carServise;
        this.producerService = producerService;
        this.modelService = modelService;
    }

    @RequestMapping(path = "/user/car")
    public java.lang.String welcome(Map<java.lang.String, Object> model) {
        model.put("cars", carServise.getCars());
        model.put("producers", producerService.getAll());
        model.put("types", CarType.values());
        return "cars";
    }

    @GetMapping(path = "/user/car/model")
    public @ResponseBody
    Iterable<String> getUserByEmail(@RequestParam String producer) {
        // This returns a JSON or XML with the users
        return modelService.getByProducerName(producer);
    }


    @PostMapping(path = "/user/car/save")
    public @ResponseBody
    CarDto saveCar(@RequestBody CarDto car) {
        // This returns a JSON or XML with the users


        return  CarDto.asTo(carServise.save(car));


    }
}
