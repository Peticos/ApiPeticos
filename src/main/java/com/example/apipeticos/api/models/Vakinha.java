package com.example.apipeticos.api.models;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "ID único da vacina gerado automaticamente",example = "3")
    Integer idVakinha;
    @Schema(description = "ID do pet que precisa da doação",example = "146")
    Integer idPet;
    @Schema(description = "ID do usuário que criou a vakinha",example = "281")
    Integer idUser;
    @Schema(description = "Titulo da Vakinha",example = " AJUDEM A MIMI A OPERAR ")
    String title;
    @Schema(description = "Link da vakinha, faz referencia a uma vaikinha existente no site https://www.vakinha.com.br",example = "https://www.vakinha.com.br/")
    String link;
    @Schema(description = "Total que já foi doado",example = "143.70")
    Double totalDonated;
    @Schema(description = "Total desejado", example = "500")
    Double goalAmount;
    @Schema(description = "Quantidade de pessoas que já apoiaram", example = "3")
    Integer supportersAmount;
    @Schema(description = "Porcentagem Total", example = "25")
    Double totalPercentage;
    @Schema(description = "Descrição da vakinha", example = "Ajude na cirurgia da Gatinha MIMI")
    String description;
    @Schema(description = "Imagem da vakinha", example = "http://link-imagem-gatinho")
    String image;
    @Schema(description = "Data que começou a vakinha", example = "2024-10-29")
    Date initialDate;
    @Schema(description = "Data que a vakinha foi finalizada", example = "2024-10-29")
    Date endDate;
}
