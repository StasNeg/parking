package com.example.demo.model.car;

import com.example.demo.model.AbstractBaseEntity;
import com.example.demo.model.enums.CarType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "model", uniqueConstraints = {@UniqueConstraint(columnNames = {"producer", "name"}, name = "model_unique_name_producer_idx")})
public class Model extends AbstractBaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private CarType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producer", nullable = false)
    @NotNull
    private Producer producer;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "model")//, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonIgnore
    private List<Car> cars;

    public Model(CarType type, @NotNull Producer producer, String name) {
        this.type = type;
        this.producer = producer;
        this.name = name;
    }

    public Model() {
    }

    public Model(Long id, CarType type, @NotNull Producer producer, String name) {
        super(id);
        this.type = type;
        this.producer = producer;
        this.name = name;
    }

    public CarType getType() {
        return type;
    }

    public void setType(CarType type) {
        this.type = type;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
