package com.example.demo.controller;

import com.example.demo.dto.CarDto;
import com.example.demo.model.car.Car;
import com.example.demo.model.enums.CarType;
import com.example.demo.service.CarService;
import com.example.demo.service.ModelService;
import com.example.demo.service.ProducerService;
import com.example.demo.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@Controller
public class CarController {
    private CarService carService;
    private ProducerService producerService;
    private ModelService modelService;

    @Autowired
    public CarController(CarService carService, ProducerService producerService, ModelService modelService) {
        this.carService = carService;
        this.producerService = producerService;
        this.modelService = modelService;
    }

    @RequestMapping(path = "/user/car")
    public java.lang.String welcome(Map<java.lang.String, Object> model) {
        model.put("cars", carService.getCars());
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
        return CarDto.asTo(carService.save(car));
    }

    @GetMapping(path = "/user/car/uniqueNumber")
    public @ResponseBody
    String isUniqueEmail(@RequestParam String carNumber, @RequestParam String id) {
        Car temp = carService.getByNumber(carNumber);
        if (Objects.isNull(temp))
            return "true";
        else if (!carNumber.isEmpty() && !id.isEmpty()
                && temp.getUser().getId().equals(UserUtil.getAutorizedId())
                && temp.getId().equals(Long.parseLong(id)))
            return "true";
        else if (!carNumber.isEmpty() && !temp.getUser().getId().equals(UserUtil.getAutorizedId()))
            return "true";
        return "false";
    }
}
