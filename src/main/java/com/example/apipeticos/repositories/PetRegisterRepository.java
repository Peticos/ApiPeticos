package com.example.apipeticos.repositories;


import com.example.apipeticos.models.PetRegister;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRegisterRepository extends JpaRepository<PetRegister, Long> {
}
