package com.example.sistemaintegracao.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    private Long id;

    private String nome;
    private String email;
    private String telefone;
}
