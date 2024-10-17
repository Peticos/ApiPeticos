package com.example.apipeticos.api.controllers;

import com.example.apipeticos.api.models.ApiResponse;
import com.example.apipeticos.api.models.PetRegister;
import com.example.apipeticos.api.services.PetRegisterService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/petregister")
public class PetRegisterController {

    private final PetRegisterService petRegisterService;

    public PetRegisterController(PetRegisterService petRegisterService) {
        this.petRegisterService = petRegisterService;
    }


    @GetMapping("/getall")
    public List<PetRegister> getAll(){
        return petRegisterService.findAll();
    }

    @PostMapping("/insert")
    public ResponseEntity<ApiResponse> inserirUsuario( @RequestBody PetRegister petRegister) {
        petRegisterService.insertPet(petRegister);

        return ResponseEntity.ok( new ApiResponse("Pet inserido com sucesso"));

    }
    @GetMapping("/getbyusername/{username}")
    public List<PetRegister> findByUserUsername(@PathVariable String username){
        return petRegisterService.findByUserUsername(username);
    }

    @DeleteMapping("/deletebyid/{id}")
    public ResponseEntity<ApiResponse> deleteById(@PathVariable Integer id){

        if (petRegisterService.findById(id) != null) {
            petRegisterService.deletePet(id);
            return ResponseEntity.ok(new ApiResponse("Pet deleted with sucess "));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Pet not found"));
        }
    }




}
