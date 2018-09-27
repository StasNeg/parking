package com.example.demo.repository;

import com.example.demo.model.car.Model;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModelRepository extends CrudRepository<Model, Long> {

    @Query("select model from Model model join fetch model.producer where model.name =:modelName")
    Optional<Model> findByModelName(String modelName);

    @Query("select model.name from Model model where model.producer.name =:producer")
    Iterable<String> getByProducerName(String producer);

    @Query("select model from Model model where model.producer.name =:producer and model.name =:name")
    Optional<Model> getModelByModelNameAndProducerName(String producer, String name);
}
