package com.example.apipeticos.api.controllers;

import com.example.apipeticos.api.models.Vakinha;
import com.example.apipeticos.api.services.VakinhasService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vakinha")
public class VakinhaController {

    private VakinhasService vakinhasService;

    public VakinhaController (VakinhasService vakinhasService){
        this.vakinhasService = vakinhasService;
    }

    @GetMapping("/getall")
    public List<Vakinha> getAll(){
        return vakinhasService.findAll();
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insert(@RequestBody Vakinha vakinha){

        try {
            return ResponseEntity.ok(vakinhasService.insertVakinhaRPA(vakinha));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("");
        }
    }
}
