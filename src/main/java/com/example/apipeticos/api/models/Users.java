package com.example.apipeticos.api.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "User_")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único do usuário gerado automaticamente", example = "1")
    Integer idUser;
    @NotNull
    @Size( max = 255)
    String fullName;
    @NotNull
    @Size( max = 255)
    String username;
    @NotNull
    @Size( max = 255)
    String email;
    String gender;
    @Size(min = 5, max = 14)
    String cnpj;
    @Column(name="plan_type")
    private String Plan;
    @Column(name="neighborhood")
    private String bairro;
    @Size( max = 12)
    private String phone;
    @Transient
    private String usernameId;
    private String profilePicture;

}
