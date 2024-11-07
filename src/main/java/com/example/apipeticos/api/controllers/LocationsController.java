package com.example.apipeticos.api.controllers;

import com.example.apipeticos.api.models.Locations;
import com.example.apipeticos.api.services.LocationsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class LocationsController {

    private final LocationsService locationsService;

    LocationsController (LocationsService locationsService) {
        this.locationsService = locationsService;
    }

    @Operation(summary = "Busca todas as localizações", description = "Retorna uma lista de todas as localizações cadastradas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de localizações retornada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Locations.class))),
            @ApiResponse(responseCode = "500", description = "Erro ao recuperar as localizações")
    })
    @GetMapping("/getall")
    public List<Locations> getAll() {
        return locationsService.getAll();
    }

    @Operation(summary = "Busca localizações por tipo", description = "Retorna uma lista de localizações com base no tipo especificado pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de localizações por tipo retornada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Locations.class))),
            @ApiResponse(responseCode = "404", description = "Nenhuma localização encontrada para o tipo fornecido",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    })
    @GetMapping("/getbytype/{id}")
    public List<Locations> getByType(@PathVariable Integer id) {
        return locationsService.findByType(id);
    }
}
