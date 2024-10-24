package com.example.apipeticos.api.services;

import com.example.apipeticos.api.models.Vakinha;
import com.example.apipeticos.api.repositories.VakinhaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VakinhasService {

    private final VakinhaRepository vakinhaRepository;

    public VakinhasService(VakinhaRepository vakinhaRepository){
        this.vakinhaRepository = vakinhaRepository;
    }

    public List<Vakinha> findAll(){
        return vakinhaRepository.findAll();
    }
}
