package com.example.demo.repository.i18n;

import com.example.demo.model.place.i18n.StreetI18n;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Street18iRepository extends CrudRepository<StreetI18n, Long> {
}
