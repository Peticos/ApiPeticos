package com.example.apipeticos.controllers;

import com.example.apipeticos.models.Users;
import com.example.apipeticos.services.UsersService;
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


    @PostMapping("/insert")
    public ResponseEntity<String> insertUser(@Valid @RequestBody Users users, BindingResult resultado){
        if (resultado.hasErrors()){
            Map<String, String> erros = validateUser(resultado);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros.toString());
        }else {
            usersService.createUser(users);
            return ResponseEntity.ok("User inserted with success");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        if (usersService.findbyId(id) != null) {
            Users deleted = usersService.deleteUser(id);
            return ResponseEntity.ok("User deleted with sucess : "+deleted);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
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
