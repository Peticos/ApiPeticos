package com.example.apipeticos.api.services;

import com.example.apipeticos.api.models.Admin;
import com.example.apipeticos.api.repositories.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    private AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository){
        this.adminRepository=adminRepository;
    }

    public Admin findById(Integer id){
        return adminRepository.findById(id).orElseThrow();
    }

    public Admin findByEmail(String email){
        return adminRepository.findByEmail(email).orElseThrow();
    }

    public boolean authenticate(String email, String password) {
        Optional<Admin> adminOptional = adminRepository.findByEmail(email);

        if (adminOptional.isPresent()) {
            Admin admin = adminOptional.get();
            // Comparação direta das senhas em texto simples
            return admin.getPassword().equals(password);
        }
        return false;
    }
}
