package com.example.apipeticos.api.controllers;

import com.example.apipeticos.api.models.ApiResponseSQL;
import com.example.apipeticos.api.models.PetRegister;
import com.example.apipeticos.api.services.PetRegisterService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/petregister")
public class PetRegisterController {

    private final PetRegisterService petRegisterService;

    public PetRegisterController(PetRegisterService petRegisterService) {
        this.petRegisterService = petRegisterService;
    }

    @GetMapping("/findbyid/{id}")
    public PetRegister findById(@PathVariable Integer id){

        return petRegisterService.findById(id);
    }


    @PostMapping("/insert")
    public Integer inserirUsuario( @RequestBody PetRegister petRegister) {
        return petRegisterService.insertPet(petRegister);

    }
    @GetMapping("/getbyusername/{username}")
    public List<PetRegister> findByUserUsername(@PathVariable String username){
        return petRegisterService.findByUserUsername(username);
    }

    @DeleteMapping("/deletebyid/{id}")
    public ResponseEntity<ApiResponseSQL> deleteById(@PathVariable Integer id){

        if (petRegisterService.findById(id) != null) {
            petRegisterService.deletePet(id);
            return ResponseEntity.ok(new ApiResponseSQL("Pet deleted with sucess "));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponseSQL("Pet not found"));
        }
    }

    @GetMapping("/nicknames")
    public List<String> getNicknamesByPetIds(@RequestParam List<Integer> ids) {
        return petRegisterService.findNicknamesById(ids);
    }

    @PostMapping("/update")
    public ResponseEntity<ApiResponseSQL> updatePet(@Valid @RequestBody PetRegister petRegister, BindingResult result){
        if (result.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseSQL("Erro ao atualizar"));
        }else {
            petRegisterService.updatePet(petRegister);
            return ResponseEntity.ok(new ApiResponseSQL("Atualizado com sucesso"));
        }
    }



}
