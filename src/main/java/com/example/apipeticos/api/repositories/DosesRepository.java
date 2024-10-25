package com.example.apipeticos.api.repositories;

import com.example.apipeticos.api.models.Doses;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DosesRepository extends JpaRepository<Doses,Integer> {

    List<Doses> findByIdVaccine(Integer id);
}
