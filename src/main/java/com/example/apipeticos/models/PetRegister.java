package com.example.apipeticos.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    Long id_pet;
    Long id_user;
    String nickname;
    int age;
    String sex;
    String description;
    Long id_specie;
    Long id_race;
    Long id_size;
    Long id_color;


}
