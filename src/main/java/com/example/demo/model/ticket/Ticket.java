package com.example.demo.model.ticket;

import com.example.demo.model.AbstractBaseEntity;
import com.example.demo.model.car.Car;
import com.example.demo.model.place.ParkingPlace;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "tickets", uniqueConstraints = {@UniqueConstraint(columnNames = {"car", "date_time_start", "date_time_end"},
        name = "car_dateTime_ticket_unique_idx")})
public class Ticket extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car", nullable = false)
    @NotNull
    private Car car;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parkingPlace", nullable = false)
    @NotNull
    private ParkingPlace parkingPlace;

    @Column(name = "date_time_start", nullable = false)
    private LocalDateTime dateTimeStart;

    @Column(name = "date_time_end", nullable = false)
    private LocalDateTime dateTimeEnd;

    @Column(name = "ammount", nullable = false)
    private double ammount;

    public Ticket() {
    }

    public Ticket(@NotNull Car car, @NotNull ParkingPlace parkingPlace, LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd, double ammount) {

        this.car = car;
        this.parkingPlace = parkingPlace;
        this.dateTimeStart = dateTimeStart;
        this.dateTimeEnd = dateTimeEnd;
        this.ammount = ammount;
    }

    public Ticket(Long id, @NotNull Car car, @NotNull ParkingPlace parkingPlace, LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd, double ammount) {
        super(id);
        this.car = car;
        this.parkingPlace = parkingPlace;
        this.dateTimeStart = dateTimeStart;
        this.dateTimeEnd = dateTimeEnd;
        this.ammount = ammount;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public ParkingPlace getParkingPlace() {
        return parkingPlace;
    }

    public void setParkingPlace(ParkingPlace parkingPlace) {
        this.parkingPlace = parkingPlace;
    }

    public LocalDateTime getDateTimeStart() {
        return dateTimeStart;
    }

    public void setDateTimeStart(LocalDateTime dateTimeStart) {
        this.dateTimeStart = dateTimeStart;
    }

    public LocalDateTime getDateTimeEnd() {
        return dateTimeEnd;
    }

    public void setDateTimeEnd(LocalDateTime dateTimeEnd) {
        this.dateTimeEnd = dateTimeEnd;
    }

    public double getAmmount() {
        return ammount;
    }

    public void setAmmount(double ammount) {
        this.ammount = ammount;
    }

}
