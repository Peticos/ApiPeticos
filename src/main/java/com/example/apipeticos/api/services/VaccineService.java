package com.example.apipeticos.api.services;

import com.example.apipeticos.api.models.Vaccine;
import com.example.apipeticos.api.repositories.VaccineRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaccineService {

    private VaccineRepository vaccineRepository;

    public VaccineService(VaccineRepository vaccineRepository){
        this.vaccineRepository=vaccineRepository;
    }

    public List<Vaccine> findAll(){
        return vaccineRepository.findAll();
    }

    public List<Vaccine> findByIdPet(Integer id){
        return vaccineRepository.getVaccineByIdPet(id);
    }

    public Integer insertVaccine(Vaccine vaccine){
        vaccineRepository.save(vaccine);
        return vaccine.getIdVaccine();
    }
}
