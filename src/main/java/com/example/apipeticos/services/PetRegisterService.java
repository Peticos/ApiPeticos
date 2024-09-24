package com.example.apipeticos.services;

import com.example.apipeticos.models.PetRegister;
import com.example.apipeticos.repositories.PetRegisterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetRegisterService {

    private final PetRegisterRepository petRegisterRepository;

    public PetRegisterService(PetRegisterRepository petRegisterRepository) {
        this.petRegisterRepository = petRegisterRepository;
    }

    public List<PetRegister> findAll() {
        return petRegisterRepository.findAll();
    }
}
