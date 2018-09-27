package com.example.demo.dto;

public class ParkingPlaceDto {
    private final Long id;
    private final String name;
    private final String streetNumber;
    private final double lat;
    private final double lng;

    public ParkingPlaceDto(Long id, String name, String streetNumber, double lat, double lng) {
        this.id = id;
        this.name = name;
        this.streetNumber = streetNumber;
        this.lat = lat;
        this.lng = lng;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }
}
