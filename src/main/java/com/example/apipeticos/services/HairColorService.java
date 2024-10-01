package com.example.apipeticos.services;

import com.example.apipeticos.models.HairColor;
import com.example.apipeticos.repositories.HairColorRepository;
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
