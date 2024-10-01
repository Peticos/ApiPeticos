package com.example.apipeticos.controllers;

import com.example.apipeticos.models.Size;
import com.example.apipeticos.services.SizeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/size")
public class SizeController {

    private final SizeService sizeService;

    public SizeController(SizeService sizeService) {
        this.sizeService = sizeService;
    }



    @GetMapping("/getall")
    public List<Size> findAll() {
        return sizeService.findAll();
    }
}


