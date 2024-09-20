package com.example.apipeticos.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    Long idUser;
    @NotNull
    Long idAddress;
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


    @Override
    public String toString() {
        return "Users { " +
                "idUser= " + idUser +
                ", idAddress= " + idAddress +
                ", fullName= '" + fullName + '\'' +
                ", username= '" + username + '\'' +
                ", email= '" + email + '\'' +
                ", password= '" + password + '\'' +
                ", gender= '" + gender + '\'' +
                ", idPlan= " + idPlan +
                ", cnpj= " + cnpj +
                " }";
    }
}
