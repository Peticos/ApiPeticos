package com.example.apipeticos.api.controllers;

import com.example.apipeticos.api.models.ApiResponseSQL;
import com.example.apipeticos.api.models.PetRegister;
import com.example.apipeticos.api.services.PetRegisterService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/petregister")
public class PetRegisterController {

    private final PetRegisterService petRegisterService;

    public PetRegisterController(PetRegisterService petRegisterService) {
        this.petRegisterService = petRegisterService;
    }

    @Operation(summary = "Buscar pet por ID", description = "Busca um pet pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pet encontrado"),
    })
    @GetMapping("/findbyid/{id}")
    public PetRegister findById(@PathVariable Integer id){
        return petRegisterService.findById(id);
    }


    @PostMapping("/insert")
    @Operation(summary = "Insere um novo registro de pet", description = "Insere um novo pet no sistema e retorna o ID do pet inserido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pet inserido com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Integer.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno ao inserir o pet",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<?> inserirUsuario(
            @RequestBody PetRegister petRegister) {

        try {
            Integer petId = petRegisterService.insertPet(petRegister);
            return ResponseEntity.ok(petId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao inserir o pet");
        }
    }

    @Operation(summary = "Buscar pet por username do dono", description = "Busca um pet pelo username do dono.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pet encontrado"),
    })
    @GetMapping("/getbyusername/{username}")
    public List<PetRegister> findByUserUsername(@PathVariable String username){
        return petRegisterService.findByUserUsername(username);
    }

    @Operation(summary = "Deleta um pet pelo ID", description = "Deleta o registro de um pet com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pet deletado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseSQL.class))),
            @ApiResponse(responseCode = "404", description = "Pet não encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseSQL.class)))
    })
    @DeleteMapping("/deletebyid/{id}")
    public ResponseEntity<ApiResponseSQL> deleteById(@PathVariable Integer id){

        if (petRegisterService.findById(id) != null) {
            petRegisterService.deletePet(id);
            return ResponseEntity.ok(new ApiResponseSQL("Pet deleted with sucess "));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponseSQL("Pet not found"));
        }
    }

    @Operation(summary = "Busca apelidos de pets por IDs", description = "Recebe uma lista de IDs de pets e retorna uma lista de apelidos correspondentes.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Apelidos encontrados com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida, IDs não fornecidos corretamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    })
    @GetMapping("/nicknames")
    public List<String> getNicknamesByPetIds(@RequestParam List<Integer> ids) {
        return petRegisterService.findNicknamesById(ids);
    }

    @Operation(summary = "Atualiza as informações de um pet", description = "Atualiza o registro de um pet com base nas informações fornecidas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pet atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseSQL.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar pet, dados inválidos",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseSQL.class)))
    })
    @PostMapping("/update")
    public ResponseEntity<ApiResponseSQL> updatePet(@Valid @RequestBody PetRegister petRegister, BindingResult result){
        if (result.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseSQL("Erro ao atualizar"));
        }else {
            petRegisterService.updatePet(petRegister);
            return ResponseEntity.ok(new ApiResponseSQL("Atualizado com sucesso"));
        }
    }



}
