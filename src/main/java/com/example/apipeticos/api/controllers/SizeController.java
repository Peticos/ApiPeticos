package com.example.apipeticos.api.controllers;

import com.example.apipeticos.api.models.Size;
import com.example.apipeticos.api.services.SizeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

@RestController
@RequestMapping("/api/size")
public class SizeController {

    private final SizeService sizeService;

    public SizeController(SizeService sizeService) {
        this.sizeService = sizeService;
    }



    @Operation(summary = "Busca todas as entradas de Size", description = "Retorna uma lista com todas as entradas do tipo Size.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de Size retornada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Size.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/getall")
    public ResponseEntity<?> findAll() {
        try {
            return ResponseEntity.ok(sizeService.findAll());
        }catch (HttpServerErrorException.InternalServerError internalServerError){
            return ResponseEntity.internalServerError().body("Erro interno no servidor");
        }
    }
}


