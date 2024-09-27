package com.example.apipeticos.controllers;


import com.example.apipeticos.models.Address;
import com.example.apipeticos.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(  AddressService addressService){
        this.addressService = addressService;
    }

    @GetMapping("/getall")
    public List<Address> getAll(){
        return addressService.findAll();
    }


}
