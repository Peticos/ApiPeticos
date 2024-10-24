package com.example.apipeticos.api.controllers;

import com.example.apipeticos.api.models.Vaccine;
import com.example.apipeticos.api.models.Vakinha;
import com.example.apipeticos.api.services.VaccineService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/vaccine")
public class VaccineController {

    private VaccineService vaccineService;

    public VaccineController(VaccineService vaccineService){
        this.vaccineService=vaccineService;
    }

    @GetMapping("/getall")
    public List<Vaccine> getAll(){
        return vaccineService.findAll();
    }

    @GetMapping("/findbyid/{id}")
    public List<Vaccine> findByIdPet(@PathVariable Integer id){
        return vaccineService.findByIdPet(id);
    }
}
