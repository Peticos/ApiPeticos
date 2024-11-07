package com.example.apipeticos.api.controllers;

import com.example.apipeticos.api.models.ApiResponseSQL;
import com.example.apipeticos.api.models.Doses;
import com.example.apipeticos.api.services.DosesService;
import com.example.apipeticos.api.services.VaccineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doses")
public class DosesController {

    private final DosesService dosesService;
    private final VaccineService vaccineService;

    public DosesController(DosesService dosesService, VaccineService vaccineService){
        this.dosesService=dosesService;
        this.vaccineService=vaccineService;
    }

    @Operation(summary = "Busca doses associadas a uma vacina", description = "Este endpoint retorna todas as doses associadas à vacina com o ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de doses recuperada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "404", description = "Vacina não encontrada para o ID fornecido",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno ao recuperar as doses",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    })
    @GetMapping("/getbyvaccine/{id}")
    public List<Doses> getByVaccine(@PathVariable Integer id){
        return dosesService.getByIdVaccine(id);
    }

    @Operation(summary = "Insere uma nova dose", description = "Este endpoint insere uma nova dose no banco de dados e atualiza o número de doses da vacina.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Doses inseridas com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseSQL.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno ao inserir a dose",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseSQL.class)))
    })
    @PostMapping("/insert")
    public ResponseEntity<ApiResponseSQL> insert(@RequestBody Doses doses){
        try {
            dosesService.insertDoses(doses);
            vaccineService.updateDose(doses.getIdVaccine());
            return ResponseEntity.ok(new ApiResponseSQL("Doses inserido com sucesso!"));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(new ApiResponseSQL(e.getMessage()));
        }
    }

}
