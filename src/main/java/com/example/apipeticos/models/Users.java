package com.example.apipeticos.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "User_")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idUser;
    @NotNull
    int idAddress;
    @NotNull
    String fullName;
    @NotNull
    String username;
    @NotNull
    String email;
    @NotNull
    String password;
    @NotNull
    String gender;
    @NotNull
    int idPlan;
    Long cnpj;

}
