package com.example.sistemaintegracao.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UsuarioLegadoDTO {

    private Long id;

    @JsonProperty("name")
    private String nome;

    private String email;

    @JsonProperty("phone")
    private String telefone;
}
