package com.example.apipeticos.api.repositories;

import com.example.apipeticos.api.models.DayHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface DayHintRepository extends JpaRepository<DayHint, Integer> {

    @Query("SELECT MIN(dh.idHint) FROM DayHint dh")
    Integer findMinId();

    @Query("SELECT MAX(dh.idHint) FROM DayHint dh")
    Integer findMaxId();

    @Query("SELECT dh FROM DayHint dh WHERE dh.idHint IN (:random)")
    List<DayHint> findRandom(@Param("random") List<Integer> random);
}
