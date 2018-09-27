package com.example.demo.model.place.i18n;

import com.example.demo.model.AbstractBaseEntity;
import com.example.demo.model.enums.Language;
import com.example.demo.model.place.ParkingPlace;
import com.example.demo.model.place.Street;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "street_i18n",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"name", "lang"}, name = "street_city_unique_name_idx")}
//        @UniqueConstraint(columnNames = {"lng", "lat"}, name = "lng_lat_unique_address_idx")}
)
public class StreetI18n extends AbstractBaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "lang", nullable = false)
    private Language lang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "street_id", nullable = false)
    @NotNull
    private Street street;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    public StreetI18n() {
    }

    public StreetI18n(Language lang, @NotNull Street street, @NotNull String name) {
        this.lang = lang;
        this.street = street;
        this.name = name;
    }

    public StreetI18n(Long id, Language lang, @NotNull Street street, @NotNull String name) {
        super(id);
        this.lang = lang;
        this.street = street;
        this.name = name;
    }

    public Language getLang() {
        return lang;
    }

    public void setLang(Language lang) {
        this.lang = lang;
    }

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
