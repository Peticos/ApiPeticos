package com.example.apipeticos.controllers;

import com.example.apipeticos.models.Users;
import com.example.apipeticos.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UsersService usersService;

    @Autowired //ele faz a injeção de dependência no programa. Ele não é necessário quando criamos o programa.
    public UserController(  UsersService usersService){
        this.usersService = usersService;
    }

    @GetMapping("/getall")
    public List<Users> getAll(){
        return usersService.findAll();
    }



}
