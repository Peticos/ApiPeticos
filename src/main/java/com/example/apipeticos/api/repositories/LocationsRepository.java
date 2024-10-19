package com.example.apipeticos.api.repositories;

import com.example.apipeticos.api.models.Locations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LocationsRepository extends JpaRepository<Locations, Integer> {


    @Query(value = "SELECT * FROM select_locations(:idLocalType)", nativeQuery = true)
    List<Locations> findByIdLocalType(@Param("idLocalType") Integer idLocalType);

    @Query(value = "SELECT * FROM select_locations()", nativeQuery = true)
    List<Locations> findAll();
}
