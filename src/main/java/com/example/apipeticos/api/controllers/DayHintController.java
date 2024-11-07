package com.example.apipeticos.api.controllers;


import com.example.apipeticos.api.models.DayHint;
import com.example.apipeticos.api.services.DayHintService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dayhint")
public class DayHintController {


    private final DayHintService dayHintService;

    public DayHintController(DayHintService dayHintService) {
        this.dayHintService = dayHintService;
    }


    @Operation(summary = "Retorna todas as dicas do dia", description = "Este endpoint retorna todas as dicas do dia registradas no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de dicas do dia recuperada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno ao recuperar as dicas",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    })
    @GetMapping("/getall")
    public List<DayHint> getAll(){
        return dayHintService.findAll();
    }

    @Operation(summary = "Retorna dicas do dia aleatórias", description = "Este endpoint retorna uma seleção aleatória de dicas do dia.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dicas do dia aleatórias recuperadas com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno ao recuperar as dicas aleatórias",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    })
    @GetMapping("/random")
    public List<DayHint> random(){
        return dayHintService.findRandomDayHints();
    }


}
