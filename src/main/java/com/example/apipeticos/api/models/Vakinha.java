package com.example.apipeticos.api.models;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY) // or GenerationType.AUTO
    Integer idVakinha;
    Integer idPet;
    Integer idUser;
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
