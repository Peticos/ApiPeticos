package com.example.apipeticos.api.repositories;

import com.example.apipeticos.api.models.Locations;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationsRepository extends JpaRepository<Locations, Integer> {


    List<Locations> findByIdLocalType(Integer idLocalType);


}
