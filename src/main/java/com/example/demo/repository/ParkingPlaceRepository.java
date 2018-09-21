package com.example.demo.repository;

import com.example.demo.model.place.ParkingPlace;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingPlaceRepository extends CrudRepository<ParkingPlace, Long> {
    @Query("select place.city, place.country from ParkingPlace place GROUP BY place.country, place.city")
    Iterable<Object> getCountryAndCity();

    Iterable<ParkingPlace> getAllByCity(String city);
}
