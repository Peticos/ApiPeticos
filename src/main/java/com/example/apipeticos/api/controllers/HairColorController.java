package com.example.apipeticos.api.controllers;

import com.example.apipeticos.api.models.HairColor;
import com.example.apipeticos.api.services.HairColorService;
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
@RequestMapping("/api/haircolor")
public class HairColorController {

    private final HairColorService hairColorService;

    public HairColorController(HairColorService hairColorService) {
        this.hairColorService = hairColorService;
    }


    @Operation(summary = "Busca todas as entradas de HairColor", description = "Retorna uma lista com todas as entradas do tipo HairColor.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de Size retornada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = HairColor.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/getall")
    public List<HairColor> getAll() {
        return hairColorService.findAll();
    }
}
