package com.example.apipeticos.api.controllers;

import com.example.apipeticos.api.models.ApiResponseSQL;
import com.example.apipeticos.api.models.Weight;
import com.example.apipeticos.api.services.WeightService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weight")
public class WeightController {

    private final WeightService weightService;

    public WeightController(WeightService weightService){
        this.weightService=weightService;
    }

    @Operation(summary = "Insere um novo peso", description = "Insere um registro de peso para um usuário específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Peso inserido com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseSQL.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseSQL.class)))
    })
    @PostMapping("/insert")
    public ResponseEntity<ApiResponseSQL> insert(@RequestBody Weight weight){
        try{
            weightService.insert(weight);
            return ResponseEntity.ok( new ApiResponseSQL("Peso inserido com sucesso") );
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(new ApiResponseSQL("Erro ao Inserir"));
        }
    }
}
