package com.example.demo.model.place;


import com.example.demo.model.AbstractBaseEntity;
import com.example.demo.model.ticket.Ticket;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "parking_place", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"street", "city", "street_number"}, name = "number_street_city_unique_address_idx"),
        @UniqueConstraint(columnNames = {"lng", "lat"}, name = "lng_lat_unique_address_idx")
})
public class ParkingPlace extends AbstractBaseEntity {

    @Column(name = "street", nullable = false)
    private String street;
    @Column(name = "city", nullable = false)
    private String city;
    @Column(name = "street_number", nullable = false)
    private String streetNumber;
    @Column(name = "lat", nullable = false)
    private double lat;
    @Column(name = "lng", nullable = false)
    private double lng;
    @Column(name = "country", nullable = false)
    private String country;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parkingPlace")//, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonIgnore
    private List<Ticket> tickets;

    public ParkingPlace() {
    }

    public ParkingPlace(String street, String city, String streetNumber, double lat, double lng, String country) {
        this.street = street;
        this.city = city;
        this.streetNumber = streetNumber;
        this.lat = lat;
        this.lng = lng;
        this.country = country;
    }

    public ParkingPlace(Long id, String street, String city, String streetNumber, double lat, double lng, String country) {
        super(id);
        this.street = street;
        this.city = city;
        this.streetNumber = streetNumber;
        this.lat = lat;
        this.lng = lng;
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
