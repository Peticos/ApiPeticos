package com.example.apipeticos.api.controllers;

import com.example.apipeticos.api.models.ApiResponseSQL;
import com.example.apipeticos.api.models.Users;
import com.example.apipeticos.api.services.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UsersService usersService;

    @Autowired
    public UserController(  UsersService usersService){
        this.usersService = usersService;
    }


    @Operation(summary = "Inserir tutor", description = "Insere um novo tutor no sistema.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário inserido com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação ou dados já existentes"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/inserttutor")
    public ResponseEntity<?> inserirUsuario(@Valid @RequestBody Users tutorRequest, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> erros = validateUser(result);
            return ResponseEntity.badRequest().body(erros); // Retorna erros de validação
        } else {
            try {
                // Insere o usuário no banco
                usersService.insertUser(tutorRequest);

                // Recupera o ID do usuário recém-inserido
                Integer idUsuario = usersService.findByUsername(tutorRequest.getUsername()).getIdUser();
                return ResponseEntity.ok(idUsuario); // Retorna o ID do usuário inserido
            } catch (DataIntegrityViolationException e) {
                // Trata erro de unicidade para email ou username
                String errorMessage;
                if (e.getMessage().contains("user__username_key")) {
                    errorMessage = "O username informado já está em uso.";
                } else if (e.getMessage().contains("user__email_key")) {
                    errorMessage = "O email informado já está em uso.";
                } else if (e.getMessage().contains("id_address")) {
                    errorMessage = "O bairro informado não foi encontrado no banco de dados.";
                } else {
                    errorMessage = "Erro de integridade de dados.";
                }
                return ResponseEntity.badRequest().body(errorMessage);
            } catch (Exception e) {
                return ResponseEntity.status(500).body("Erro interno do servidor: " + e.getMessage());
            }
        }
    }

    @Operation(summary = "Inserir profissional", description = "Insere um novo profissional no sistema.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Profissional inserido com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação ou dados já existentes"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/insertprofissional")
    public ResponseEntity<?> inserirProfissional(@Valid @RequestBody Users tutorRequest, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> erros = validateUser(result);
            return ResponseEntity.badRequest().body(erros); // Retorna erros de validação
        } else {
            try {
                usersService.insertUserProfissonal(tutorRequest);
                Integer idUsuario = usersService.findByUsername(tutorRequest.getUsername()).getIdUser();
                return ResponseEntity.ok(idUsuario); // Retorna o ID do usuário inserido
            } catch (DuplicateKeyException e){
               return ResponseEntity.badRequest().body(e.getMessage());
            }
            catch (IllegalArgumentException e){
                return ResponseEntity.unprocessableEntity().body("Dados inválidos fornecidos para inserir o profissional.");
            }
            catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Erro inesperado ao inserir o profissional. Por favor, tente novamente mais tarde.");            }
        }
    }

    @Operation(summary = "Deletar usuário", description = "Deleta um usuário pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponseSQL> deleteUser(@PathVariable Integer id){
        if (usersService.findbyId(id) != null) {
            Users deleted = usersService.deleteUser(id);
            return ResponseEntity.ok(new ApiResponseSQL("User deleted with sucess : "+deleted));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponseSQL("User not found"));
        }
    }

    @Operation(summary = "Atualizar usuário", description = "Atualiza informações de um usuário.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/updateuser")
    public ResponseEntity<ApiResponseSQL> updateUser(
                                             @Valid @RequestBody Users userUpdated, BindingResult result){
        if (result.hasErrors()){
            Map<String, String> erros = validateUser(result);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseSQL(erros.toString()));
        }else {
            usersService.updateUser(userUpdated);
            return ResponseEntity.ok(new ApiResponseSQL("Usuario atualizado com sucesso"));
        }
    }

    @Operation(summary = "Atualizar Profissional", description = "Atualiza informações de um profissional.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/updateprofissional")
    public ResponseEntity<ApiResponseSQL> updateProfissional(
            @Valid @RequestBody Users userUpdated, BindingResult result){
        if (result.hasErrors()){
            Map<String, String> erros = validateUser(result);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseSQL(erros.toString()));
        }else {
            usersService.updateUserProfissional(userUpdated);
            return ResponseEntity.ok(new ApiResponseSQL("Usuario atualizado com sucess"));
        }
    }

    @Operation(summary = "Buscar usuário por ID", description = "Busca um usuário pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
            @ApiResponse(responseCode = "500", description = "Usuário não encontrado")
    })
    @GetMapping("/findbyid/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id){

        Optional<Users> users =usersService.findbyId(id);
        if(users.isPresent()){
            return ResponseEntity.ok(users.get());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário com ID " + id + " não foi encontrado.");


    }

    @Operation(summary = "Buscar usuário por username", description = "Busca um usuário pelo username.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
            @ApiResponse(responseCode = "500", description = "Usuário não encontrado")
    })
    @GetMapping("/getbyusername/{username}")
    public ResponseEntity<?> findByUsername(@PathVariable String username){
        try {
            return  ResponseEntity.ok(usersService.findByUsername(username));

        }catch (HttpServerErrorException.InternalServerError internalServerError){
            return ResponseEntity.internalServerError().body(new ApiResponseSQL("Usuário não encontrado"));
        }
    }

    public Map<String, String> validateUser(BindingResult result){
        Map<String, String> erros = new HashMap<>();

        for (FieldError error : result.getFieldErrors()){
            erros.put(error.getField(), error.getDefaultMessage());
        }

        return erros;
    }
}
