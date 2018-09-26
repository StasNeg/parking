package com.example.demo.service;

import com.example.demo.dto.ParkingPlaceDto;
import com.example.demo.model.enums.Language;
import com.example.demo.repository.ParkingPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ParkingPlaceService {

    private ParkingPlaceRepository parkingPlaceRepository;

    @Autowired
    public ParkingPlaceService(ParkingPlaceRepository parkingPlaceRepository) {
        this.parkingPlaceRepository = parkingPlaceRepository;
    }

    // TODO: 9/26/2018 rewrite to DTO in Select query 
    public Iterable<ParkingPlaceDto> getAllByCity(String id) {
        List<ParkingPlaceDto> parkingPlaceDtos = new LinkedList<>();
        parkingPlaceRepository.getAllById(Long.parseLong(id), Language.valueOf(LocaleContextHolder.getLocale().getLanguage().toUpperCase())).
                forEach(item -> parkingPlaceDtos.add(
                        new ParkingPlaceDto(item.getId(), item.getStreet().getStreetI18ns().get(0).getName(), item.getStreetNumber(),
                                item.getLat(), item.getLng())));
        return parkingPlaceDtos;
    }
}
