package com.example.sistemaintegracao.service;

import com.example.sistemaintegracao.client.SistemaLegadoClient;
import com.example.sistemaintegracao.model.Usuario;
import com.example.sistemaintegracao.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final SistemaLegadoClient sistemaLegadoClient;

    public UsuarioService(UsuarioRepository repository, SistemaLegadoClient sistemaLegadoClient) {
        this.repository = repository;
        this.sistemaLegadoClient = sistemaLegadoClient;
    }

    public List<Usuario> sincronizarUsuarios() {
        List<Usuario> usuariosExternos = sistemaLegadoClient.buscarUsuarios();

        List<Usuario> usuariosParaPersistir = new ArrayList<>();

        for (Usuario externo : usuariosExternos) {
            Optional<Usuario> existente = repository.findById(externo.getId());

            if (existente.isPresent()) {
                Usuario usuario = existente.get();
                usuario.setNome(externo.getNome());
                usuario.setEmail(externo.getEmail());
                usuario.setTelefone(externo.getTelefone());
                usuariosParaPersistir.add(usuario);
            } else {
                usuariosParaPersistir.add(externo);
            }
        }

        return repository.saveAll(usuariosParaPersistir);
    }


    public List<Usuario> listarUsuarios() {
        return repository.findAll();
    }
}
