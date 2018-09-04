package com.example.demo.model.car;

import com.example.demo.model.AbstractBaseEntity;
import com.example.demo.model.enums.CarType;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "car_description", uniqueConstraints = {@UniqueConstraint(columnNames = {"producer", "name"}, name = "cars_desc_unique_name_producer_idx")})
public class CarDescription extends AbstractBaseEntity {
    @Column(name = "producer", nullable = false)
    private String producer;

    @Column(name = "name", nullable = false)
    private String name;


    @Enumerated(EnumType.STRING)
    private CarType type;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "carDescription")//, cascade = CascadeType.REMOVE, orphanRemoval = true)
    protected List<Car> cars;

    public CarDescription() {
    }

    public CarDescription(String producer, String name, CarType type, List<Car> cars) {
        this.producer = producer;
        this.name = name;
        this.type = type;
        this.cars = cars;
    }

    public CarDescription(Long id, String producer, String name, CarType type, List<Car> cars) {
        super(id);
        this.producer = producer;
        this.name = name;
        this.type = type;
        this.cars = cars;
    }

    public CarDescription(String producer, String name, CarType type) {
        this.producer = producer;
        this.name = name;
        this.type = type;
    }

    public CarDescription(Long id, String producer, String name, CarType type) {
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
