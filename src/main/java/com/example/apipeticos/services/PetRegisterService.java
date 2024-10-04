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

    public List<PetRegister> findByUsername(String username) {
        return petRegisterRepository.findPetsByUsername(username);
    }

    public List<PetRegister> findAll() {
        return petRegisterRepository.findAll();
    }

    public void insertPet(PetRegister petRegister) {
        petRegisterRepository.insertPet(
                petRegister.getIdUser(),
                petRegister.getNickname(),
                petRegister.getAge(),
                petRegister.getSex(),
                petRegister.getSpecie(),
                petRegister.getRace(),
                petRegister.getSize(),
                petRegister.getColor(),
                petRegister.getDescription()
        );
    }

    public void deletePet(Integer id) {
        petRegisterRepository.deletePet(id);
    }

    public PetRegister findById(Integer id) {
        return petRegisterRepository.findById(id).orElseThrow(() -> new RuntimeException("Pet not found"));
    }
}
