package com.example.apipeticos.api.controllers;

import com.example.apipeticos.api.models.ApiResponse;
import com.example.apipeticos.api.models.Users;
import com.example.apipeticos.api.services.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UsersService usersService;

    @Autowired
    public UserController(  UsersService usersService){
        this.usersService = usersService;
    }

    @GetMapping("/getall")
    public List<Users> getAll(){
        return usersService.findAll();
    }

    @PostMapping("/inserttutor")
    public Integer inserirUsuario(@Valid @RequestBody Users tutorRequest, BindingResult result) {

        if (result.hasErrors()){
            Map<String, String> erros = validateUser(result);
            return -1;
        }else {
            usersService.insertUser(tutorRequest);
            return usersService.findByUsername(tutorRequest.getUsername()).getIdUser();
        }

    }

    @PostMapping("/insertprofissional")
    public Integer inserirProfissional(@Valid @RequestBody Users tutorRequest, BindingResult result) {
        if (result.hasErrors()){
            Map<String, String> erros = validateUser(result);
            return -1;
        }else {
            usersService.insertUserProfissonal(tutorRequest);
            return usersService.findByUsername(tutorRequest.getUsername()).getIdUser();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer id){
        if (usersService.findbyId(id) != null) {
            Users deleted = usersService.deleteUser(id);
            return ResponseEntity.ok(new ApiResponse("User deleted with sucess : "+deleted));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("User not found"));
        }
    }

    @PutMapping("/updateuser")
    public ResponseEntity<ApiResponse> updateUser(
                                             @Valid @RequestBody Users userUpdated, BindingResult result){
        if (result.hasErrors()){
            Map<String, String> erros = validateUser(result);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(erros.toString()));
        }else {
            usersService.updateUser(userUpdated);
            return ResponseEntity.ok(new ApiResponse("Usuario atualizado com sucesso"));
        }
    }

    @PutMapping("/updateprofissional")
    public ResponseEntity<ApiResponse> updateProfissional(
            @Valid @RequestBody Users userUpdated, BindingResult result){
        if (result.hasErrors()){
            Map<String, String> erros = validateUser(result);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(erros.toString()));
        }else {
            usersService.updateUserProfissional(userUpdated);
            return ResponseEntity.ok(new ApiResponse("Usuario atualizado com sucess"));
        }
    }


    @GetMapping("/findbyid/{id}")
    public Users findById(@PathVariable Integer id){
        return usersService.findById(id);
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
