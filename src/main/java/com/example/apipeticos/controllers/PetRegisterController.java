package com.example.apipeticos.controllers;

import com.example.apipeticos.models.PetRegister;
import com.example.apipeticos.services.PetRegisterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
