package com.example.demo.repository;

import com.example.demo.model.enums.Language;
import com.example.demo.model.place.City;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {
    @Query("select city from City city join fetch city.cities names where names.lang =:lang")
    Iterable<City> getCities(Language lang);
}
