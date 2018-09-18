package com.example.demo.repository;

import com.example.demo.model.place.ParkingPlace;
import org.springframework.data.repository.CrudRepository;

public interface ParkingPlaceRepository extends CrudRepository<ParkingPlace, Long> {
}
