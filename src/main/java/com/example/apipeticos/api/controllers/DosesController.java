package com.example.apipeticos.api.controllers;

import com.example.apipeticos.api.models.ApiResponseSQL;
import com.example.apipeticos.api.models.Doses;
import com.example.apipeticos.api.services.DosesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doses")
public class DosesController {

    private final DosesService dosesService;

    public DosesController(DosesService dosesService){
        this.dosesService=dosesService;
    }

    @GetMapping("/getbyvaccine/{id}")
    public List<Doses> getByVaccine(@PathVariable Integer id){
        return dosesService.getByIdVaccine(id);
    }

    @PostMapping("/insert")
    public ResponseEntity<ApiResponseSQL> insert(@RequestBody Doses doses){
        try {
            dosesService.insertDoses(doses);
            return ResponseEntity.ok(new ApiResponseSQL("Doses inserido com sucesso!"));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(new ApiResponseSQL(e.getMessage()));
        }
    }

}
