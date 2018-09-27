package com.example.demo.model.place.i18n;

import com.example.demo.model.AbstractBaseEntity;
import com.example.demo.model.enums.Language;
import com.example.demo.model.place.City;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "city_i18n",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"name", "lang"}, name = "city_unique_name_idx")}
)
public class CityI18n extends AbstractBaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "lang", nullable = false)
    private Language lang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", nullable = false)
    @NotNull
    private City city;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    public CityI18n() {
    }

    public CityI18n(Language lang, @NotNull City city, @NotNull String name) {
        this.lang = lang;
        this.city = city;
        this.name = name;
    }

    public CityI18n(Long id, Language lang, @NotNull City city, @NotNull String name) {
        super(id);
        this.lang = lang;
        this.city = city;
        this.name = name;
    }

    public Language getLang() {
        return lang;
    }

    public void setLang(Language lang) {
        this.lang = lang;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
