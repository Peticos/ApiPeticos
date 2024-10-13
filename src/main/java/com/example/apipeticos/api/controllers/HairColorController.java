package com.example.apipeticos.api.controllers;

import com.example.apipeticos.api.models.HairColor;
import com.example.apipeticos.api.services.HairColorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/haircolor")
public class HairColorController {

    private final HairColorService hairColorService;

    public HairColorController(HairColorService hairColorService) {
        this.hairColorService = hairColorService;
    }


    @GetMapping("/getall")
    public List<HairColor> getAll() {
        return hairColorService.findAll();
    }
}
