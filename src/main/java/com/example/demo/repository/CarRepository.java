package com.example.demo.repository;


import com.example.demo.model.car.Car;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {
    @Query("select car from Car car join fetch car.model m join fetch m.producer where car.user.id =:id")
    Iterable<Car> findAllByUserId(Long id);
}
