package com.example.apipeticos.api.services;

import com.example.apipeticos.api.models.Weight;
import com.example.apipeticos.api.repositories.WeightRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeightService {

    private WeightRepository weightRepository;

    public WeightService (WeightRepository weightRepository){
        this.weightRepository=weightRepository;
    }

    public void insert(Weight weight){
        weightRepository.save(weight);
    }

    public List<Weight> findAll(){
        return weightRepository.findAll();
    }

}
