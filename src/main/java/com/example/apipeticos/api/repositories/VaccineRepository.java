package com.example.apipeticos.api.repositories;

import com.example.apipeticos.api.models.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VaccineRepository extends JpaRepository<Vaccine, Integer> {


    List<Vaccine> getVaccineByIdPet(Integer id);

}
