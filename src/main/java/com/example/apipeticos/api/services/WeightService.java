package com.example.apipeticos.api.services;

import com.example.apipeticos.api.repositories.WeightRepository;
import org.springframework.stereotype.Service;

@Service
public class WeightService {

    private WeightRepository weightRepository;

    public WeightService (WeightRepository weightRepository){
        this.weightRepository=weightRepository;
    }

}
