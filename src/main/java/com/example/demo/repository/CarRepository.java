package com.example.demo.repository;


import com.example.demo.model.car.Car;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {
    @Query("select car from Car car left join fetch car.carDescription where car.user.id =:id")
    Iterable<Car> findAllByUserId(Long id);
}