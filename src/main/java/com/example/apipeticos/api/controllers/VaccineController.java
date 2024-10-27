package com.example.apipeticos.api.controllers;

import com.example.apipeticos.api.models.Vaccine;
import com.example.apipeticos.api.models.Vakinha;
import com.example.apipeticos.api.services.VaccineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.hibernate.sql.Insert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vaccine")
public class VaccineController {

    private VaccineService vaccineService;

    public VaccineController(VaccineService vaccineService){
        this.vaccineService=vaccineService;
    }

    @Operation(summary = "Busca todas as vacinas", description = "Utilizado para testes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vacinas do Pet encontradas"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    })
    @GetMapping("/getall")
    public List<Vaccine> getAll(){
        return vaccineService.findAll();
    }

    @Operation(summary = "Busca vacinas por ID do Pet")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vacinas do Pet encontradas"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    })
    @GetMapping("/findbyid/{id}")
    public List<Vaccine> findByIdPet( @Parameter(description = "ID do Pet para o qual as vacinas serão buscadas", required = true, example = "146") @PathVariable Integer id){
        return vaccineService.findByIdPet(id);
    }

    @Operation(summary = "Insere uma nova vacina")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Vacina inserida com sucesso. Retorna o Id da Vacina que foi inserida."),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Vaccine.class),
                    examples = {
                            @ExampleObject(name = "Vacina", summary = "Exemplo para inserir vacina",
                                    value = "{\n" +
                                            "  \"idPet\": 145, \n" +
                                            "  \"name\": \"Rabies\",  \n" +
                                            "  \"numDoses\": 2,       \n" +
                                            "\"dosesTaked\": 0       \n"+
                                            "}", description = "Exemplo para inserir vacina. Não é necessário informar o idVacinas, pois a API gera esse número automaticamente. Lembre-se de usar um idPet válido!"),

                    }
            )
    )
    @PostMapping("/insert")
    public Integer insert( @RequestBody Vaccine vaccine){
        return vaccineService.insertVaccine(vaccine);
    }
}
