package com.example.apipeticos.repositories;

import com.example.apipeticos.models.Locations;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationsRepository extends JpaRepository<Locations, Integer> {


    List<Locations> findByIdLocalType(Integer idLocalType);


}
