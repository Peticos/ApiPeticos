package com.example.apipeticos.api.services;

import com.example.apipeticos.api.models.PetRegister;
import com.example.apipeticos.api.repositories.PetRegisterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetRegisterService {

    private final PetRegisterRepository petRegisterRepository;

    public PetRegisterService(PetRegisterRepository petRegisterRepository) {
        this.petRegisterRepository = petRegisterRepository;
    }

    public List<PetRegister> findByUserUsername(String username) {
        return petRegisterRepository.findPetsByUserUsername(username);
    }

    public List<PetRegister> findAll() {
        return petRegisterRepository.findAll();
    }

    public  Integer insertPet(PetRegister petRegister) {
         return petRegisterRepository.insertPet(
                petRegister.getUser(),
                petRegister.getNickname(),
                petRegister.getAge(),
                petRegister.getSex(),
                petRegister.getSpecie(),
                petRegister.getRace(),
                petRegister.getSize(),
                petRegister.getColor()
        );
    }

    public void deletePet(Integer id) {
        petRegisterRepository.deletePet(id);
    }

    public List<String> findNicknamesById(List<Integer> ids){
        return petRegisterRepository.findNicknamesByIds(ids);
    }

    public PetRegister findById(Integer id) {
        return petRegisterRepository.findById(id).orElseThrow(() -> new RuntimeException("Pet not found"));
    }

    public void updatePet(PetRegister petRegister){
        petRegisterRepository.updatePet(petRegister.getIdPet(), petRegister.getNickname(), petRegister.getAge(), petRegister.getSex(), petRegister.getSpecie(), petRegister.getRace(), petRegister.getSize(), petRegister.getColor());
    }

}
