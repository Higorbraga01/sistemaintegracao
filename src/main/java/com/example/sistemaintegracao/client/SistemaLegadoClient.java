package com.example.sistemaintegracao.client;

import com.example.sistemaintegracao.dto.UsuarioLegadoDTO;
import com.example.sistemaintegracao.model.Usuario;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class SistemaLegadoClient {

    private final RestTemplate restTemplate;

    public SistemaLegadoClient(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }


    public List<Usuario> buscarUsuarios() {
        String url = "https://jsonplaceholder.typicode.com/users";
        ResponseEntity<UsuarioLegadoDTO[]> response = restTemplate.getForEntity(url, UsuarioLegadoDTO[].class);
        return Arrays.stream(response.getBody())
                .map(dto -> new Usuario(dto.getId(), dto.getNome(), dto.getEmail(), dto.getTelefone()))
                .toList();
    }

}
