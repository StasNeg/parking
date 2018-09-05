package com.example.demo.model.car;

import com.example.demo.model.AbstractBaseEntity;
import com.example.demo.model.enums.CarProducer;
import com.example.demo.model.enums.CarType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "car_description", uniqueConstraints = {@UniqueConstraint(columnNames = {"producer", "name"}, name = "cars_desc_unique_name_producer_idx")})
public class CarDescription extends AbstractBaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "producer", nullable = false)
    private CarProducer producer;

    @Column(name = "name", nullable = false)
    private String name;


    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private CarType type;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "carDescription")//, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonIgnore
    protected List<Car> cars;

    public CarDescription() {
    }

    public CarDescription(CarProducer producer, String name, CarType type, List<Car> cars) {
        this.producer = producer;
        this.name = name;
        this.type = type;
        this.cars = cars;
    }

    public CarDescription(Long id, CarProducer producer, String name, CarType type, List<Car> cars) {
        super(id);
        this.producer = producer;
        this.name = name;
        this.type = type;
        this.cars = cars;
    }

    public CarDescription(CarProducer producer, String name, CarType type) {
        this.producer = producer;
        this.name = name;
        this.type = type;
    }

    public CarDescription(Long id, CarProducer producer, String name, CarType type) {
        super(id);
        this.producer = producer;
        this.name = name;
        this.type = type;
    }

    public CarProducer getProducer() {
        return producer;
    }

    public void setProducer(CarProducer producer) {
        this.producer = producer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CarType getType() {
        return type;
    }

    public void setType(CarType type) {
        this.type = type;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
