package com.example.demo.model.place;

import com.example.demo.model.AbstractBaseEntity;
import com.example.demo.model.place.i18n.StreetI18n;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "street"
//        uniqueConstraints = {
//                @UniqueConstraint(columnNames = {"city", "type"}, name = "street_city_unique_name_idx")}
)
public class Street extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    private String type;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "street")
    @JsonIgnore
    private List<StreetI18n> streetI18ns;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "street")
    @JsonIgnore
    private List<ParkingPlace> parkingPlaces;

    public Street() {
    }

    public Street(City city, String type) {
        this.city = city;
        this.type = type;
    }

    public Street(Long id, City city, String type) {
        super(id);
        this.city = city;
        this.type = type;
    }

    public Street(City city, String type, List<StreetI18n> streetI18ns, List<ParkingPlace> parkingPlaces) {
        this.city = city;
        this.type = type;
        this.streetI18ns = streetI18ns;
        this.parkingPlaces = parkingPlaces;
    }

    public Street(Long id, City city, String type, List<StreetI18n> streetI18ns, List<ParkingPlace> parkingPlaces) {
        super(id);
        this.city = city;
        this.type = type;
        this.streetI18ns = streetI18ns;
        this.parkingPlaces = parkingPlaces;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<StreetI18n> getStreetI18ns() {
        return streetI18ns;
    }

    public void setStreetI18ns(List<StreetI18n> streetI18ns) {
        this.streetI18ns = streetI18ns;
    }

    public List<ParkingPlace> getParkingPlaces() {
        return parkingPlaces;
    }

    public void setParkingPlaces(List<ParkingPlace> parkingPlaces) {
        this.parkingPlaces = parkingPlaces;
    }
}