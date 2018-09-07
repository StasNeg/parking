package com.example.demo.repository;

import com.example.demo.model.car.CarDescription;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarDescriptionRepository extends CrudRepository<CarDescription, Long> {
}
