package com.example.apipeticos.api.controllers;


import com.example.apipeticos.api.models.ApiResponse;
import com.example.apipeticos.api.models.RescuedLost;
import com.example.apipeticos.api.services.RescuedLostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RescuedLostController {

    @Autowired
    private RescuedLostService rescuedLostService;


}
