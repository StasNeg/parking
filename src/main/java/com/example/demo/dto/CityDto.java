package com.example.demo.dto;

import com.example.demo.model.place.City;

public class CityDto {
    private final Long id;
    private final double lat;
    private final double lng;
    private final String name;

    public CityDto(Long id, double lat, double lng, String name) {
        this.id = id;
        this.lat = lat;
        this.lng = lng;
        this.name = name;
    }

    public static CityDto asTo(City city) {
        return new CityDto(city.getId(), city.getLat(), city.getLng(), city.getCities().size() == 0 ? "No City in that language" : city.getCities().get(0).getName());
    }

    public Long getId() {
        return id;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public String getName() {
        return name;
    }
}
