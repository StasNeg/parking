package com.example.demo.dto;

import com.example.demo.model.ticket.Ticket;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TicketDto {
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);

    private Long id;

    private final String street;

    private final String country;

    private final String city;
    private final String streetNumber;

    @JsonFormat(pattern = DATE_TIME_PATTERN, timezone = "UTC")
    private final LocalDateTime dateTimeStart;

    @JsonFormat(pattern = DATE_TIME_PATTERN, timezone = "UTC")
    private final LocalDateTime dateTimeEnd;

    private final String carNumber;
    private final CarDto car;
    public TicketDto(String street, String country, String city, String streetNumber, LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd, String carNumber, CarDto car) {
        this.street = street;
        this.country = country;
        this.city = city;
        this.streetNumber = streetNumber;
        this.dateTimeStart = dateTimeStart;
        this.dateTimeEnd = dateTimeEnd;
        this.carNumber = carNumber;
        this.car = car;
    }
    public TicketDto(Long id, String street, String country, String city, String streetNumber, LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd, String carNumber, CarDto car) {
        this.id = id;
        this.street = street;
        this.country = country;
        this.city = city;
        this.streetNumber = streetNumber;
        this.dateTimeStart = dateTimeStart;
        this.dateTimeEnd = dateTimeEnd;
        this.carNumber = carNumber;
        this.car = car;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public LocalDateTime getDateTimeStart() {
        return dateTimeStart;
    }

    public LocalDateTime getDateTimeEnd() {
        return dateTimeEnd;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public CarDto getCar() {
        return car;
    }

    public static TicketDto asTo(Ticket ticket) {
        return new TicketDto(ticket.getId(),ticket.getParkingPlace().getStreet(), ticket.getParkingPlace().getCountry(), ticket.getParkingPlace().getCity(),
                ticket.getParkingPlace().getStreetNumber(), ticket.getDateTimeStart(), ticket.getDateTimeEnd(), ticket.getCar().getNumber(), CarDto.asTo(ticket.getCar()));
    }
}
