package com.example.apipeticos.api.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "User_")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único do usuário gerado automaticamente", example = "1")
    Integer idUser;
    @NotNull
    @Size( max = 255)
    @Schema(description = "Nome completo do usuário", example = "João da Silva")
    String fullName;
    @NotNull
    @Size( max = 255)
    @Schema(description = "Nome de usuário único", example = "joaosilva")
    String username;
    @NotNull
    @Size( max = 255)
    @Schema(description = "Endereço de e-mail do usuário", example = "joao.silva@example.com")
    String email;
    @Schema(description = "Gênero do usuário", example = "Masculino")
    String gender;
    @Size(min = 5, max = 14)
    @Schema(description = "CNPJ do usuário, se aplicável", example = "Tutor")
    String cnpj;
    @Column(name="plan_type")
    @Schema(description = "Tipo de plano do usuário", example = "Sem Plano")
    private String Plan;
    @Column(name="neighborhood")
    @Schema(description = "Bairro onde o usuário reside", example = "Centro")
    private String bairro;
    @Size( max = 12)
    @Schema(description = "Número de telefone do usuário", example = "11987654321")
    private String phone;
    @Schema(description = "Username usado para atualizar", example = "null",nullable = true)
    @Transient
    private String usernameId;
    @Schema(description = "URL da imagem de perfil do usuário", example = "http://example.com/images/profile.jpg")
    private String profilePicture;

}
