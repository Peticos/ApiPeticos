package com.example.apipeticos.api.controllers;

import com.example.apipeticos.api.models.Admin;
import com.example.apipeticos.api.models.Users;
import com.example.apipeticos.api.services.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService){
        this.adminService=adminService;
    }

    @GetMapping("/getbyid/{id}")
    public Admin findByUsername(@PathVariable Integer id){
        return adminService.findById(id);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Admin admin) {
        if (adminService.authenticate(admin.getEmail(), admin.getPassword())) {
            admin = adminService.findByEmail(admin.getEmail());
            return ResponseEntity.ok(admin.getIdAdmin());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
        }
    }
}
