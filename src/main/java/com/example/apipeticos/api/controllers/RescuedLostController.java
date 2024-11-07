package com.example.apipeticos.api.controllers;


import com.example.apipeticos.api.models.ApiResponseSQL;
import com.example.apipeticos.api.models.RescuedLost;
import com.example.apipeticos.api.models.Size;
import com.example.apipeticos.api.services.RescuedLostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/rescuedlost")
public class RescuedLostController {

    @Autowired
    private RescuedLostService rescuedLostService;

    @Operation(summary = "Busca todos os registros de Rescued_lost", description = "Retorna uma lista com todos os Pets perdidos (tem verificação de data de achado).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de pets perdidos retornada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RescuedLost.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/getall")
    public ResponseEntity<?> getAll(){
        try {
            return ResponseEntity.ok(rescuedLostService.findAll());
        }catch (HttpServerErrorException.InternalServerError error){
            return ResponseEntity.internalServerError().body("Erro interno do servidor");
        }

    }


    @Operation(summary = "Insere um novo resgate/perda", description = "Insere um novo registro de resgate/perda de animal.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Perdido inserido com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseSQL.class))),
            @ApiResponse(responseCode = "400", description = "Entidade Inválida",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseSQL.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseSQL.class)))
    })
    @PostMapping("/insert")
    public ResponseEntity<ApiResponseSQL> insert(@RequestBody RescuedLost rescuedLost, BindingResult result){
        if (result.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseSQL("Entidade Invalida") );
        }else {
            rescuedLostService.insertRescuedLost(rescuedLost);
            return ResponseEntity.ok( new ApiResponseSQL("Perdido  inserido com sucesso"));
        }
    }

    @Operation(summary = "Buscar pet pelo ID do dono", description = "Busca pets perdido por usuario.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pets encontrados"),
    })
    @GetMapping("/findbyid/{id}")
    public  List<RescuedLost> findById(@PathVariable Integer id){
        return rescuedLostService.findById(id);
    }

    @Operation(summary = "Atualiza a data de resgate de um animal",
            description = "Atualiza a data de resgate do animal identificado pelo ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pet Achado!",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseSQL.class))),
            @ApiResponse(responseCode = "404", description = "Pet não encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseSQL.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseSQL.class)))
    })
    @PutMapping("/findpet/{id}")
    public ResponseEntity<ApiResponseSQL> updateRescuedDate(@PathVariable Integer id, @RequestParam("rescuedDate") Date rescuedDate) {
        rescuedLostService.findPet(id, rescuedDate);
        return ResponseEntity.ok(new ApiResponseSQL("Pet Achado!"));
    }
}
