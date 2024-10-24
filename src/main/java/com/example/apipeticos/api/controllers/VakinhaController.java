package com.example.apipeticos.api.controllers;

import com.example.apipeticos.api.models.Vakinha;
import com.example.apipeticos.api.services.VakinhasService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/vakinha")
public class VakinhaController {

    private VakinhasService vakinhasService;

    public VakinhaController (VakinhasService vakinhasService){
        this.vakinhasService = vakinhasService;
    }

    @GetMapping("/getall")
    public List<Vakinha> getAll(){
        return vakinhasService.findAll();
    }

}
