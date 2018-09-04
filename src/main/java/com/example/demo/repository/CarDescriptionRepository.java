package com.example.demo.repository;

import com.example.demo.model.car.CarDescription;
import org.springframework.data.repository.CrudRepository;

public interface CarDescriptionRepository extends CrudRepository<CarDescription, Long> {
}
