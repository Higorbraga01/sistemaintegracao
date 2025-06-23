package com.example.sistemaintegracao.controller;

import com.example.sistemaintegracao.model.Usuario;
import com.example.sistemaintegracao.service.UsuarioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }


    @GetMapping
    public List<Usuario> listar() {
        return service.listarUsuarios();
    }

    @PostMapping("/sincronizar")
    public List<Usuario> sincronizar() {
        return service.sincronizarUsuarios();
    }
}
