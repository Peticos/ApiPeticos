package com.example.apipeticos.api.controllers;

import com.example.apipeticos.api.models.ApiResponseSQL;
import com.example.apipeticos.api.models.Vaccine;
import com.example.apipeticos.api.models.Vakinha;
import com.example.apipeticos.api.services.VakinhasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vakinha")
public class VakinhaController {

    private VakinhasService vakinhasService;

    public VakinhaController (VakinhasService vakinhasService){
        this.vakinhasService = vakinhasService;
    }

    @Operation(summary = "Busca todas as vakinhas", description = "Utilizado para feed")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vakinhas encontradas"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    })
    @GetMapping("/getall")
    public List<Vakinha> getAll(){
        return vakinhasService.findAll();
    }

    @Operation(summary = "Insere uma nova Vakinha por meio de um RPA")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inserido com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Vaccine.class),
                    examples = {
                            @ExampleObject(name = "Vakinha", summary = "Exemplo para inserir vakinha",
                                    value = "{\n" +
                                            "\"idUser\":281,  \n"+
                                            "\"link\":\"https://www.vakinha.com.br/5164271\" ,"+
                                            "\"idPet\":146  \n"+
                                            "}", description = "Exemplo para inserir vakinha. O Link da Vakinha deve ser valido. todos os outros campos são preenchidos a partir do RPA que utiliza o link"),

                    }
            )
    )
    @PostMapping("/insert")
    public ResponseEntity<ApiResponseSQL> insert(@RequestBody Vakinha vakinha){

        try {
            vakinhasService.insertVakinhaRPA(vakinha);
            return ResponseEntity.ok(new ApiResponseSQL("Inserido com sucesso"));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new ApiResponseSQL("Erro ao inserir"));
        }
    }


    @Operation(summary = "Busca as vakinhas pelo ID do usuario", description = "Utilizado para feed suas vakinhas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vakinhas encontradas"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    })
    @GetMapping("/getByUser/{id}")
    public ResponseEntity<?> getByUser(@Parameter(description = "ID do Usuario para o qual as Vakinhas serão buscadas", required = true, example = "281")@PathVariable Integer id){
        try {
            return ResponseEntity.ok(vakinhasService.findByIdUser(id));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("Erro na api");
        }
    }
}
