package com.example.apipeticos.api.repositories;

import com.example.apipeticos.api.models.Locations;
import com.example.apipeticos.api.models.RescuedLost;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RescuedLostRepository extends JpaRepository<RescuedLost, Integer> {

    @Transactional
    @Modifying
    @Query(value = "CALL insert_rescued_lost(:idPet, :idUserParam, :bairro, :title, :description, :postTime, :picture, :location, :lostDate)", nativeQuery = true)
    void insertRescuedLost(
            @Param("idPet") int idPet,
            @Param("idUserParam") int idUserParam,
            @Param("bairro") String bairro,
            @Param("title") String title,
            @Param("description") String description,
            @Param("postTime") LocalDateTime postTime,
            @Param("picture") String picture,
            @Param("location") String location,
            @Param("lostDate") LocalDate lostDate
    );

    @Query(value = "SELECT * FROM select_rescued_lost()", nativeQuery = true)
    List<RescuedLost> findAll();

    @Query(value = "SELECT * FROM select_rescued_lost(:id)", nativeQuery = true)
    List<RescuedLost> findByIdUser(Integer id);
}
