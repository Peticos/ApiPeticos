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
public class Weight {

    @Id
    private Integer idWeight;
    private Integer idPet;
    private double weight;
    private Date weightDate;
}
