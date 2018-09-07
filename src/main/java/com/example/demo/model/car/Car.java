package com.example.demo.model.car;


import com.example.demo.model.AbstractBaseEntity;
import com.example.demo.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    @NotNull
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model", nullable = false)
    @NotNull
    private Model model;

    public Car() {
    }

    public Car(String number, String description, @NotNull Model model) {
        this.number = number;
        this.description = description;
        this.model = model;
    }

    public Car(Long id, String number, String description, @NotNull Model model) {
        super(id);
        this.number = number;
        this.description = description;
        this.model = model;
    }

    public Car(String number, String description, @NotNull User user, @NotNull Model model) {

        this.number = number;
        this.description = description;
        this.user = user;
        this.model = model;
    }

    public Car(Long id, String number, String description, @NotNull User user, @NotNull Model model) {
        super(id);
        this.number = number;
        this.description = description;
        this.user = user;
        this.model = model;
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

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

}
