package com.example.demo.service;

import com.example.demo.dto.CityDto;
import com.example.demo.model.enums.Language;
import com.example.demo.model.place.City;
import com.example.demo.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

@Service
public class CityService {
    private CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public Iterable<CityDto> getCities() {
        Language lang = Language.valueOf(LocaleContextHolder.getLocale().getLanguage().toUpperCase());
        List<CityDto> dtos = new LinkedList<>();
        for (City city : cityRepository.getCities(lang)) {
            dtos.add(CityDto.asTo(city));
        }
        return dtos;
    }
}
