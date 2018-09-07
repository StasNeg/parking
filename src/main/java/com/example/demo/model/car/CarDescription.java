package com.example.demo.model.car;

import com.example.demo.model.AbstractBaseEntity;
import com.example.demo.model.enums.CarType;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "car_description", uniqueConstraints = {@UniqueConstraint(columnNames = {"producer", "name"}, name = "cars_desc_unique_name_producer_idx")})
public class CarDescription extends AbstractBaseEntity {

    @Column(name = "producer", nullable = false)
    private String producer;

    @Column(name = "name", nullable = false)
    private java.lang.String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private CarType type;


    public CarDescription() {
    }

    public CarDescription(String producer, java.lang.String name, CarType type, List<Car> cars) {
        this.producer = producer;
        this.name = name;
        this.type = type;

    }

    public CarDescription(Long id, String producer, java.lang.String name, CarType type, List<Car> cars) {
        super(id);
        this.producer = producer;
        this.name = name;
        this.type = type;

    }

    public CarDescription(String producer, java.lang.String name, CarType type) {
        this.producer = producer;
        this.name = name;
        this.type = type;
    }

    public CarDescription(Long id, String producer, java.lang.String name, CarType type) {
        super(id);
        this.producer = producer;
        this.name = name;
        this.type = type;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public CarType getType() {
        return type;
    }

    public void setType(CarType type) {
        this.type = type;
    }

}
