package com.example.apipeticos.api.controllers;

import com.example.apipeticos.api.models.Admin;
import com.example.apipeticos.api.models.Users;
import com.example.apipeticos.api.services.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Busca admin pelo ID", description = "Este endpoint retorna os dados do administrador com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Administrador encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Admin.class))),
            @ApiResponse(responseCode = "404", description = "Administrador não encontrado para o ID fornecido",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno ao recuperar o administrador",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    })
    @GetMapping("/getbyid/{id}")
    public Admin findByUsername(@PathVariable Integer id){
        return adminService.findById(id);
    }

    @Operation(summary = "Realiza o login do administrador", description = "Este endpoint autentica um administrador com base no email e senha fornecidos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login bem-sucedido",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Integer.class))),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno durante o processo de login",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    })
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
