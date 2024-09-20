package com.example.apipeticos.controllers;


import com.example.apipeticos.models.Address;
import com.example.apipeticos.models.Users;
import com.example.apipeticos.services.AddressService;
import com.example.apipeticos.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    private final AddressService addressService;

    @Autowired //ele faz a injeção de dependência no programa. Ele não é necessário quando criamos o programa.
    public AddressController(  AddressService addressService){
        this.addressService = addressService;
    }

    @GetMapping("/getall")
    public List<Address> getAll(){
        return addressService.findAll();
    }


}
