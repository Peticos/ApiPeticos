package com.example.apipeticos.api.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PetRegister {
    @Id
    Integer idPet;
    int idUser;
    String nickname;
    Integer age;
    Character sex;
    String specie;
    String race;
    String size;
    String color;

    @Transient
    String user;

}
