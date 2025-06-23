package com.example.sistemaintegracao.repository;

import com.example.sistemaintegracao.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
