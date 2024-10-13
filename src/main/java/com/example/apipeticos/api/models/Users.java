package com.example.apipeticos.api.models;

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
    Integer idUser;

    Integer idAddress;

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

    int idPlan;

    @Size(min = 5, max = 14)
    String cnpj;

    @Transient
    private String Plan;

    @Transient
    private String bairro;

    @Transient
    @Size( max = 12)
    private String phone;




    @Override
    public String toString() {
        return "Users { " +
                "idUser= " + idUser +
                ", idAddress= " + idAddress +
                ", fullName= '" + fullName + '\'' +
                ", username= '" + username + '\'' +
                ", email= '" + email + '\'' +
                ", gender= '" + gender + '\'' +
                ", idPlan= " + idPlan +
                ", cnpj= " + cnpj +
                " }";
    }
}
