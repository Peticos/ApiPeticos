package com.example.apipeticos.repositories;

import com.example.apipeticos.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;


public interface UsersRepository extends JpaRepository<Users, Long> {

    Users findByUsername(String username);

    @Procedure(procedureName = "insert_user_tutor")
    void insertUserTutor(
            @Param("fullName") String fullName,
            @Param("username") String username,
            @Param("email") String email,
            @Param("bairro") String bairro,
            @Param("plan") String plan,
            @Param("phone") String phone,
            @Param("gender") String gender
    );

    @Procedure(procedureName = "insert_user_profissional")
    void insertUserProfissional(
            @Param("fullName") String fullName,
            @Param("username") String username,
            @Param("email") String email,
            @Param("bairro") String bairro,
            @Param("plan") String plan,
            @Param("phone") String phone,
            @Param("cnpj") String cnpj
    );


}