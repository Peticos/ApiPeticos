package com.example.apipeticos.api.services;

import com.example.apipeticos.api.models.Doses;
import com.example.apipeticos.api.repositories.DosesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DosesService {


    private final DosesRepository dosesRepository;

    public DosesService (DosesRepository dosesRepository){
        this.dosesRepository=dosesRepository;
    }

    public void insertDoses(Doses doses){
        dosesRepository.save(doses);
    }

    public List<Doses> getByIdVaccine(Integer id){
        return dosesRepository.findByIdVaccine(id);
    }
}
