package com.example.apipeticos.api.controllers;


import com.example.apipeticos.api.models.ApiResponseSQL;
import com.example.apipeticos.api.models.RescuedLost;
import com.example.apipeticos.api.services.RescuedLostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

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
    public ResponseEntity<ApiResponseSQL> insert(@RequestBody RescuedLost rescuedLost, BindingResult result){
        if (result.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseSQL("Entidade Invalida") );
        }else {
            rescuedLostService.insertRescuedLost(rescuedLost);
            return ResponseEntity.ok( new ApiResponseSQL("Perdido  inserido com sucesso"));
        }
    }

    @GetMapping("/findbyid/{id}")
    public  List<RescuedLost> findById(@PathVariable Integer id){
        return rescuedLostService.findById(id);
    }

    @PutMapping("/findpet/{id}")
    public ResponseEntity<ApiResponseSQL> updateRescuedDate(@PathVariable Integer id, @RequestParam("rescuedDate") Date rescuedDate) {
        rescuedLostService.findPet(id, rescuedDate);
        return ResponseEntity.ok(new ApiResponseSQL("Pet Achado!"));
    }
}
