package com.example.apipeticos.api.repositories;

import com.example.apipeticos.api.models.RescuedLost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public interface RescuedLostRepository extends JpaRepository<RescuedLost, Integer> {

    @Procedure("insert_rescued_lost")
    void insertRescuedLost(
            Integer idPet,
            Integer idUser,
            String bairro,
            String title,
            String description,
            LocalDateTime postTime,
            String picture,
            String street,
            Integer streetNum,
            LocalDate lostDate
    );
}
