package com.example.apipeticos.api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DayHint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idHint;
    @NotNull
    String title;
    @NotNull
    String hint_text;
    @NotNull
    String link;


}
