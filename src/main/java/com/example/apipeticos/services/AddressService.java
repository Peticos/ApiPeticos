package com.example.apipeticos.services;

import com.example.apipeticos.models.Address;
import com.example.apipeticos.repositories.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository){
        this.addressRepository = addressRepository;
    }

    public List<Address> findAll(){
        return addressRepository.findAll();
    }
}
