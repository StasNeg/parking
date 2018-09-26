package com.example.demo.repository.i18n;

import com.example.demo.model.place.i18n.CityI18n;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface City18iRepository extends CrudRepository<CityI18n, Long> {
}
