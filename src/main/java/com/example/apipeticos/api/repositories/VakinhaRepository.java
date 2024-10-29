package com.example.apipeticos.api.repositories;

import com.example.apipeticos.api.models.Vakinha;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VakinhaRepository extends JpaRepository<Vakinha, Integer> {

    List<Vakinha> findByIdUser(Integer id);
}
