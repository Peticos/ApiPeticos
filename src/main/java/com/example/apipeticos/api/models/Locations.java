package com.example.apipeticos.api.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
public class Locations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLocal;
    private Integer idLocalType;
    private String localName;
    private String localPicture;
    private String street;
    private String city;
    private String neighborhood;
    private Integer streetNum;
    private String description;
    private String linkKnowMore;
    private String phone;

}
