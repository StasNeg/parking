package com.example.demo.service;

import com.example.demo.model.car.Car;
import com.example.demo.repository.CarRepository;
import com.example.demo.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarServise {
    private CarRepository carRepository;

    @Autowired
    public CarServise(CarRepository carRepository) {
        this.carRepository = carRepository;
    }


    public Iterable<Car> getCars() {
        return carRepository.findAllByUserId(UserUtil.getAutorizedId());
    }
}
