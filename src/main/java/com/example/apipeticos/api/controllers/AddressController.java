package com.example.apipeticos.api.controllers;


import com.example.apipeticos.api.services.AddressService;
import com.example.apipeticos.api.models.Address;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Busca todas as entradas de Address", description = "Retorna uma lista com todas as entradas do tipo Address.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de Size retornada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Address.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/getall")
    public List<Address> getAll(){
        return addressService.findAll();
    }


}
