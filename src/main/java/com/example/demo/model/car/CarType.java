package com.example.demo.model.car;

import com.example.demo.model.AbstractBaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "car_type", uniqueConstraints = {@UniqueConstraint(columnNames = {"producer", "type"}, name = "meals_unique_user_datetime_idx")})
public class CarType extends AbstractBaseEntity {
    @Column(name = "producer", nullable = false)
    private String producer;

    @Column(name = "type", nullable = false)
    private String type;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "carType")//, cascade = CascadeType.REMOVE, orphanRemoval = true)
    protected List<Car> cars;


}
