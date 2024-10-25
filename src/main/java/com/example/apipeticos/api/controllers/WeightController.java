package com.example.apipeticos.api.controllers;

import com.example.apipeticos.api.models.ApiResponseSQL;
import com.example.apipeticos.api.models.Weight;
import com.example.apipeticos.api.services.WeightService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weight")
public class WeightController {

    private final WeightService weightService;

    public WeightController(WeightService weightService){
        this.weightService=weightService;
    }

    @PostMapping("/insert")
    public ResponseEntity<ApiResponseSQL> insert(@RequestBody Weight weight){

        try{
            weightService.insert(weight);
            return ResponseEntity.ok( new ApiResponseSQL("Peso inserido com sucesso") );
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(new ApiResponseSQL(""));
        }
    }
}
