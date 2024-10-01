package com.example.apipeticos.services;

import com.example.apipeticos.models.Size;
import com.example.apipeticos.repositories.SizeRepository;
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
