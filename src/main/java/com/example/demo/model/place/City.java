package com.example.demo.model.place;

import com.example.demo.model.AbstractBaseEntity;
import com.example.demo.model.place.i18n.CityI18n;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;


@Entity
@Table(name = "city"
//        uniqueConstraints = {
//                @UniqueConstraint(columnNames = {"name", "lang"}, name = "street_city_unique_name_idx")}
)
public class City extends AbstractBaseEntity {

    private double lat;
    private double lng;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "city")
    @JsonIgnore
    private List<Street> streets;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "city")//, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonIgnore
    private List<CityI18n> cities;

    public City() {
    }

    public City(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public City(Long id, double lat, double lng) {
        super(id);
        this.lat = lat;
        this.lng = lng;
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

    public List<Street> getStreets() {
        return streets;
    }

    public void setStreets(List<Street> streets) {
        this.streets = streets;
    }

    public List<CityI18n> getCities() {
        return cities;
    }

    public void setCities(List<CityI18n> cities) {
        this.cities = cities;
    }
}
