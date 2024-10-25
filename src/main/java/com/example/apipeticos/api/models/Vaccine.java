package com.example.apipeticos.api.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Schema(description = "Vacina")
public class Vaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // or GenerationType.AUTO
    @Schema(description = "Id da vacina",example = "3")
    private Integer idVaccine;
    @Schema(description = "Id do pet que tem que tomar a vacina", example = "140")
    private Integer idPet;
    @Schema(description = "Nome da vacina", example = "Raiva")
    private String name;
    @Schema(description = "Numeros de Dose que a Vacina Tem", example = "2")
    private Integer numDoses;
}
