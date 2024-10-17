package com.example.apipeticos.api.controllers;


import com.example.apipeticos.api.models.ApiResponse;
import com.example.apipeticos.api.models.RescuedLost;
import com.example.apipeticos.api.models.Users;
import com.example.apipeticos.api.services.RescuedLostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rescuedlost")
public class RescuedLostController {

    @Autowired
    private RescuedLostService rescuedLostService;

    @GetMapping("/getall")
    public List<RescuedLost> getAll(){
        return rescuedLostService.findAll();
    }

    @PostMapping("/insert")
    public ResponseEntity<ApiResponse> insert(@RequestBody RescuedLost rescuedLost, BindingResult result){
        if (result.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Entidade Invalida") );
        }else {
            rescuedLostService.insertRescuedLost(rescuedLost);
            return ResponseEntity.ok( new ApiResponse("Perdido  inserido com sucesso"));
        }
    }
}
