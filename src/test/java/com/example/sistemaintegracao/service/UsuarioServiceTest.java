package com.example.sistemaintegracao.service;

import com.example.sistemaintegracao.client.SistemaLegadoClient;
import com.example.sistemaintegracao.model.Usuario;
import com.example.sistemaintegracao.repository.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class UsuarioServiceTest {

    @MockitoBean
    private UsuarioRepository repository;

    @MockitoBean
    private SistemaLegadoClient client;

    @Autowired
    private UsuarioService service;


    @Test
    void deveSincronizarUsuarios_AtualizandoOuCriando() {
        Usuario usuarioApi = new Usuario(1L, "Higor Atualizado", "higor@email.com", "12345");
        Usuario usuarioExistente = new Usuario(1L, null, null, null);

        // Simula retorno da API externa
        Mockito.when(client.buscarUsuarios())
                .thenReturn(List.of(usuarioApi));

        // Simula que o usuário já existe no banco
        Mockito.when(repository.findById(1L))
                .thenReturn(Optional.of(usuarioExistente));

        // Simula persistência
        Mockito.when(repository.saveAll(Mockito.anyList()))
                .thenReturn(List.of(usuarioApi));

        List<Usuario> result = service.sincronizarUsuarios();

        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("Higor Atualizado", result.get(0).getNome());
        Assertions.assertEquals("12345", result.get(0).getTelefone());
    }
}