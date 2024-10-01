package com.example.apipeticos.repositories;


import com.example.apipeticos.models.PetRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface PetRegisterRepository extends JpaRepository<PetRegister, Long> {


    @Procedure(procedureName = "insert_pet")
    void insertPet(
            @Param("id_user") int idUser,
            @Param("nickname") String nickname,
            @Param("age") int age,
            @Param("sex") String sex,
            @Param("specie") String specie,
            @Param("race") String race,
            @Param("size") String size,
            @Param("color") String color,
            @Param("description") String description
    );

}
