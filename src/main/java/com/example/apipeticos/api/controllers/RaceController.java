package com.example.apipeticos.api.controllers;

import com.example.apipeticos.api.models.Race;
import com.example.apipeticos.api.models.Size;
import com.example.apipeticos.api.services.RaceService;
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
@RequestMapping("/api/race")
public class RaceController {

    private final RaceService raceService;

    public RaceController(RaceService raceService) {
        this.raceService = raceService;
    }

    @Operation(summary = "Busca todas as entradas de Race", description = "Retorna uma lista com todas as entradas do tipo Race. Utilizada para cadastro de pet.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de Race retornada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Size.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/getall")
    public ResponseEntity getAll() {
        try {
            return ResponseEntity.ok(raceService.findAll());

        }catch (HttpServerErrorException.InternalServerError e){
            return ResponseEntity.internalServerError().body("Erro interno no servidor");
        }
    }
}
