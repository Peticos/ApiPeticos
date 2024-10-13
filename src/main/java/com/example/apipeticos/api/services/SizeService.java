package com.example.apipeticos.api.services;

import com.example.apipeticos.api.repositories.SizeRepository;
import com.example.apipeticos.api.models.Size;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeService {

    private final SizeRepository sizeRepository;

    public SizeService(SizeRepository sizeRepository) {
        this.sizeRepository = sizeRepository;
    }

    public List<Size> findAll() {
        return sizeRepository.findAll();
    }
}
