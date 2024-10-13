package com.example.apipeticos.api.services;

import com.example.apipeticos.api.models.HairColor;
import com.example.apipeticos.api.repositories.HairColorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HairColorService {

    private final HairColorRepository hairColorRepository;

    public HairColorService(HairColorRepository hairColorRepository) {
        this.hairColorRepository = hairColorRepository;
    }

    public List<HairColor> findAll() {
        return hairColorRepository.findAll();
    }
}
