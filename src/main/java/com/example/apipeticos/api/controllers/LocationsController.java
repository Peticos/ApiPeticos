package com.example.apipeticos.api.controllers;

import com.example.apipeticos.api.models.Locations;
import com.example.apipeticos.api.services.LocationsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class LocationsController {

    private final LocationsService locationsService;

    LocationsController (LocationsService locationsService) {
        this.locationsService = locationsService;
    }

    @GetMapping("/getall")
    public List<Locations> getAll() {
        return locationsService.getAll();
    }

    @GetMapping("/getbytype/{id}")
    public List<Locations> getByType(@PathVariable Integer id) {
        return locationsService.findByType(id);
    }
}
