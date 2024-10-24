package com.example.apipeticos.api.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Vaccine {

    @Id
    private Integer idVaccine;
    private Integer idPet;
    private String name;
    private Date date_vaccine;
    private Integer dose;
}
