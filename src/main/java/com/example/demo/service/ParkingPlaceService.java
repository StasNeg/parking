package com.example.demo.service;

import com.example.demo.model.place.ParkingPlace;
import com.example.demo.repository.ParkingPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ParkingPlaceService {

    private ParkingPlaceRepository parkingPlaceRepository;

    @Autowired
    public ParkingPlaceService(ParkingPlaceRepository parkingPlaceRepository) {
        this.parkingPlaceRepository = parkingPlaceRepository;
    }


    public Map<String, Set<String>> getAllCountryAndCity() {
        Iterable<Object> fromRepo = parkingPlaceRepository.getCountryAndCity();
        Map<String, Set<String>> result = new HashMap<>();
        for (Object o: fromRepo) {
            String city = (String) (((Object[])o)[0]);
            String county = (String) (((Object[])o)[1]);
            Set<String> temp = result.getOrDefault(county, new HashSet<>());
            temp.add(city);
            if(temp.size()==1) result.put(county,temp);
        }
       return result;
    }

    public Iterable<ParkingPlace> getAllByCity(String city) {
        return parkingPlaceRepository.getAllByCity(city);
    }
}
