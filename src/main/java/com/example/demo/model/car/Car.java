package com.example.demo.model.car;


import com.example.demo.model.AbstractBaseEntity;
import com.example.demo.model.user.User;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "cars", uniqueConstraints = {@UniqueConstraint(columnNames = {"number"}, name = "number_unique_car_idx")})
public class Car extends AbstractBaseEntity {
    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "description")
    private String description;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cartype_id", nullable = false)
    @NotNull
    private CarDescription carDescription;

    public Car() {
    }

    public Car(Long id, String number, String description, @NotNull User user, @NotNull CarDescription carDescription) {
        super(id);
        this.number = number;
        this.description = description;
        this.user = user;
        this.carDescription = carDescription;
    }

    public Car(String number, String description, @NotNull User user, @NotNull CarDescription carDescription) {
        this.number = number;
        this.description = description;
        this.user = user;
        this.carDescription = carDescription;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CarDescription getCarDescription() {
        return carDescription;
    }

    public void setCarDescription(CarDescription carDescription) {
        this.carDescription = carDescription;
    }
}
