package com.example.demo.repository;

import com.example.demo.model.place.Street;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StreetRepository extends CrudRepository<Street, Long> {
}
