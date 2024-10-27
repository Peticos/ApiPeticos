package com.example.apipeticos.api.repositories;


import com.example.apipeticos.api.models.PetRegister;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PetRegisterRepository extends JpaRepository<PetRegister, Integer> {

    PetRegister findByIdPet(Integer idPet);

    @Procedure(procedureName = "delete_pet")
    void deletePet(@Param("pet_id") Integer idPet);

    @Query(value = "SELECT * FROM select_pet(:username)", nativeQuery = true)
    List<PetRegister> findPetsByUserUsername(@Param("username") String username);

    @Query(value = "SELECT * FROM select_pet(:id)", nativeQuery = true)
    Optional<PetRegister> findById(@Param("id") Integer id);


    @Query("SELECT pr.nickname FROM PetRegister pr WHERE pr.idPet IN :ids")
    List<String> findNicknamesByIds(List<Integer> ids);

    @Query(value = "SELECT insert_pet(:username, :nickname, :age, :sex, :specie, :race, :size, :color)", nativeQuery = true)
    Integer insertPet(
            @Param("username") String username,
            @Param("nickname") String nickname,
            @Param("age") Integer age,
            @Param("sex") Character sex,
            @Param("specie") String specie,
            @Param("race") String race,
            @Param("size") String size,
            @Param("color") String color
    );

    @Modifying
    @Transactional
    @Procedure("update_pet")
    void updatePet(
            @Param("idPet") Integer idPet,
            @Param("nickname") String nickname,
            @Param("age") Integer age,
            @Param("sex") Character sex,
            @Param("specie") String specie,
            @Param("race") String race,
            @Param("size") String size,
            @Param("color") String color
    );

}
