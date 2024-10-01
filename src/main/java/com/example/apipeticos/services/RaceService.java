package com.example.apipeticos.services;

import com.example.apipeticos.models.Race;
import com.example.apipeticos.repositories.RaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RaceService {

    private final RaceRepository raceRepository;

    public RaceService(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    public List<Race> findAll() {
        return raceRepository.findAll();
    }
}
