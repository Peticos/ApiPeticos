package com.example.apipeticos.api.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
public class Vakinha {

    @Id
    Integer idVakinha;
    Integer idPet;
    String title;
    String link;
    Double totalDonated;
    Double goalAmount;
    Integer supportersAmount;
    Double totalPercentage;
    String description;
    String image;
    Date initialDate;
    Date endDate;
}
