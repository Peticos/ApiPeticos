package com.example.apipeticos.api.controllers;

import com.example.apipeticos.api.models.ApiResponseSQL;
import com.example.apipeticos.api.models.Users;
import com.example.apipeticos.api.services.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UsersService usersService;

    @Autowired
    public UserController(  UsersService usersService){
        this.usersService = usersService;
    }


    @PostMapping("/inserttutor")
    public ResponseEntity<?> inserirUsuario(@Valid @RequestBody Users tutorRequest, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> erros = validateUser(result);
            return ResponseEntity.badRequest().body(erros); // Retorna erros de validação
        } else {
            try {
                usersService.insertUser(tutorRequest);
                Integer idUsuario = usersService.findByUsername(tutorRequest.getUsername()).getIdUser();
                return ResponseEntity.ok(idUsuario); // Retorna o ID do usuário inserido
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.toString()); // Retorna mensagem genérica
            }
        }

    }

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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponseSQL> deleteUser(@PathVariable Integer id){
        if (usersService.findbyId(id) != null) {
            Users deleted = usersService.deleteUser(id);
            return ResponseEntity.ok(new ApiResponseSQL("User deleted with sucess : "+deleted));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponseSQL("User not found"));
        }
    }

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


    @GetMapping("/findbyid/{id}")
    public Users findById(@PathVariable Integer id){
        return usersService.findbyId(id);
    }

    @GetMapping("/getbyusername/{username}")
    public Users findByUsername(@PathVariable String username){
        return usersService.findByUsername(username);
    }


    public Map<String, String> validateUser(BindingResult result){
        Map<String, String> erros = new HashMap<>();

        for (FieldError error : result.getFieldErrors()){
            erros.put(error.getField(), error.getDefaultMessage());
        }

        return erros;
    }
}
