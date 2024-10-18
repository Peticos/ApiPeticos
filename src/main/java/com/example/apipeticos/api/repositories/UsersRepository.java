package com.example.apipeticos.api.repositories;

import com.example.apipeticos.api.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UsersRepository extends JpaRepository<Users, Integer> {


    @Procedure(name = "delete_user")
    void deleteByUsername(String username_param);
    @Query(value = "SELECT * FROM select_user(CAST(:username_param AS varchar))", nativeQuery = true)
    Users findByUsername(@Param("username_param") String username);

    @Procedure(procedureName = "insert_user_tutor")
    void insertUserTutor(
            @Param("fullName") String fullName,
            @Param("username") String username,
            @Param("email") String email,
            @Param("bairro") String bairro,
            @Param("plan") String plan,
            @Param("phone") String phone,
            @Param("gender") String gender,
            @Param("profilePicture") String profilPicture
    );

    @Procedure(procedureName = "insert_user_profissional")
    void insertUserProfissional(
            @Param("fullName") String fullName,
            @Param("username") String username,
            @Param("email") String email,
            @Param("bairro") String bairro,
            @Param("plan") String plan,
            @Param("phone") String phone,
            @Param("cnpj") String cnpj,
            @Param("profilePicture") String profilPicture

    );

    @Procedure(procedureName = "update_user_tutor")
    void updateUserTutor(
            @Param("fullname") String fullName,
            @Param("username_id") String usernameId,
            @Param("username") String username,
            @Param("email") String email,
            @Param("bairro") String bairro,
            @Param("phone") String phone,
            @Param("gender") String gender,
            @Param("profilePicture") String profilPicture

    );

    @Procedure(procedureName = "update_user_profissional")
    void updateUserProfissional(
            @Param("fullname") String fullName,
            @Param("username_id") String usernameId,
            @Param("username") String username,
            @Param("email") String email,
            @Param("bairro") String bairro,
            @Param("phone") String phone,
            @Param("cnpj") String cnpj,
            @Param("profilePicture") String profilPicture

    );

}