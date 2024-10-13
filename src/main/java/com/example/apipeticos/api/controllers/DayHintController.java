package com.example.apipeticos.api.controllers;


import com.example.apipeticos.api.models.DayHint;
import com.example.apipeticos.api.services.DayHintService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dayhint")
public class DayHintController {


    private final DayHintService dayHintService;

    public DayHintController(DayHintService dayHintService) {
        this.dayHintService = dayHintService;
    }


    @GetMapping("/getall")
    public List<DayHint> getAll(){
        return dayHintService.findAll();
    }

}
