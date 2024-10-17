package com.example.apipeticos.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "rescued_lost")
public class RescuedLost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_rescued_lost;
    private Integer idPet;
    private Integer idUser;
    private Integer idAddress;
    @Transient
    private String bairro;
    private String title;
    private String description;
    private LocalDateTime postTime;
    private String picture;
    private String street;
    private Integer streetNum;
    private LocalDate lostDate;

}

