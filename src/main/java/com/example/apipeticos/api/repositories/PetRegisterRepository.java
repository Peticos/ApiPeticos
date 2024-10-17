package com.example.apipeticos.api.repositories;


import com.example.apipeticos.api.models.PetRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PetRegisterRepository extends JpaRepository<PetRegister, Integer> {

    PetRegister findByIdPet(Integer idPet);

    @Procedure(procedureName = "delete_pet")
    void deletePet(@Param("pet_id") Integer idPet);

    @Query(value = "SELECT pr.* FROM pet_register pr JOIN user_ u ON pr.id_user = u.id_user WHERE u.username = :username", nativeQuery = true)
    List<PetRegister> findPetsByUserUsername(@Param("username") String username);

    @Query("SELECT pr.nickname FROM PetRegister pr WHERE pr.idPet IN :ids")
    List<String> findNicknamesByIds(List<Integer> ids);

    @Procedure(procedureName = "insert_pet")
    void insertPet(
            @Param("username") String user,
            @Param("nickname") String nickname,
            @Param("age") int age,
            @Param("sex") String sex,
            @Param("specie") String specie,
            @Param("race") String race,
            @Param("size") String size,
            @Param("color") String color
    );

    @Procedure(procedureName = "update_pet")
    void updatePet(
            @Param("id_pet") int idPet,
            @Param("nickname_param") String nickname,
            @Param("age_param") int age,
            @Param("sex_param") char sex,
            @Param("specie") String specie,
            @Param("race") String race,
            @Param("size") String size,
            @Param("color") String color
    );

}
