package com.example.demo.repository;

import com.example.demo.model.enums.Language;
import com.example.demo.model.place.ParkingPlace;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingPlaceRepository extends CrudRepository<ParkingPlace, Long> {
    @Query("select place from ParkingPlace place inner join fetch place.street street " +
            "inner join fetch street.city city " +
            "inner join fetch city.cities cities where cities.name =:city")
    Iterable<ParkingPlace> getAllByCity(String city);

    @Query("select place from ParkingPlace place inner join fetch place.street street " +
            "inner join fetch street.streetI18ns streetI18n "+
            "inner join fetch street.city city " +
            "where city.id =:id and streetI18n.lang =:lang")
    Iterable<ParkingPlace> getAllById(Long id, Language lang);
}
