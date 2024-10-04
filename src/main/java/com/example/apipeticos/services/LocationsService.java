package com.example.apipeticos.services;

import com.example.apipeticos.models.Locations;
import com.example.apipeticos.repositories.LocationsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationsService {

    final LocationsRepository locationsRepository;

    public LocationsService(LocationsRepository locationsRepository) {
        this.locationsRepository = locationsRepository;
    }

    public List<Locations> getAll() {
        return locationsRepository.findAll();
    }

    public List<Locations> findByType(int idLocaltype){
        return locationsRepository.findByIdLocalType(idLocaltype);
    }

}
