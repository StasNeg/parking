package com.example.demo.model.place;


import com.example.demo.model.AbstractBaseEntity;
import com.example.demo.model.place.i18n.StreetI18n;
import com.example.demo.model.ticket.Ticket;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "parking_place", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"street_id", "street_number"}, name = "number_street_city_unique_address_idx"),
        @UniqueConstraint(columnNames = {"lng", "lat"}, name = "lng_lat_unique_address_idx")
})
public class ParkingPlace extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "street_id", nullable = false)
    @NotNull
    @JsonIgnore
    private Street street;

    @Column(name = "street_number", nullable = false)
    private String streetNumber;

    @Column(name = "lat", nullable = false)
    private double lat;

    @Column(name = "lng", nullable = false)
    private double lng;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parkingPlace")
    @JsonIgnore
    private List<Ticket> tickets;

    public ParkingPlace() {
    }

    public ParkingPlace(@NotNull Street street, String streetNumber, double lat, double lng) {
        this.street = street;
        this.streetNumber = streetNumber;
        this.lat = lat;
        this.lng = lng;
    }

    public ParkingPlace(Long id, @NotNull Street street, String streetNumber, double lat, double lng) {
        super(id);
        this.street = street;
        this.streetNumber = streetNumber;
        this.lat = lat;
        this.lng = lng;
    }

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
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

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}