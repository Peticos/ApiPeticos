package com.example.apipeticos.api.controllers;

import com.example.apipeticos.api.models.Race;
import com.example.apipeticos.api.services.RaceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/race")
public class RaceController {

    private final RaceService raceService;

    public RaceController(RaceService raceService) {
        this.raceService = raceService;
    }

    @GetMapping("/getall")
    public List<Race> getAll() {
        return raceService.findAll();
    }
}
