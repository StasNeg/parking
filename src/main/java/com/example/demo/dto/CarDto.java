package com.example.demo.dto;

import com.example.demo.model.car.Car;
import com.example.demo.model.enums.CarType;

public class CarDto {
    private Long id;
    private String number;
    private String model;
    private CarType carType;
    private String producer;
    private String description;

    public CarDto() {
    }

    public CarDto(long id, String number, String model, CarType carType, String producer, String description) {

        this.id = id;
        this.number = number;
        this.model = model;
        this.carType = carType;
        this.producer = producer;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public static CarDto asTo(Car car){
        return new CarDto(car.getId(), car.getNumber(),car.getModel().getName(),car.getModel().getType(),car.getModel().getProducer().getName(),car.getDescription());
    }
}
